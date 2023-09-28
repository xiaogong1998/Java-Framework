package com.gong.core.utils;

import com.gong.core.enums.Base64Enum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * TODO 图片工具类
 *
 * @author xiaogong
 * @since 2023/9/25 16:05
 */
public class ImageUtils {

    public static String imageToBase64(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(new File(imagePath).toPath());
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String imagePath = "C:\\Users\\xiaogong\\Desktop\\2023-9-19(11).webp"; // 替换为你的图片路径
        String base64Image = ImageUtils.imageToBase64(imagePath);
        String content = Base64Enum.PNG.getValue() + base64Image;
        ClipboardUtils.copyToClipboard(content);
        // System.out.println(content);
        // System.out.println(base64Image.length());
    }
}
