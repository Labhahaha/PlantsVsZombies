package Plant;

import Game.GamePlay;
import Game.Sound;
import Zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

//樱桃炸弹类
public class CherryBomb extends Plant {
    Timer bombTimer;
    Timer counterTimer;
     //用数组存储一系列图片
    static Image[] plantImage = new Image[11];
    {
        for (int i = 0; i < 11; i++) {
            plantImage[i] = new ImageIcon(this.getClass().getResource("/Image/PlantImage/" + (i + 1) + ".gif")).getImage();
        }
    }
    int counter;//计数器

    //构造方法
    public CherryBomb(GamePlay gamePlay) {
        super(112, 81, 150, gamePlay);
        gamePlay.setLayer(this,6);
        redrawTimer.setDelay(200);//每0.2秒重画一次，以适应图片的播放效果
        redrawTimer.restart();
        counterTimer = new Timer(200,(ActiveEvent) -> count());
        counterTimer.start();
        super.px = 0;//修正图片坐标
        super.py = 0;
        counter = 0;//初始化计数器
        life = 20;//生命为20
        bombTimer = new Timer(1600, (ActionEvent) -> bomb());
        bombTimer.start();
    }

    public void paintComponent(Graphics g) {
        if(counter <= 10){
            g.drawImage(plantImage[counter], px, py, null);//顺序播放每一帧的图片
        }else {
            redrawTimer.stop();
        }
    }

    public void count(){
        counter++;
    }

    //爆炸方法
    public void bomb() {
        Sound bombSound = new Sound(this.getClass().getResource("/Music/CherryBomb.wav"), false);
        bombSound.start();
        redrawTimer.stop();
        int row = (this.getY() - 90) / 100 + 1;//获得图片所在的行
        //如果炸弹放在第一行，则只检测上两行炸弹周围是否有僵尸
        if(row == 1){
            for(int i = 0 ; i < 2 ; i++){
                Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList(row+i).iterator();
                while (zombiesIterator.hasNext()) {
                    Zombie zombie = (Zombie) zombiesIterator.next();
                    //检测炸弹与僵尸的的距离
                    if (Math.abs((zombie.getLabelX() - this.getX())) < 100) {
                        zombie.setState(Zombie.State.DEAD);//设置僵尸死亡
                        zombiesIterator.remove();
                        gamePlay.remove(zombie);
                        bombTimer.stop();
                        System.gc();
                    }
                }
            }
        }
        //如果炸弹放在第五行，则只检测下两行炸弹周围是否有僵尸
        else if (row == 5){
            for(int i = - 1 ; i < 1 ; i ++){
                Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList(row+i).iterator();
                while (zombiesIterator.hasNext()) {
                    Zombie zombie = (Zombie) zombiesIterator.next();
                    //检测炸弹与僵尸的的距离
                    if (Math.abs((zombie.getLabelX() - this.getX())) < 100) {
                        zombie.setState(Zombie.State.DEAD);//设置僵尸死亡
                        zombiesIterator.remove();
                        gamePlay.remove(zombie);
                        bombTimer.stop();
                        System.gc();
                    }
                }
            }
        }
        //如果炸弹放在其他行，则检测所在行和其上下两行炸弹周围的僵尸
        else {
            for(int i = -1 ; i < 2 ; i++){
                Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList(row+i).iterator();
                while (zombiesIterator.hasNext()) {
                    Zombie zombie = (Zombie) zombiesIterator.next();
                    if (zombie.getLabelX() - this.getX() < 150 && zombie.getLabelX() - this.getX() > -100) {
                        zombie.setState(Zombie.State.DEAD);//设置僵尸死亡
                        zombiesIterator.remove();
                        gamePlay.remove(zombie);
                        System.gc();
                    }
                }
            }
        }
        bombTimer.stop();
        life = 0;//移除种植的植物
    }
}