package com.rhythm.order.Enum;

public enum OrderType {
    INSTORAGE(1), // 入库
    OUTSTORAGE(2); // 出库

    private int type;
    OrderType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
