package com.filter.cli;

import java.math.BigDecimal;
import java.math.BigInteger;

public enum DataType {
    INTEGER("integers.txt") {
        public boolean matches(String value) {
            try {
                new BigInteger(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    FLOAT("floats.txt") {
        public boolean matches(String value) {
            try {
                String normalized  = value.replace(",",".");
                new BigDecimal(normalized);
                return !INTEGER.matches(normalized) &&
                        (normalized.contains(".") ||
                                normalized.contains("e") ||
                                normalized.contains("E"));
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    STRING("strings.txt") {
        public boolean matches(String value) {
            return true;
        }

    };

    private final String name;

    DataType(String name) {
        this.name = name;
    }

    public String getFileName() {
        return name;
    }

    public abstract boolean matches(String value);


    public static DataType detentionType(String str) {
        for (DataType dt : DataType.values()) {
            if (dt.matches(str)) {
                return dt;
            }
        }
        return null;
    }
}
