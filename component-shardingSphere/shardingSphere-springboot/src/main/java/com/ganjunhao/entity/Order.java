package com.ganjunhao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ganjunhao
 * @date 2023/6/12 15:25
 */
@Data
@TableName("t_order")
public class Order {

    private static final long serialVersionUID = 661434701950670670L;

    private long orderId;

    private int userId;

    private long addressId;

    private String status;
}
