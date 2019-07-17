package com.api.common.Idcard.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 */

public final class IDCardUtils {
    private static final int IDCardLength15 = 15;
    private static final int IDCardLength18 = 18;
    /**
     * 加权因子
     */
    private static final int[] WI = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,
            4, 2};
    /**
     * 对应的校验码
     */
    private static final char[] CheckBit = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    public static final Pattern PatternForIdCard15 = Pattern
            .compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    public static final Pattern PatternForIdCard18 = Pattern
            .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");

    public static final String DateFormatPattern = "yyyyMMdd";

    /**
     * 15位有效身份证 转化为18位身份证<br>
     * <p>
     * 15位身份证号码各位的含义:<br>
     * 1-2位省、自治区、直辖市代码； <br>
     * 3-4位地级市、盟、自治州代码； <br>
     * 5-6位县、县级市、区代码； <br>
     * 7-12位出生年月日;<br>
     * 13-15位为顺序号，其中15位男为单数，女为双数；<br>
     * <p>
     * 比如670401代表1967年4月1日,与18位的第一个区别； 13-15位为顺序号，其中15位男为单数，女为双数；<br>
     * 与18位身份证号的第二个区别：没有最后一位的验证码。
     *
     * @param idCard15 15位有效身份证
     * @return {@code String} 18位身份证
     */
    public static String transformIdCard15to18(String idCard15) {
        if (StringUtils.isBlank(idCard15) || idCard15.length() != IDCardLength15) {
            throw new IllegalArgumentException("15位身份证长度不合法");
        }
        StringBuilder idCard18 = new StringBuilder(idCard15);
        idCard18.insert(6, "19");

        char checkCode = getIdCardCheckCode(idCard18.toString());

        idCard18.append(checkCode);

        return idCard18.toString();
    }

    private static char getIdCardCheckCode(String idCard18) {

        int sum = 0;
        for (int i = 0, length = IDCardLength18 - 1; i < length; i++) {
            sum += Character.getNumericValue(idCard18.charAt(i)) * WI[i];
        }
        return CheckBit[sum % 11];
    }

    public static boolean isValidIdCard18(String idCard18) {
        if (idCard18.length() != IDCardLength18) {
            return false;
        }

        if (!PatternForIdCard18.matcher(idCard18).find()) {
            return false;
        }
        String birthDay = idCard18.substring(6, 14);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatPattern, Locale.ENGLISH);
        try {
            Date date = getBirthDay(idCard18);
            if (!birthDay.equals(dateFormat.format(date))) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        char idCardCheckCode = getIdCardCheckCode(idCard18);
        if (idCardCheckCode != idCard18.charAt(IDCardLength18 - 1)) {
            return false;
        }

        return true;
    }

    public static boolean isValidIdCard15(String idCard15) {
        if (idCard15.length() != IDCardLength15) {
            return false;
        }

        if (!PatternForIdCard15.matcher(idCard15).find()) {
            return false;
        }

        String birthDay = "19" + idCard15.substring(6, 12);

        SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatPattern, Locale.ENGLISH);
        try {
            Date date = getBirthDay(idCard15);
            if (!birthDay.equals(dateFormat.format(date))) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 获取性别信息
     *
     * @param idCard 15位or18位身份证
     * @return {@code String} 2-女；M-男；N-未知
     */
    public static Byte getGender(String idCard) {
        if (idCard.length() != IDCardLength15 && idCard.length() != IDCardLength18) {
            throw new IllegalArgumentException("身份证长度不合法");
        }

        if (idCard.length() == IDCardLength15) {
            String gender = idCard.substring(IDCardLength15 - 1, IDCardLength15);
            if (Integer.parseInt(gender) % 2 == 0) {
                return 2;
            } else {
                return 1;
            }
        }
        if (idCard.length() == IDCardLength18) {
            String gender = idCard.substring(IDCardLength18 - 2, IDCardLength18 - 1);
            if (Integer.parseInt(gender) % 2 == 0) {
                return 2;
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static Date getBirthDay(String idCard) throws ParseException {
        String birthDay;
        if (idCard.length() == 15) {
            birthDay = "19" + idCard.substring(6, 12);
        } else {
            birthDay = idCard.substring(6, 14);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatPattern, Locale.ENGLISH);
        return dateFormat.parse(birthDay);
    }

}
