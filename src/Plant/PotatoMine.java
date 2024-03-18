package Plant;

import Game.GamePlay;
import Game.Sound;
import Zombie.Zombie;

import javax.swing.*;
import java.util.Iterator;

public class PotatoMine extends Plant {
    Timer stateChangeTimer;//状态改变计时器
    Timer bombTimer;

    //土豆地雷的4种状态
    public enum State {
        UNDERGROUND, READY, BOMB, DIE;
    }

    //枚举变量
    State state;

    //构造方法
    public PotatoMine(GamePlay gamePlay) {
        super(36, 30, 25, gamePlay);
        super.px = -20;//修正图片坐标
        super.py = -23;
        state = State.UNDERGROUND;//初始状态
        life = 5;//生命为5
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/PotatoMineNotReady.png")).getImage();
        stateChangeTimer = new Timer(15000, (ActionEvent) -> stateChange());//15秒状态改变为READY
        stateChangeTimer.start();
        bombTimer = new Timer(500, (ActionEvent) -> bomb());
    }

    public void stateChange() {
        super.px = -1;//修正图片坐标
        super.py = 0;
        this.setLocation(this.getX() - 10, this.getY() - 10);
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/PotatoMine.gif")).getImage();//更换图片
        this.setSize(74, 53);
        this.repaint();
        state = State.READY;//状态改变为准备
        stateChangeTimer.stop();
        bombTimer.start();
    }

    public void timerStop(){
        stateChangeTimer.stop();
        bombTimer.stop();
    }
    public void bomb() {
        if (state == State.READY) {
            //遍历僵尸对象
            Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList((this.getY() - 90) / 100 + 1).iterator();
            while (zombiesIterator.hasNext()) {
                Zombie zombie = (Zombie) zombiesIterator.next();
                if ((zombie.getLabelX() - this.getX()) < 35) {
                    Sound bombSound = new Sound(this.getClass().getResource("/Music/explosion.wav"), false);
                    bombSound.start();
                    this.setSize(131, 92);
                    plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/PotatoMine_mashed.gif")).getImage();//更换爆炸图片
                    repaint();
                    zombie.setState(Zombie.State.DEAD);//设置僵尸死亡
                    zombiesIterator.remove();
                    gamePlay.remove(zombie);
                    System.gc();
                    state = State.BOMB;//土豆状态变为爆炸
                }
            }
        } else if (state == State.BOMB) {
            this.setSize(74, 53);
            bombTimer.stop();
            gamePlay.getGrass(this.getX(), this.getY()).removeAssignedPlant();//移除种植的植物
            state = State.DIE;//土豆状态变为死亡
        } else if (state == State.DIE) {
            bombTimer.stop();
        }
    }

}
