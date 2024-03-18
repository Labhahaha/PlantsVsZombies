package Game;

import PlantCard.*;

import javax.swing.*;

//游戏进行主类
public class GamePlay extends JLayeredPane {
    ImageIcon imageIcon;//程序图标
    JLabel preImage;//准备安放植物的图片
    Grass[][] grasses;//草地二维数组
    Shovel shovel;//铲子
    SunProducer sunProducer;//阳光生产
    SunLabel sunLabel;//太阳面板
    ZombieController zombieController;//僵尸控制类
    BGM bgm;//背景音乐
    Timer closePreImageTimer;//关闭图片计时器

    //第一个构造方法，初始化墓碑界面
    public GamePlay() {
        //设置面板
        this.setSize(916, 637);
        this.setVisible(true);
        this.add(new Menu());//加入Menu面板
        imageIcon = new ImageIcon(this.getClass().getResource("/BackgroundImage/IconImage.png"));//创建图标
    }

    //第二个构造方法，初始化游戏进行界面
    public GamePlay(Boolean b) {
        this.setSize(1400, 637);
        this.setVisible(true);
        imageIcon = new ImageIcon(this.getClass().getResource("/BackgroundImage/IconImage.png"));//创建图标
        Yard yard = new Yard();//初始化园子
        this.add(yard);//加入yard面板
        this.setLayer(yard, 0);//将园子设置在第0层

        //初始化准备图片
        preImage = new JLabel();
        preImage.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/PrepareGrowPlants.png")));
        preImage.setSize(255,324);
        preImage.setLocation(450,120);
        preImage.setVisible(true);
        preImage.setLayout(null);
        this.add(preImage);//加入准备图片
        this.setLayer(preImage,3);//设置准备图片在第三层

        grassesInit();
        shovelInit();
        sunProducerInit();
        sunLabelInit();
        plantCardInit();
        zombieControllerInit();
        bgm = new BGM();//调用BGM，播放音乐
        bgm.start();
        closePreImageTimer = new Timer(3000, (ActionEvent) -> closePreImage());//设置关闭图片的计时器
        closePreImageTimer.start();
    }

    //第三个构造方法，游戏失败
    public GamePlay(int x) {
        this.setSize(1400, 637);
        this.setVisible(true);
        End end = new End();//初始化结束面板
        this.add(end);
        this.setLayer(end, 0);//将结束面板设置在第0层
        imageIcon = new ImageIcon(this.getClass().getResource("/BackgroundImage/IconImage.png"));//获取图标
    }

    //第四个构造方法，游戏胜利
    public GamePlay(double x) {
        this.setSize(1400, 637);
        this.setVisible(true);
        Win win = new Win();//初始化结束面板
        this.add(win);
        this.setLayer(win, 0);//将结束面板设置在第0层
        imageIcon = new ImageIcon(this.getClass().getResource("/BackgroundImage/IconImage.png"));//获取图标
    }


    //初始化草地类
    public void grassesInit() {
        grasses = new Grass[5][9];//草地方格为5行9列
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                grasses[i][j] = new Grass(i, j, this);
            }
        }//为每个元素初始化草地类
    }

    //初始化铲子
    public void shovelInit() {
        this.shovel = new Shovel(600, 0, this);
    }

    //初始化阳光生产类
    public void sunProducerInit() {
        this.sunProducer = new SunProducer(this);
    }

    //初始化太阳面板类
    public void sunLabelInit() {
        this.sunLabel = new SunLabel(this);
    }

    //初始化植物卡片类
    public void plantCardInit() {
        new PeaShooterCard(80, 8, this);
        new SunflowerCard(130, 8, this);
        new WallNutCard(180, 8, this);
        new SnowPeaShooterCard(230, 8, this);
        new PotatoMineCard(280, 8, this);
        new CherryBombCard(330, 8, this);
        new ChomperCard(380,8,this);
        Sound sound = new Sound(this.getClass().getResource("/Music/readysetplant.wav"), false);
        sound.start();
    }

    //初始化僵尸控制类
    public void zombieControllerInit() {
        zombieController = new ZombieController(this);
    }

    //判断一行上是否有僵尸
    public boolean isRowHasZombie(int row) {
        return zombieController.isRowHasZombie(row);
    }

    //移除“准备安放植物图片”
    public void closePreImage() {
        this.remove(preImage);
        this.repaint();
        closePreImageTimer.stop();
    }
    //Getters
    public BGM getBGM() {
        return bgm;
    }

    //根据坐标得到草地的位置（行列值）
    public Grass getGrass(int x, int y) {
        return grasses[(y - 90) / 100][(x - 260) / 80];
    }

    public ZombieController getZombieController() {
        return zombieController;
    }

    public SunLabel getSunLabel() {
        return sunLabel;
    }

}

