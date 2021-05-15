package com.rhythm.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzpei
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private String name;

    private Integer size;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("productionDate")
    private LocalDateTime productionDate;

    private Integer duration;

    @TableField("supplyName")
    private String supplyName;

    @TableField("supplyLocation")
    private String supplyLocation;

    @TableField(exist = false)
    private Integer productNum;

    @TableField(exist = false)
    private boolean isUsed;

    @TableField(exist = false)
    private String location;

    @TableField(exist = false)
    private Integer overdue;

    @TableField(exist = false)
    private String startdate;

    @TableField(exist = false)
    private String enddate;

    @TableField(exist = false)
    private Integer rpstId;

    @TableField(exist = false)
    private Long current;

    @TableField(exist = false)
    private Long pageSize;

}
