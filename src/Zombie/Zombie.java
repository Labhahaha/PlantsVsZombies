package Zombie;

import javax.swing.*;
import java.awt.*;

public abstract class Zombie extends JPanel {
    //僵尸图片的高度和宽度
    public int WIDTH;
    public int HEIGHT;
    public int row;//僵尸所在的行
    public int speed;//僵尸的横向速度
    //用于调整图片位置的参数
    public int px;
    public int py;
    public int px0;
    public int py0;
    public int ly;
    public int pw;
    public int ph;
    //僵尸的状态
    public enum State {
        ADVANCE, ATTACK, DEAD;
    }

    State state;
    public int life;//僵尸生命值

    JLayeredPane layeredPane;

    Timer redrawTimer;//重画计时器
    Timer advanceTimer;//僵尸前进计时器
    Timer resetSpeedTimer;//速度恢复计时器
    public Image zombieNormalAttackImage;
    public Image zombieNormalAdvanceImage;
    public Image zombieFrozenAttackImage;
    public Image zombieFrozenAdvanceImage;
    public Image zombieAdvanceImage;
    public Image zombieAttackImage;
    public Image zombieDeadImage;
    public Image zombieHeadImage;
    int labelX = 0;//图片位置坐标
    int labelY = 0;

    //构造方法
    public Zombie(int labelX, int labelY, int width, int height, JLayeredPane layeredPane) {
        px0 = -62;//修正参数
        py0 = -21;
        ly = 0;
        pw = 0;
        ph = -5;
        zombieAdvanceImage = zombieNormalAdvanceImage;
        zombieAttackImage = zombieNormalAttackImage;
        this.row = (labelY - 40) / 100;//设置僵尸所在行
        this.labelX = labelX;//设置图片位置
        this.labelY = labelY;
        state = State.ADVANCE;//初始状态为前进
        this.layeredPane = layeredPane;
        WIDTH = width;//设置图片大小
        HEIGHT = height;
        speed = 2;//前进速度大小
        super.setSize(WIDTH, HEIGHT);
        super.setOpaque(false);//设置背景透明
        this.setLocation(labelX, labelY);
        redrawTimer = new Timer(25, (ActionEvent) -> repaint());
        redrawTimer.start();
        advanceTimer = new Timer(100, (ActionEvent) -> advance());
        advanceTimer.start();
        resetSpeedTimer = new Timer(4000, (ActionListener) -> resetSpeed());//减速状态下，每4秒恢复一次速度
        resetSpeedTimer.start();
        setVisible(true);
        layeredPane.add(this);
        layeredPane.setLayer(this, 4);//设置僵尸图片在第4层
    }

    //画图片方法
    public void paintComponent(Graphics g) {
        if (state == State.ADVANCE) {
            super.paintComponent(g);
            g.drawImage(zombieAdvanceImage, px, py, null);//用修正后的坐标画图
        } else if (state == State.ATTACK) {
            super.paintComponent(g);
            g.drawImage(zombieAttackImage, px - 2, py - 3, null);//用修正后的坐标画图
        } else if (state == State.DEAD) {
            super.paintComponent(g);
            g.drawImage(zombieDeadImage, px, py, null);//画僵尸没头的图片
            g.drawImage(zombieHeadImage, px, py, null);//画僵尸掉头的图片
        }
    }

    //僵尸前进方法，由僵尸前进计时器重复调用
    public void advance() {
        labelX -= speed;//坐标左移
        this.setLocation(labelX, labelY+ly);//重设位置
        this.repaint();
    }

    //速度恢复方法，由速度恢复计时器调用
    public void resetSpeed() {
        px = px0;
        py = py0;
        setSpeed(2);//修改回初始速度
        zombieAdvanceImage = zombieNormalAdvanceImage;//修改回普通图片
        zombieAttackImage = zombieNormalAttackImage;//修改回普通图片
        resetSpeedTimer.stop();//关闭计时器
    }

    //减速方法
    public void slow() {
        px = 0;
        py = 0;
        //如果速度不为1，则调整速度
        if (speed != 1) {
            zombieAdvanceImage = zombieFrozenAdvanceImage;//修改为减速图片
            zombieAttackImage = zombieFrozenAttackImage;//修改为减速图片
            setSpeed(1);//调整速度
        }
        resetSpeedTimer.restart();//重新启动计时器
    }

    //getter setter
    public int getLabelY() {
        return labelY;
    }

    public int getLabelX() {
        return labelX;
    }

    public Timer getAdvanceTimer() {
        return advanceTimer;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        if (state == State.ATTACK) {
            super.setSize(WIDTH + pw, HEIGHT + ph);//如果僵尸在攻击，调整图片大小
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
