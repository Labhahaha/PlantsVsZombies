package Plant;

import Bullet.*;
import Game.GamePlay;

import javax.swing.*;

public class Peashooter extends Plant {
    Timer shootTimer;//攻击计时器
    Boolean isShooting;

    //构造方法
    public Peashooter(GamePlay gamePlay) {
        super(68, 68, 100, gamePlay);//豌豆所需阳光值为100
        life = 5;//豌豆生命为5
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/Peashooter.gif")).getImage();
        shootTimer = new Timer(3000, (ActionEvent) -> shoot());//3秒发射一次豌豆，调用攻击方法
        shootTimer.start();
        isShooting = false;//默认状态为不发射
    }

    //攻击
    protected void shoot() {
        if (isShooting) {
            new PeaBullet(this.getX() + 10, this.getY(), super.gamePlay);//初始子弹的位置为豌豆图片的位置
        }
    }

    //setter
    public void setIsShooting(Boolean b) {
        isShooting = b;
    }
}




