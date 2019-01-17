package cn.chenhuanming.octopus.core.config;


import cn.chenhuanming.octopus.formatter.DateFormatter;
import cn.chenhuanming.octopus.formatter.DefaultFormatterContainer;
import cn.chenhuanming.octopus.formatter.Formatter;
import cn.chenhuanming.octopus.formatter.FormatterContainer;
import cn.chenhuanming.octopus.model.DefaultField;
import cn.chenhuanming.octopus.core.Field;
import cn.chenhuanming.octopus.util.ColorUtils;
import cn.chenhuanming.octopus.util.ReflectionUtils;
import cn.chenhuanming.octopus.util.StringUtils;
import cn.chenhuanming.octopus.util.StyleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author chenhuanming
 * Created at 2018/12/17
 */
@Slf4j
public abstract class AbstractXMLConfigReader extends CachedConfigReader {

    protected final ByteArrayInputStream is;

    protected static final String SPLITTER = "\\|";

    public AbstractXMLConfigReader(InputStream is) {
        try {
            this.is = new ByteArrayInputStream(IOUtils.toByteArray(is));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static final String SCHEMA_URI = "https://raw.githubusercontent.com/zerouwar/my-maven-repo/master/cn/chenhuanming/octopus/1.0.0/octopus.xsd";

    protected void validateXML(Source source) throws Exception {
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new URL(SCHEMA_URI));
        Validator validator = schema.newValidator();
        validator.validate(source);
        is.reset();
    }

    protected String getAttribute(Node node, String name) {
        if (node == null) {
            return null;
        }
        NamedNodeMap attributes = node.getAttributes();
        Node item = attributes.getNamedItem(name);
        if (item == null) {
            return null;
        }
        return item.getNodeValue();
    }



    private FormatterContainer readFormatter(Node formatNode) {
        DefaultFormatterContainer container = new DefaultFormatterContainer();

        String dateFormat = getAttribute(formatNode, XMLConfig.Formatters.Attribute.DATE_FORMAT);
        if (StringUtils.isEmpty(dateFormat)) {
            container.addFormat(Date.class, new DateFormatter("yyyy-MM-dd HH:mm:ss"));
        } else {
            container.addFormat(Date.class, new DateFormatter(dateFormat));
        }

        if (formatNode != null && formatNode.hasChildNodes()) {
            NodeList children = formatNode.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node item = children.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE || !item.getNodeName().equals(XMLConfig.Formatters.Formatter.name)) {
                    continue;
                }
                String targetClass = getAttribute(item, XMLConfig.Formatters.Formatter.Attribute.TARGET);
                String formatClass = getAttribute(item, XMLConfig.Formatters.Formatter.Attribute.CLASS);

                try {
                    Class target = Class.forName(targetClass);
                    Class format = Class.forName(formatClass);
                    container.addFormat(target, (Formatter) format.newInstance());
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }

            }
        }
        return container;
    }

    private Field getField(Node node, Class classType) {
        DefaultField field = new DefaultField();

        setBaseConfig(field, node);

        setCellStyleConfig(field, node);

        setHeaderCellStyleConfig(field, node);

        setInvoker(field, classType);

        if (node.getNodeName().equals(XMLConfig.Field.name)) {
            setImportValidation(field, node);
        }

        NodeList children = node.getChildNodes();

        Class headerType = node.getNodeName().equals(XMLConfig.Root.name) ? classType : (field.getPicker() != null ? field.getPicker().getReturnType() : null);
        for (int i = 0; i < children.getLength(); i++) {
            Node item = children.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE || (!item.getNodeName().equals(XMLConfig.Field.name) && !item.getNodeName().equals(XMLConfig.Header.name))) {
                continue;
            }
            field.addChildren(getField(children.item(i), headerType));
        }
        return field;
    }

    private void setBaseConfig(DefaultField field, Node node) {
        String name = getAttribute(node, XMLConfig.Field.Attribute.NAME);
        if (!StringUtils.isEmpty(name)) {
            field.setName(name);
        }
        String desc = getAttribute(node, XMLConfig.Field.Attribute.DESCRIPTION);
        if (!StringUtils.isEmpty(desc)) {
            field.setDescription(desc);
        }

        String dateFormat = getAttribute(node, XMLConfig.Field.Attribute.DATE_FORMAT);
        if (!StringUtils.isEmpty(dateFormat)) {
            field.setDateFormat(new DateFormatter(dateFormat));
        }

        //read formatter
        String formatterStr = getAttribute(node, XMLConfig.Field.Attribute.FORMATTER);
        if (!StringUtils.isEmpty(formatterStr)) {
            try {
                Class formatterClass = Class.forName(formatterStr);
                if (!Formatter.class.isAssignableFrom(formatterClass)) {
                    log.error(formatterStr + " is not subclass of cn.chenhuanming.octopus.model.formatter.Formatters");
                } else {
                    field.setFormatter((Formatter) formatterClass.newInstance());
                }
            } catch (Exception e) {
                log.warn(formatterStr + " may not have a default constructor");
            }
        }
    }

    private void setCellStyleConfig(DefaultField field, Node node) {
        String fontSize = getAttribute(node, XMLConfig.Field.Attribute.FONT_SIZE);
        if (!StringUtils.isEmpty(fontSize)) {
            field.setFontSize(Short.parseShort(fontSize));
        }
        String color = getAttribute(node, XMLConfig.Field.Attribute.COLOR);
        if (!StringUtils.isEmpty(color)) {
            field.setColor(ColorUtils.hex2Rgb(color));
        }
        String isBold = getAttribute(node, XMLConfig.Field.Attribute.IS_BOLD);
        if (!StringUtils.isEmpty(isBold)) {
            field.setBold(Boolean.parseBoolean(isBold));
        }
        String foregroundColor = getAttribute(node, XMLConfig.Field.Attribute.FOREGROUND_COLOR);
        if (!StringUtils.isEmpty(foregroundColor)) {
            field.setForegroundColor(ColorUtils.hex2Rgb(foregroundColor));
        }
        String border = getAttribute(node, XMLConfig.Field.Attribute.BORDER);
        if (!StringUtils.isEmpty(border)) {
            field.setBorder(StyleUtils.convertBorder(border));
        }
        String borderColor = getAttribute(node, XMLConfig.Field.Attribute.BORDER_COLOR);
        if (!StringUtils.isEmpty(borderColor)) {
            field.setBorderColor(ColorUtils.convertBorderColor(borderColor));
        }
    }

    private void setHeaderCellStyleConfig(DefaultField field, Node node) {
        String fontSize = getAttribute(node, XMLConfig.Header.Attribute.HEADER_FONT_SIZE);
        if (!StringUtils.isEmpty(fontSize)) {
            field.setHeaderFontSize(Short.parseShort(fontSize));
        }
        String color = getAttribute(node, XMLConfig.Header.Attribute.HEADER_COLOR);
        if (!StringUtils.isEmpty(color)) {
            field.setHeaderColor(ColorUtils.hex2Rgb(color));
        }
        String isBold = getAttribute(node, XMLConfig.Header.Attribute.IS_HEADER_BOLD);
        if (!StringUtils.isEmpty(isBold)) {
            field.setHeaderBold(Boolean.parseBoolean(isBold));
        }
        String foregroundColor = getAttribute(node, XMLConfig.Header.Attribute.HEADER_FOREGROUND_COLOR);
        if (!StringUtils.isEmpty(foregroundColor)) {
            field.setHeaderForegroundColor(ColorUtils.hex2Rgb(foregroundColor));
        }
        String border = getAttribute(node, XMLConfig.Header.Attribute.HEADER_BORDER);
        if (!StringUtils.isEmpty(border)) {
            field.setHeaderBorder(StyleUtils.convertBorder(border));
        }
        String borderColor = getAttribute(node, XMLConfig.Header.Attribute.HEADER_BORDER_COLOR);
        if (!StringUtils.isEmpty(borderColor)) {
            field.setHeaderBorderColor(ColorUtils.convertBorderColor(borderColor));
        }
    }

    private void setInvoker(DefaultField field, Class classType) {
        if (classType == null || StringUtils.isEmpty(field.getName())) {
            return;
        }
        //set picker
        Method picker = ReflectionUtils.readMethod(classType, field.getName());
        field.setPicker(picker);

        //set pusher
        Method pusher = ReflectionUtils.writeMethod(classType, field.getName());
        field.setPusher(pusher);
    }

    private void setImportValidation(DefaultField field, Node node) {
        String isBlankable = getAttribute(node, XMLConfig.Field.Attribute.IS_BLANKABLE);
        if (!StringUtils.isEmpty(isBlankable)) {
            field.setBlankable(Boolean.parseBoolean(isBlankable));
        }

        String regex = getAttribute(node, XMLConfig.Field.Attribute.REGEX);
        if (!StringUtils.isEmpty(regex)) {
            field.setRegex(Pattern.compile(regex));
        }
        String options = getAttribute(node, XMLConfig.Field.Attribute.OPTIONS);
        if (!StringUtils.isEmpty(options) && options.length() >= 2) {
            String[] split = options.split(SPLITTER);
            field.setOptions(Arrays.asList(split));
        }
    }


    /**
     * xml config constant
     */
    protected interface XMLConfig {

        interface Root {
            String name = "Root";

            interface Attribute {
                String CLASS = "class";
            }
        }

        interface Header {
            String name = "Header";

            interface Attribute {
                String NAME = "name";
                String DESCRIPTION = "description";
                String HEADER_FONT_SIZE = "header-font-size";
                String HEADER_COLOR = "header-color";
                String IS_HEADER_BOLD = "header-is-bold";
                String HEADER_FOREGROUND_COLOR = "header-foreground-color";
                String HEADER_BORDER = "header-border";
                String HEADER_BORDER_COLOR = "header-border-color";
            }
        }

        interface Field {
            String name = "Field";

            interface Attribute extends Header.Attribute {
                String FONT_SIZE = "font-size";
                String COLOR = "color";
                String IS_BOLD = "is-bold";
                String FOREGROUND_COLOR = "foreground-color";
                String BORDER = "border";
                String BORDER_COLOR = "border-color";

                String DATE_FORMAT = "date-format";
                String FORMATTER = "formatter";
                String IS_BLANKABLE = "is-blankable";
                String REGEX = "regex";
                String OPTIONS = "options";
            }
        }

        interface Formatters {
            String name = "Formatters";

            interface Formatter {
                String name = "Formatter";

                interface Attribute {
                    String TARGET = "target";
                    String CLASS = "class";
                }
            }

            interface Attribute {
                String DATE_FORMAT = "date-format";
            }
        }
    }
}
