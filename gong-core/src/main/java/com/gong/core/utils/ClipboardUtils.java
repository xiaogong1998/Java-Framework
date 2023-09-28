package com.gong.core.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * TODO 剪切板工具类
 *
 * @author xiaogong
 * @since 2023/9/25 16:12
 */
public class ClipboardUtils {

    public static void copyToClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
        System.out.println("已将内容复制到剪切板");
    }
}
