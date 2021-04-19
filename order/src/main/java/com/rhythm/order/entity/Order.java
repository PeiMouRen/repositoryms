package com.rhythm.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzpei
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer type;

    @TableField("rpstId")
    private Integer rpstId;

    @TableField("productId")
    private Integer productId;

    @TableField("productNum")
    private Integer productNum;

    @TableField("userId")
    private Integer userId;

    private LocalDateTime time;

    private String des;

    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String productName;
    @TableField(exist = false)
    private String rpstName;
    @TableField(exist = false)
    private String userName;




}
