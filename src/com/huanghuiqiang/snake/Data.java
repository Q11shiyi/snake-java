package com.huanghuiqiang.snake;

import javax.swing.*;
import java.net.URL;

/**
 * @author: huanghuiqiang
 * @create: 21.3.17 20:48
 */
public class Data {
    /**
     * 头部图片
     */
    public static URL headerUrl = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);
    /**
     * 头部：上下左右
     */
    public static URL upUrl = Data.class.getResource("/statics/up.png");
    public static URL downUrl = Data.class.getResource("/statics/down.png");
    public static URL leftUrl = Data.class.getResource("/statics/left.png");
    public static URL rightUrl = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    /**
     * 身体
     */
    public static URL bodyUrl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    /**
     * 食物
     */
    public static URL foodUrl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
    /**
     * 爆炸
     */
    public static URL boomUrl = Data.class.getResource("/statics/boom.png");
    public static ImageIcon boom = new ImageIcon(boomUrl);
}
