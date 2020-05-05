package com.carry.main.enums;

import lombok.Getter;
import lombok.Setter;


public enum DelayTypeEnum {
    DELAY_10s(1), DELAY_60s(2);


    private int delayType;

    DelayTypeEnum(int delayType) {
        this.delayType = delayType;
    }

    public int getDelayType() {
        return delayType;
    }


    public static DelayTypeEnum getDelayTypeEnumByValue(int delayType) {
        for (DelayTypeEnum type : DelayTypeEnum.values()) {
            if (type.getDelayType() == delayType) {
                return type;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(DelayTypeEnum.getDelayTypeEnumByValue(1));
    }
}
