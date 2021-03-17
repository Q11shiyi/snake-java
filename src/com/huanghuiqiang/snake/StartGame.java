package com.huanghuiqiang.snake;

import javax.swing.*;

/**
 * @author: huanghuiqiang
 * @create: 21.3.17 20:48
 */
public class StartGame {

    public static void main(String[] args) {
        //1.新建一个窗口
        JFrame frame = new JFrame("huanghuiqiang-贪吃蛇小游戏");
        // 设置窗口的位置和大小
        frame.setBounds(100,100,900,720);
        //窗口大小不可调整,即固定窗口大小
        frame.setResizable(false);
        // 设置关闭事件，游戏可以关闭
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //2.添加我们自己编写的画布背景
        frame.add(new GamePanel());

        //将窗口展示出来
        frame.setVisible(true);
    }

}
