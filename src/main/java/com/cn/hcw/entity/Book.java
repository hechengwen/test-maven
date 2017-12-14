package com.cn.hcw.entity;

import java.util.Date;
import java.math.BigDecimal;

import com.cn.hcw.base.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseObject {

    /**  */
    private String bookName;

    /**  */
    private String bookType;

    /**  */
    private BigDecimal bookPirce;

    /**  */
    private String bookId;

    /**  */
    private String bookPublish;

    /**  */
    private Date bookPublishTime;


}