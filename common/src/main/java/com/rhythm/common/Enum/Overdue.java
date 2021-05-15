package com.rhythm.common.Enum;

public enum Overdue {
    EXPIRED(1), // 已过期
    BEEXPIRED(2), // 即将过期
    UNEXPIRED(3), // 未过期

    EXPIREDDAY(7); // 还剩多少天提示即将过期

    private int overdue;
    Overdue(int overdue) {
        this.overdue = overdue;
    }

    public int getOverdue() {
        return overdue;
    }
}
