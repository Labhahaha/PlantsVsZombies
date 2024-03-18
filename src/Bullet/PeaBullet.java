package Bullet;

import Game.GamePlay;

import javax.swing.*;

public class PeaBullet extends Bullet{

    //构造方法
    public PeaBullet(int x , int y , GamePlay g){
        super(x, y ,g);//设置子弹坐标
        this.setIcon(new ImageIcon(this.getClass().getResource("/Image/BulletImage/PeaBullet.png")));
    }
}
