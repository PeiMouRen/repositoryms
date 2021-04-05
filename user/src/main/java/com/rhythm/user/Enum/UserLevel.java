package com.rhythm.user.Enum;

public enum UserLevel {
    ADMIN(1), OPERATOR_ADVANCED(2), OPERATOR_ORDINARY(3);

    private int level;

    UserLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
