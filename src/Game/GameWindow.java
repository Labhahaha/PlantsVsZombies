package Game;

import javax.swing.*;

//游戏开始执行类
public class GameWindow {

    public static JFrame gameWindow;//创建JFrame的静态引用

    //main方法
    public static void main(String[] args) {
        selectModel();
    }

    //开始界面，墓碑窗口
    public static void selectModel() {
        if (gameWindow != null) gameWindow.dispose();//返回此界面时，清除之前的窗口
        gameWindow = new JFrame("PlantsVsZombies");
        GamePlay gamePlay = new GamePlay();//调用第一个GamePlay构造方法，创建实例
        gameWindow.setIconImage(gamePlay.imageIcon.getImage());//设置窗口图标
        gameWindow.add(gamePlay);//添加到窗口中
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(916, 637);
        gameWindow.setVisible(true);//设置窗口可见
        gameWindow.setResizable(false);//设置窗口大小不可调整
    }

    //游戏界面窗口
    public static void startPlay() {
        gameWindow.dispose();//清除墓碑界面
        System.gc();
        gameWindow = new JFrame("PlantsVsZombies");
        GamePlay gamePlay = new GamePlay(true);//调用第二个GamePlay构造方法,创建实例
        gameWindow.setIconImage(gamePlay.imageIcon.getImage());//设置窗口图标
        gameWindow.add(gamePlay);//添加到窗口中
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(1400, 637);
        gameWindow.setVisible(true);//设置窗口可见
        gameWindow.setResizable(false);//设置窗口大小不可调整
    }

    //游戏失败+音乐
    public static void gameEnd() {
        gameWindow.dispose();
        System.gc();
        gameWindow = new JFrame("PlantsVsZombies");
        gameWindow.setSize(1370, 620);
        gameWindow.setVisible(true);//设置窗口可见
        gameWindow.setResizable(false);//设置窗口大小不可调整
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePlay gamePlay = new GamePlay(1);//调用第三个GamePlay构造方法
        gameWindow.setIconImage(gamePlay.imageIcon.getImage());//设置图标
        gameWindow.add(gamePlay);
    }

    //游戏胜利+音乐
    public static void gameWin() {
        gameWindow.dispose();
        System.gc();
        gameWindow = new JFrame("PlantsVsZombies");
        gameWindow.setSize(845, 950);
        gameWindow.setVisible(true);//设置窗口可见
        gameWindow.setResizable(false);//设置窗口大小不可调整
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePlay gamePlay = new GamePlay(0.1);//调用第四个GamePlay构造方法
        gameWindow.setIconImage(gamePlay.imageIcon.getImage());//设置图标
        gameWindow.add(gamePlay);
    }
}
