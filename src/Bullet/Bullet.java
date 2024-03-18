package Bullet;
import Game.GamePlay;
import Game.Sound;
import Game.ZombieController;
import Zombie.Zombie;

import javax.swing.*;
import java.util.Iterator;

public abstract class Bullet extends JLabel {
    int labelX;//子弹图片的坐标
    int labelY;
    int xSpeed;//子弹速度
    int row;
    GamePlay gamePlay;
    Timer advanceTimer;//子弹前进计时器
    Timer collisionTimer;//碰撞检测计时器
    boolean isLive;//子弹是否存在

    //构造方法
    public Bullet(int x, int y, GamePlay g) {
        xSpeed = 10;//设置子弹速度
        this.gamePlay = g;
        this.labelX = x;
        this.labelY = y;
        row = (y - 90) / 100 + 1;//子弹所在的行
        isLive = true;//初始子弹存在
        advanceTimer = new Timer(16, (ActionEvent) -> advance());//设置前进计时器
        collisionTimer = new Timer(25, (ActionEvent) -> collisionDetection());//设置碰撞检测计时器
        advanceTimer.start();
        collisionTimer.start();
        this.setSize(56, 34);//图片大小
        this.setVisible(true);
        this.setLocation(x, y);
        gamePlay.add(this);//把此标签加入面板
        gamePlay.setLayer(this, 4);//设置此标签在面板第4层
    }

    //碰撞检测
    public void collisionDetection() {
        if(this.labelX > 1400){
            gamePlay.remove(this);
            advanceTimer.stop();
            collisionTimer.stop();
            System.gc();//回收对象
            return;
        }
        ZombieController zombieController = gamePlay.getZombieController();//得到僵尸数组
        Iterator iterator = zombieController.getZombieArrayList(row).iterator();//在指定行遍历僵尸
        while (iterator.hasNext()) {
            Zombie zombie = (Zombie) iterator.next();//得到僵尸对象
            //僵尸图片的横坐标与子弹图片的横坐标基本重合并且僵尸生命大于0
            if ((zombie.getLabelX() - this.labelX) < 5 && zombie.life > 0 && (zombie.getLabelX() - this.labelX) > -5) {
                Sound sound = new Sound(this.getClass().getResource("/Music/tap.wav"),false);//添加打击音效
                sound.start();
                zombie.life -= 1;//僵尸生命值减1
                gamePlay.remove(this);//移除子弹图片
                isLive = false;//设置子弹不存在
                advanceTimer.stop();
                collisionTimer.stop();
                System.gc();//回收对象
            }
        }
    }

    //子弹前进
    private void advance() {
        labelX += 4;//坐标改变4像素
        this.setLocation(labelX, labelY);
        this.repaint();
    }

}
