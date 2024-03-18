package Plant;

import Game.GamePlay;
import Game.Sun;

import javax.swing.*;

public class Sunflower extends Plant{
    Timer sunProduceTimer;//生产阳光计时器
    //构造方法
    public Sunflower(GamePlay gamePlay){
        super(70,80,50 , gamePlay);
        life = 5;//生命值为5
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/Sunflower.gif")).getImage();
        sunProduceTimer = new Timer(20000,(ActionEvent) -> produceSun());//20秒生产一个阳光
        sunProduceTimer.start();
    }

    //在太阳花位置产生阳光，关闭产阳光计时器
    public void produceSun(){
        new Sun(this.getX()-30,this.getY()+20,gamePlay).getDropTimer().stop();
    }
}
