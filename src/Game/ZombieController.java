package Game;

import Zombie.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ZombieController {
    int producedZombies = 0;//产出的僵尸的数量
    Timer zombieProducerTimer;//生产僵尸计时器
    Timer zombieStateCheckTimer;//僵尸状态检测计时器
    Timer attackTimer;//僵尸攻击计时器
    Timer gameWinCheckTimer;//胜利检测计时器
    GamePlay gamePlay;//gameplay的引用
    Random yRandom;//僵尸出现的行的随机数
    Random kindOfZombie;//产生僵尸种类的随机数
    boolean hasFirstZombie = false;//是否出现第一个僵尸
    boolean hasZombieEating = false;//是否有僵尸在攻击，用于调用攻击音效
    //每一行设置一个僵尸数组
    ArrayList<Zombie> zombieArrayList1;
    ArrayList<Zombie> zombieArrayList2;
    ArrayList<Zombie> zombieArrayList3;
    ArrayList<Zombie> zombieArrayList4;
    ArrayList<Zombie> zombieArrayList5;
    Sound zombieEating = new Sound(this.getClass().getResource("/Music/zombieEat.wav"), true);//僵尸攻击的音效
    JLabel waveImage;
    Timer closeImage;

    ZombieController(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
        yRandom = new Random();
        kindOfZombie = new Random();
        //初始化僵尸数组
        zombieArrayList1 = new ArrayList();
        zombieArrayList2 = new ArrayList();
        zombieArrayList3 = new ArrayList();
        zombieArrayList4 = new ArrayList();
        zombieArrayList5 = new ArrayList();
        zombieEating.start();//开启线程
        zombieProducerTimer = new Timer(10000, (ActionEvent) -> nextZombie());//设置僵尸出现的间隔时间
        zombieStateCheckTimer = new Timer(10, (ActionEvent) -> zombieStateCheck());//检测僵尸状态
        attackTimer = new Timer(800, (ActionEvent) -> attack());//僵尸攻击
        gameWinCheckTimer = new Timer(10000, (AboutEvent) -> gameWinCheck());//胜利检测，在僵尸达到50时开启
        zombieProducerTimer.start();
        zombieStateCheckTimer.start();
        attackTimer.start();
    }

    //下一个僵尸出现
    public void nextZombie() {
        //如果产出的僵尸达到50，则不再产生僵尸，启动胜利检测
        if (producedZombies == 50) {
            gameWinCheckTimer.start();
            zombieProducerTimer.stop();//关闭产出僵尸计时器
            return;
        }
        int y = yRandom.nextInt(5);//0到5的随机数
        //第一个僵尸没有出现
        if (!hasFirstZombie) {
            Sound zombieComing = new Sound(this.getClass().getResource("/Music/zombieComing.wav"), false);//僵尸进场的旋律
            zombieComing.start();
            hasFirstZombie = true;//保证if语句只用一次
            getZombieArrayList(y + 1).add(new FlagZombie(1400, 40 + 100 * y, gamePlay));//第一个为旗帜僵尸
            producedZombies++;
        } else {
            if (producedZombies <= 10) {
                //产出的僵尸小于15个，则只产出普通僵尸
                getZombieArrayList(y + 1).add(new NormalZombie(1400, 40 + 100 * y, gamePlay));
                producedZombies++;
            } else if (producedZombies <= 30) {
                //初始化一大波僵尸图片和音效
                if(producedZombies == 11){
                    waveImage = new JLabel();
                    waveImage.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/hugeWave.gif")));
                    waveImage.setSize(286,34);
                    waveImage.setLocation(450,200);
                    waveImage.setVisible(true);
                    waveImage.setLayout(null);
                    gamePlay.add(waveImage);//加入一大波僵尸图片
                    gamePlay.setLayer(waveImage,5);//设置准备图片在第三层
                    closeImage = new Timer(3000,action->closeImage());
                    closeImage.start();
                    Sound hugeWave = new Sound(this.getClass().getResource("/Music/hugeWave.wav"),false);//一大波僵尸的音效
                    hugeWave.start();
                }
                //产出的僵尸大于15个且小于30个，则产出普通僵尸和路障僵尸，路障僵尸概率为1/3
                zombieProducerTimer.setDelay(6000);//加快僵尸产出的速度
                int zombieKind = kindOfZombie.nextInt(2);
                switch (zombieKind) {
                    case 0://普通僵尸
                        getZombieArrayList(y + 1).add(new NormalZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                        break;
                    default://路障僵尸
                        getZombieArrayList(y + 1).add(new ConeheadZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                }
            } else {
                //初始化最后波僵尸图片和音效
                if(producedZombies == 31){
                    waveImage = new JLabel();
                    waveImage.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/finalWave.png")));
                    waveImage.setSize(246,67);
                    waveImage.setLocation(450,200);
                    waveImage.setVisible(true);
                    waveImage.setLayout(null);
                    gamePlay.add(waveImage);//加入一大波僵尸图片
                    gamePlay.setLayer(waveImage,5);//设置准备图片在第三层
                    closeImage.restart();//计时器重启
                    Sound finalWave = new Sound(this.getClass().getResource("/Music/finalWave.wav"),false);//最后一波僵尸的音效
                    finalWave.start();
                }
                //产出的僵尸大于30个，则产出普通僵尸、路障僵尸和铁桶僵尸，铁桶僵尸概率为7/10
                zombieProducerTimer.setDelay(3000);//加快僵尸产出的速度
                int zombieKind = kindOfZombie.nextInt(9);
                switch (zombieKind) {
                    case 0://普通僵尸
                        getZombieArrayList(y + 1).add(new NormalZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                        break;
                    case 1:
                    case 2://路障僵尸
                        getZombieArrayList(y + 1).add(new ConeheadZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                        break;
                    default://铁桶僵尸
                        getZombieArrayList(y + 1).add(new BucketheadZombie(1400, 40 + 100 * y, gamePlay));
                        producedZombies++;
                }
            }
        }
    }

    //僵尸状态检测
    public void zombieStateCheck() {
        hasZombieEating = false;//初始为没有僵尸在攻击
        //遍历僵尸数组
        for (int i = 1; i <= 5; i++) {
            ArrayList<Zombie> zombieArrayList = getZombieArrayList(i);
            Iterator iterator = zombieArrayList.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = (Zombie) iterator.next();
                if (zombie.life <= 0) {
                    zombie.setSpeed(2);
                    zombie.setState(Zombie.State.DEAD);//设置僵尸死亡
                    iterator.remove();//从数组移除对象
                    gamePlay.remove(zombie);//清除僵尸图片
                    System.gc();//清除对象
                } else if (
                    //设置僵尸攻击状态的条件 1）在草坪上 2）草地有植物 3）距离合适
                        zombie.getLabelX() < 970 &&
                                zombie.getLabelX() > 260 &&
                                gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).isAssigned() &&
                                (zombie.getLabelX() - gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).getX()) < 30 &&
                                (zombie.getLabelX() - gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).getX()) > 0
                ) {
                    zombie.setState(Zombie.State.ATTACK);//设置僵尸状态为攻击
                    zombie.getAdvanceTimer().stop();//停止僵尸前进
                    hasZombieEating = true;//设置有僵尸在攻击
                } else if (zombie.getLabelX() <= 210) {//僵尸走出草坪，游戏结束
                    zombieStateCheckTimer.stop();//关闭计时器
                    gamePlay.getBGM().stop();//关闭BGM
                    GameWindow.gameEnd();//切换游戏结束的窗口
                } else {
                    //其余情况则为僵尸前进
                    zombie.setState(Zombie.State.ADVANCE);
                    zombie.getAdvanceTimer().start();//僵尸前进
                }
            }
        }
        //控制僵尸攻击的音效
        if (hasZombieEating) {
            if (zombieEating.isStop != false) {
                zombieEating.setStop(false);//开启音效
            }
        } else {
            if (zombieEating.isStop != true)
                zombieEating.setStop(true);//关闭音效
        }
    }

    //僵尸攻击的方法
    public void attack() {
        //遍历每一行僵尸数组
        for (int i = 1; i <= 5; i++) {
            ArrayList<Zombie> zombieArrayList = getZombieArrayList(i);
            Iterator iterator = zombieArrayList.iterator();
            while (iterator.hasNext()) {
                Zombie zombie = (Zombie) iterator.next();
                //如果僵尸正在攻击
                if (zombie.getState() == Zombie.State.ATTACK) {
                    //如果草地种有植物
                    if (gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).isAssigned()) {
                        gamePlay.getGrass(zombie.getLabelX(), zombie.getLabelY() + 50).loseLife();//获得所在草地的对象，减少植物生命值
                    }
                }
            }
        }
    }

    //检测某一行的草坪是否有僵尸
    public boolean isRowHasZombie(int row) {
        Iterator iterator = getZombieArrayList(row).iterator();
        while (iterator.hasNext()) {
            Zombie zombie = (Zombie) iterator.next();
            //僵尸在草坪坐标内
            if (zombie.getLabelX() < 1000) {
                return true;
            }
        }
        return false;
    }

    //胜利检测
    public void gameWinCheck() {
        for (int i = 1; i <= 5; i++) {
            //如果某一行有僵尸，则直接返回
            if (isRowHasZombie(i)) {
                return;
            }
        }
        gamePlay.getBGM().stop();//关闭BGM
        gameWinCheckTimer.stop();//胜利检测计时器关闭
        GameWindow.gameWin();//切换游戏胜利的窗口
    }

    //关闭wave图片
    public void closeImage(){
        gamePlay.remove(waveImage);
        gamePlay.repaint();
        closeImage.stop();
    }
    //getter
    public ArrayList<Zombie> getZombieArrayList(int row) {
        if (row == 1) return zombieArrayList1;
        else if (row == 2) return zombieArrayList2;
        else if (row == 3) return zombieArrayList3;
        else if (row == 4) return zombieArrayList4;
        else if (row == 5) return zombieArrayList5;
        else return null;
    }
}
