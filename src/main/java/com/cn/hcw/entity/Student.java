package com.cn.hcw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import com.cn.hcw.base.BaseObject;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseObject {

    /** id主键 */
    private String studentId;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 性别 */
    private String sex;

    /** 身高 */
    private Object height;

    /** 体重 */
    private Object weight;

    /** 住址 */
    private String address;

    /** 公司 */
    private String company;


}