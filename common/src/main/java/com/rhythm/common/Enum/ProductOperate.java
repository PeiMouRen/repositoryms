package com.rhythm.common.Enum;

public enum ProductOperate {
    IN(1), // 入库
    OUT(-1); // 出库

    private int operate;

    ProductOperate(int operate) {
        this.operate = operate;
    }

    public int getOperate() {
        return operate;
    }
}
