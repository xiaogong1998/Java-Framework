package com.gong.core.utils;

import java.util.Random;

/**
 * 工具类
 */
public class RandomColorUtils {

    /**
     * 返回自定义的随机颜色
     *
     * @return
     */
    public static String getCustomizeColor() {
        String[][] mColors = {
                {"#39ADD1", "#3079AB", "#C25975", "#E15258", "#F9845B"},
                {"#838CC7", "#7D669E", "#53BBB4", "#51B46D", "#E0AB18"},
                {"#838CC7", "#7D669E", "#53BBB4", "#51B46D", "#E0AB18"},
                {"#637A91", "#F092B0", "#B7C0C7", "#D4B8B4", "#CCD2CC"},
                {"#D8CBA", "#C4BAB1", "#F7ACBC", "#DEAB8A", "#817936"},
                {"#EF5B9C", "#FEDCBD", "#FEEEED", "#F05B72", "#905A3D"},
                {"#F15B6C", "#F8ABA6", "#F69C9F", "#F58F98", "#F391A9"},
                {"#FAB27B", "#B7BA6B", "#BD6758", "#BED742", "#A3CF62"}
        };
        Random randomGenerator = new Random();
        int randomX = randomGenerator.nextInt(mColors.length);
        int randomY = randomGenerator.nextInt(mColors[randomX].length);
        return mColors[randomX][randomY];
    }

    /**
     * 获取随机颜色
     *
     * @return 颜色
     */
    public static String getRandomColor() {
        // 生成随机对象
        Random random = new Random();
        // 生成红色颜色代码
        String red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        // 生成绿色颜色代码
        String green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        // 生成蓝色颜色代码
        String blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        StringBuilder color = new StringBuilder("#");
        color.append(red.length() == 1 ? "0" + red : red);
        color.append(green.length() == 1 ? "0" + green : green);
        color.append(blue.length() == 1 ? "0" + blue : blue);
        return color.toString();
    }
}
