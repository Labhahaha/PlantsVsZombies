package Plant;

import Bullet.*;
import Game.GamePlay;

import javax.swing.*;

//寒冰射手继承自豌豆类
public class SnowPeaShooter extends Peashooter{

    //构造方法
    public SnowPeaShooter(GamePlay gamePlay){
        super(gamePlay);
        super.setSize(70,70);
        super.plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/SnowPea.gif")).getImage();
    }

    protected void shoot(){
        if(isShooting){
            new SnowPeaBullet(this.getX()+40,this.getY(),super.gamePlay);
        }
    }
}
