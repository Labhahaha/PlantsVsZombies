package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//阳光类
public class Sun extends JLabel implements MouseListener {
    int X;
    int Y;
    int ySpeed = 10;//下落速度
    JLayeredPane layeredPane;
    Timer dropTimer;//阳光下落计时器

    //构造方法
    public Sun(int x, int y, JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        this.X = x;
        this.Y = y;
        dropTimer = new Timer(25, (ActionEvent) -> drop());//设置计时器
        this.addMouseListener(this);
        this.setIcon(new ImageIcon(this.getClass().getResource("/BackgroundImage/Sun.gif")));
        this.setSize(72, 65);
        this.setVisible(true);
        this.setLocation(X, Y);
        layeredPane.add(this);
        layeredPane.setLayer(this, 4);//设置阳光图片在第4层
        dropTimer.start();
    }

    //阳光下落
    public void drop() {
        if (Y < 500) {
            Y += 2;//每次下落2个坐标
            this.setLocation(X, Y);
        }
    }

    //getter
    public Timer getDropTimer() {
        return dropTimer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        layeredPane.remove(this);//点击后移除植物图片
        GamePlay gamePlay = (GamePlay) layeredPane;
        gamePlay.getSunLabel().addCurrentSun(25);//增加当前阳光值
        layeredPane.repaint();
        Sound clickSun = new Sound(this.getClass().getResource("/Music/clickSun.wav"), false);//加入点击阳光的音效
        clickSun.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
