package com.gong.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 10进制转36进制工具类
 *
 * @author xiaogong
 * @since 2023/9/27 14:29
 */
public class BaseConvertUtils {

    public static void main(String[] args) throws InterruptedException {
        // long decimalNumber = 36 * 72000;
        // String thirtySixBaseNumber = convertTo36Base(decimalNumber);
        // System.out.println("36进制数为：" + thirtySixBaseNumber);
        // long i = convertToDecimal("a1");
        // System.out.println("10进制数为：" + i);

        // String excelColumn = "Z";
        // long columnNumber = convertToNumber(excelColumn);
        // System.out.println("Excel列 " + excelColumn + " 对应的数字为：" + columnNumber);
        //
        // long number = 26;
        // String excelColumnNumber = convertToExcelColumn(number);
        // System.out.println("数字 " + number + " 对应的Excel列为：" + excelColumnNumber);
        for (int i = 1; i <= 1000; i++) {
            String excelColumnNumber = convertToExcelColumn(i);
            System.out.println("数字 " + i + " 对应的Excel列为：" + excelColumnNumber);
        }
    }

    // ========== 十进制与三十六进制的相互转换 ==========

    /**
     * 十进制转三十六进制
     *
     * @param decimalNumber
     * @return
     */
    public static String convertTo36Base(long decimalNumber) {
        List<Character> thirtySixBaseDigits = new ArrayList<>();
        while (decimalNumber > 0) {
            long remainder = decimalNumber % 36;
            if (remainder < 10) {
                thirtySixBaseDigits.add((char) ('0' + remainder));
            } else {
                thirtySixBaseDigits.add((char) ('A' + remainder - 10));
            }
            decimalNumber /= 36;
        }
        StringBuilder thirtySixBaseNumber = new StringBuilder();
        for (int i = thirtySixBaseDigits.size() - 1; i >= 0; i--) {
            thirtySixBaseNumber.append(thirtySixBaseDigits.get(i));
        }
        return thirtySixBaseNumber.toString();
    }

    /**
     * 三十六进制转十进制
     *
     * @param thirtySixBaseNumber
     * @return
     */
    public static long convertToDecimal(String thirtySixBaseNumber) {
        thirtySixBaseNumber = thirtySixBaseNumber.toUpperCase();
        long decimalNumber = 0;
        long power = 0;
        for (int i = thirtySixBaseNumber.length() - 1; i >= 0; i--) {
            char digit = thirtySixBaseNumber.charAt(i);
            int value;
            if (digit >= '0' && digit <= '9') {
                value = digit - '0';
            } else if (digit >= 'A' && digit <= 'Z') {
                value = digit - 'A' + 10;
            } else {
                throw new IllegalArgumentException("无效的36进制数：" + thirtySixBaseNumber);
            }
            decimalNumber += value * Math.pow(36, power);
            power++;
        }
        return decimalNumber;
    }

    // ========== Excel列与十进制的相互转换 ==========

    public static long convertToNumber(String excelColumn) {
        long columnNumber = 0;
        int power = 0;
        for (int i = excelColumn.length() - 1; i >= 0; i--) {
            int digitValue = excelColumn.charAt(i) - 'A' + 1;
            columnNumber += digitValue * Math.pow(26, power);
            power++;
        }
        return columnNumber;
    }

    public static String convertToExcelColumn(long number) {
        StringBuilder excelColumnNumber = new StringBuilder();
        while (number > 0) {
            long remainder = (number - 1) % 26;
            char digit = (char) ('A' + remainder);
            excelColumnNumber.insert(0, digit);
            number = (number - 1) / 26;
        }
        return excelColumnNumber.toString();
    }
}
