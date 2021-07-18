package com.etong.pms.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Student
 * @Author wch
 * @DATE 2019/10/9 23:25
 * @return
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Student implements Serializable {
    public String name;
    public Integer age1;
    public Integer age2;
    public Integer age3;
}
