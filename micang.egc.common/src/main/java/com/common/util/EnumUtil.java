package com.common.util;

import com.common.enumerate.BaseEnum;

public class EnumUtil {

    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int index) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getIndex() == index)
                return e;
        }
        return null;
    }
}
