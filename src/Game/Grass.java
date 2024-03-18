package Game;

import Plant.Chomper;
import Plant.Peashooter;
import Plant.Plant;
import Plant.PotatoMine;

import javax.swing.*;


//草地类
public class Grass {
    //第一块草皮的初始坐标
    private int X = 260;
    private int Y = 90;
    //每块草皮的宽度和高度
    final int WIDTH = 80;
    final int HEIGHT = 100;
    private Boolean isAssigned = false;//判断草地是否被种植物
    private Plant assignedPlant = null;//草地种的是何种植物
    Timer isRowHasZombieTimer;//草地某行是否有僵尸计时器
    Timer checkStateTimer;//检查植物状态计时器
    GamePlay gamePlay;

    //草地的构造方法
    public Grass(int i, int j, GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        //设置第i行第j列草地的坐标
        X += WIDTH * j;
        Y += HEIGHT * i;
        checkStateTimer = new Timer(20, (ActionEvent) -> checkState());//开始检查植物状态
        checkStateTimer.start();
    }

    //检查植物状态
    public void checkState() {
        //判断是否种植物，如果是，且植物生命小于0，移除植物
        if (isAssigned) {
            if (assignedPlant.life <= 0) {
                removeAssignedPlant();
            }
        }
    }

    //移除当前植物
    public void removeAssignedPlant() {
        //如果是豌豆类，则设置不再射击
        if (assignedPlant instanceof Peashooter) {
            Peashooter peashooter = (Peashooter) assignedPlant;
            peashooter.setIsShooting(false);
        }
        //如果是土豆地雷，则设置计时器全部关闭
        else if (assignedPlant instanceof PotatoMine) {
            PotatoMine potatoMine = (PotatoMine) assignedPlant;
            potatoMine.timerStop();
        }
        //如果是土豆地雷，则设置计时器全部关闭
        else if (assignedPlant instanceof Chomper) {
            Chomper chomper = (Chomper) assignedPlant;
            chomper.timerStop();
        }

        gamePlay.remove(assignedPlant);//移除植物图片
        assignedPlant = null;//移除植物
        isAssigned = false;//设置无植物种植
        gamePlay.repaint();
    }


    //设置种何种植物
    public void setAssignedPlant(Plant plant) {
            assignedPlant = plant;
            isAssigned = true;
            if (assignedPlant instanceof Peashooter) {
                isRowHasZombieTimer = new Timer(25, (ActionEvent) -> setIsShooting());
                isRowHasZombieTimer.start();
            }
            gamePlay.add(assignedPlant);//加入植物图片
            if (assignedPlant instanceof PotatoMine) {
                assignedPlant.setLocation(X + 10, Y + 20);
            } else {
                assignedPlant.setLocation(X, Y);
            }
            gamePlay.setLayer(assignedPlant, 3);//设置植物图片在第3层
            gamePlay.getSunLabel().addCurrentSun(-1 * plant.sunUse);//种植物后减少当前阳光值
            Sound plantHit = new Sound(this.getClass().getResource("/Music/plantHit.wav"), false);//种植物的音效
            plantHit.start();
    }

    //减少生命值
    public void loseLife() {
        if (isAssigned) {
            assignedPlant.life--;
        }
    }

    //设置攻击状态
    public void setIsShooting() {
        if (isAssigned) {
            //此草地所在的行有僵尸
            if (assignedPlant.getGamePlay().isRowHasZombie((Y - 90) / 100 + 1)) {
                if (assignedPlant instanceof Peashooter) {
                    Peashooter peashooter = (Peashooter) assignedPlant;
                    peashooter.setIsShooting(true);//开始攻击
                }
            } else {
                if (assignedPlant instanceof Peashooter) {
                    Peashooter peashooter = (Peashooter) assignedPlant;
                    peashooter.setIsShooting(false);//关闭攻击
                }
            }
        }
    }

    //getter and setter
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

}
