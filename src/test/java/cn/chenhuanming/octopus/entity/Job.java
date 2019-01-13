package cn.chenhuanming.octopus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenhuanming
 * Created at 2018/12/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private Company company;
    private String JobDescription;
    private String rank;
}
