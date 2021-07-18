package com.etong.pms.constant;

/**
 * 全局常量
 *
 * @author 冯月帅
 */
public class GlobalConstant {

    public static final String LOCALHOST_IP = "127.0.0.1";

    /** 整型常量 */
    public interface Number {
        int ZERO = 0;
        int ONE = 1;
        int TWO = 2;
        int THREE = 3;
        int FOUR = 4;
        int FIVE = 5;
        int SIX = 6;
        int SEVEN = 7;
        int EIGHT = 8;
        int NINE = 9;
        int TEN = 10;
        int HUNDRED = 100;
        int THOUSAND = 1000;
    }

    /** 常用字符 */
    public static final class Symbol {
        private Symbol() {}

        /** The constant COMMA. */
        public static final String COMMA = ",";

        public static final String SPOT = ".";
        /** The constant UNDER_LINE. */
        public static final String UNDER_LINE = "_";
        /** The constant PER_CENT. */
        public static final String PER_CENT = "%";
        /** The constant AT. */
        public static final String AT = "@";
        /** The constant PIPE. */
        public static final String PIPE = "||";

        public static final String SHORT_LINE = "-";
        public static final String SPACE = " ";
        public static final String SLASH = "/";
        public static final String MH = ":";
    }
    /** 常用字符 */
    public static final class Sex {
        private Sex() {}
        /** The constant Man. */
        public static final Integer Man = 0;
        public static final Integer WoMan = 1;
    }
}
