package com.rhythm.user.Enum;

public enum UserLevel {
    ADMIN(1), // 管理员
    OPERATOR_ADVANCED(2), // 高级操作员
    OPERATOR_ORDINARY(3); // 普通操作员

    private int level;

    UserLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
