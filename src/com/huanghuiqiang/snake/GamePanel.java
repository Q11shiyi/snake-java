package com.huanghuiqiang.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author: huanghuiqiang
 * @create: 21.3.17 20:48
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    /**
     * 蛇的长度
     */
    int length;
    /**
     * 蛇的坐标x
     */
    int[] snakeX = new int[600];
    /**
     * 蛇的坐标y
     */
    int[] snakeY = new int[500];
    /**
     * 蛇的方向 ： R:右  L:左  U:上  D:下
     */
    String fx = "R";
    /**
     * 游戏是否开始
     */
    boolean isStart = false;
    /**
     * 定时器：第一个参数，就是定时执行时间
     */
    Timer timer = new Timer(100, this);
    /**
     * 食物
     */
    int foodX;
    int foodY;

    Random random = new Random();
    /**
     * 游戏是否结束
     */
    boolean isFail = false;
    /**
     * 游戏分数！
     */
    int score;

    public GamePanel(){
        //初始化
        init();
        //获取焦点事件
        this.setFocusable(true);
        //键盘监听事件
        this.addKeyListener(this);
        timer.start();
    }

    /**
     * 初始化方法
     */
    public void init(){
        //初始小蛇有三节,包括小脑袋
        length = 3;
        //初始化开始的蛇,给蛇定位,
        snakeX[0] = 100; snakeY[0] = 100;
        snakeX[1] = 75; snakeY[1] = 100;
        snakeX[2] = 50; snakeY[2] = 100;

        //初始化食物数据
        foodX = 25 + 25* random.nextInt(34);
        foodY = 75 + 25* random.nextInt(24);

        //初始化游戏分数
        score = 0;
    }


    /**
     * 画组件
     * @param g g
     */
    @Override
    public void paintComponent(Graphics g){
        //清屏
        super.paintComponent(g);
        //设置面板的背景色
        this.setBackground(Color.WHITE);
        //绘制头部信息区域
        Data.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,600);

        //把小蛇画上去 蛇的头通过方向变量来判断
        switch (fx) {
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            default:
                break;
        }
        for (int i = 1; i < length; i++) {
            //蛇的身体长度根据length来控制
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //画食物
        Data.food.paintIcon(this,g, foodX, foodY);

        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度 " + length,750,35);
        g.drawString("分数 " + score,750,50);

        //游戏提示
        if (!isStart){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏!",300,300);
        }
        //失败判断
        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("失败, 按下空格重新开始",200,300);
        }

    }

    private final static String R = "R";
    private final static String L = "L";
    private final static String U = "U";
    private final static String D = "D";


    /**
     * 键盘监听事件
     * @param e e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键盘
        int keyCode = e.getKeyCode();

        //如果是空格
        if (keyCode==KeyEvent.VK_SPACE){
            //如果游戏失败,从头再来！
            if (isFail){
                isFail = false;
                //重新初始化
                init();
            //否则，暂停游戏
            }else {
                isStart = !isStart;
            }
            repaint();
        }

        //键盘控制走向
        if (keyCode==KeyEvent.VK_LEFT){
            if (!R.equals(fx)){
                fx = L;
            }
        }else if (keyCode==KeyEvent.VK_RIGHT){
            if (!L.equals(fx)){
                fx = R;
            }
        }else if (keyCode==KeyEvent.VK_UP){
            if (!D.equals(fx)){
                fx = U;
            }
        }else if (keyCode==KeyEvent.VK_DOWN){
            if (!U.equals(fx)){
                fx = D;
            }
        }

    }


    /**
     * 定时执行的操作
     * @param e e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且没有结束，则小蛇可以移动
        if (isStart && !isFail){
            //右移:即让后一个移到前一个的位置即可 !
            //除了脑袋都往前移：身体移动
            for (int i = length -1; i > 0; i--) {
                //即第i节(后一节)的位置变为(i-1：前一节)节的位置！
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //通过方向控制，头部移动
            switch (fx) {
                case "R":
                    snakeX[0] = snakeX[0] + 25;
                    if (snakeX[0] > 850) {
                        snakeX[0] = 25;
                    }
                    break;
                case "L":
                    snakeX[0] = snakeX[0] - 25;
                    if (snakeX[0] < 25) {
                        snakeX[0] = 850;
                    }
                    break;
                case "U":
                    snakeY[0] = snakeY[0] - 25;
                    if (snakeY[0] < 75) {
                        snakeY[0] = 650;
                    }
                    break;
                case "D":
                    snakeY[0] = snakeY[0] + 25;
                    if (snakeY[0] > 650) {
                        snakeY[0] = 75;
                    }
                    break;
                default:
                    break;
            }

            //吃食物:当蛇的头和食物一样时,算吃到食物!
            if (snakeX[0]== foodX && snakeY[0]== foodY){
                //1.长度加一
                length++;
                //每吃一个食物，增加积分
                score = score + 10;
                //2.重新生成食物
                foodX = 25 + 25* random.nextInt(34);
                foodY = 75 + 25* random.nextInt(24);
            }

            //结束判断，头和身体撞到了
            for (int i = 1; i < length; i++) {
                //如果头和身体碰撞，那就说明游戏失败
                if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                    isFail = true;
                    break;
                }
            }

            //需要不断的更新页面实现动画
            repaint();
        }
        //让时间动起来!
        timer.start();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
