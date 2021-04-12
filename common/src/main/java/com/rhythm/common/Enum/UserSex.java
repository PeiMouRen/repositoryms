package com.rhythm.common.Enum;

public enum UserSex {
    MALE(1),  // 雄性
    FEMALE(0); // 雌性
    private int sex;

    UserSex (int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return this.sex;
    }

}
