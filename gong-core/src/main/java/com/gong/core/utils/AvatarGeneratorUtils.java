package com.gong.core.utils;

import com.gong.core.enums.AvatarEnum;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class AvatarGeneratorUtils {
    private static final int IMAGE_SIZE = 300; // 画布大小

    private static final Random random = new Random();

    public static String generateAvatar(String name) {
        return generateAvatar(name, AvatarEnum.solidBackground);
    }

    public static String generateAvatar(String name, AvatarEnum avatarEnum) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return null;
            }
            String nameWritten = getNameWritten(name);

            BufferedImage bi = generateAvatarBackground(avatarEnum);
            Graphics2D g2 = (Graphics2D) bi.getGraphics();
            g2.setPaint(Color.WHITE);

            Font font = new Font("微软雅黑", Font.PLAIN, (int) (0.3 * IMAGE_SIZE));
            g2.setFont(font);

            FontMetrics fontMetrics = g2.getFontMetrics(font);
            int stringWidth = fontMetrics.stringWidth(nameWritten);
            int ascent = fontMetrics.getAscent();
            int descent = fontMetrics.getDescent();
            int x = (IMAGE_SIZE - stringWidth) / 2;
            int y = (IMAGE_SIZE - (ascent + descent)) / 2 + ascent;

            // 绘制描边效果
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            g2.drawString(nameWritten, x - 1, y);
            g2.drawString(nameWritten, x + 1, y);
            g2.drawString(nameWritten, x, y - 1);
            g2.drawString(nameWritten, x, y + 1);
            // 绘制姓名
            g2.setColor(Color.WHITE);
            g2.drawString(nameWritten, x, y);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            log.error("生成文本内容为：{}的Base64图片失败，原因：{}", name, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String getNameWritten(String name) {
        String first = name.substring(1);
        if (isChinese(first)) {
            return name.substring(name.length() - 2);
        } else {
            return name.substring(0, 1).toUpperCase();
        }
    }

    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static BufferedImage generateAvatarBackground(AvatarEnum avatarEnum) {
        BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(getRandomColor());
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);

        if (AvatarEnum.mixedColorBackground.equals(avatarEnum)) {
            for (int i = 0; i < 0.1 * IMAGE_SIZE; i++) {
                int size = random.nextInt((int) (0.2 * IMAGE_SIZE));
                int x = random.nextInt(IMAGE_SIZE);
                int y = random.nextInt(IMAGE_SIZE);
                Color color = getRandomColor();

                graphics.setColor(color);
                graphics.fillOval(x, y, size, size);
            }
        }

        graphics.dispose();

        return image;
    }

    private static Color getRandomColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
}
