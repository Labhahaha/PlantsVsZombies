package Plant;

import Game.GamePlay;
import Zombie.Zombie;

import javax.swing.*;
import java.util.Iterator;

public class Chomper extends Plant{
    //设置状态的枚举变量
    public enum State {
        READY, DIGEST
    }
    State state;
    Timer biteTimer;//大嘴花吃僵尸计时器
    Timer resetTimer;//状态恢复计时器

    //构造方法
    public Chomper(GamePlay gamePlay){
        super(98,110,150,gamePlay);
        state = State.READY;//设置植物状态
        life = 10;//大嘴花生命值
        px = - 1;//修正图片位置
        py = -25;
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/Chomper.gif")).getImage();
        biteTimer = new Timer(50,(ActionEvent)-> bite());//检测大嘴花吃的状态
        biteTimer.start();
        resetTimer = new Timer(30000,(ActionEvent) -> reset());//30秒状态恢复
    }

    //大嘴花吃僵尸
    public void bite() {
        if (state == State.READY) {
            //遍历僵尸对象
            Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList((this.getY() - 90) / 100 + 1).iterator();
            while (zombiesIterator.hasNext()) {
                Zombie zombie = (Zombie) zombiesIterator.next();
                if ((zombie.getLabelX() - this.getX()) < 112 && (zombie.getLabelX() - this.getX()) > 0) {
                    repaint();
                    zombie.setState(Zombie.State.DEAD);//设置僵尸死亡
                    zombiesIterator.remove();
                    gamePlay.remove(zombie);
                    System.gc();
                    px = -5;//图片坐标修正
                    py = -30;
                    this.setSize(76,80);
                    plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/ChomperDigest.gif")).getImage();
                    this.repaint();
                    state = State.DIGEST;//食人花状态变为消化中
                    biteTimer.stop();
                    resetTimer.start();//状态恢复计时器开启，30秒后状态恢复
                }
            }
        }
    }

    //状态恢复
    public void reset(){
        px = - 1;//图片坐标修正
        py = -25;
        this.setSize(98,91);
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/Chomper.gif")).getImage();//图片还原
        this.repaint();
        state = State.READY;//设置状态为准备
        resetTimer.stop();//状态恢复计时器关闭
        biteTimer.start();//大嘴花吃僵尸计时器开启
    }

    //计时器关闭
    public void timerStop(){
        biteTimer.stop();
        resetTimer.stop();
    }
}
