package Plant;

import Game.GamePlay;

import javax.swing.*;
import java.awt.*;

//植物类
public abstract class Plant extends JPanel {

    protected int WIDTH;//植物图片的高度和宽度
    protected int HEIGHT;
    public int sunUse;//种植物需要的阳光
    public int life;//植物生命值
    GamePlay gamePlay;
    Timer redrawTimer;//重画计时器
    protected Image plantImage;
    int px;//用以修正图片位置的变量
    int py;
    //构造方法
    public Plant(int width, int height, int sunUse, GamePlay gamePlay) {
        px = 0;
        py = 0;
        this.gamePlay = gamePlay;
        this.sunUse = sunUse;
        WIDTH = width;
        HEIGHT = height;
        this.setSize(WIDTH, HEIGHT);
        this.setOpaque(false);//设置边框为透明
        this.setVisible(true);
        redrawTimer = new Timer(25, (ActionEvent) -> repaint());
        redrawTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(plantImage, px, py, null);
    }

    //getter
    public GamePlay getGamePlay() {
        return gamePlay;
    }
    public int getSunUse() {
        return sunUse;
    }
}
