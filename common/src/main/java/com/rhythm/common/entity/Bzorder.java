package com.rhythm.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzpei
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Bzorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer type;

    @TableField("rpstName")
    private String rpstName;

    @TableField("productName")
    private String productName;

    @TableField("productNum")
    private Integer productNum;

    @TableField("userName")
    private String userName;

    @TableField("optName")
    private String optName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    private String des;

    @TableField(exist = false)
    private String startdate;

    @TableField(exist = false)
    private String enddate;

    @TableField(exist = false)
    private Long current;

    @TableField(exist = false)
    private Long pageSize;


}
