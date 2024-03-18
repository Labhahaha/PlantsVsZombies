package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//菜单类
public class Menu extends JPanel implements MouseListener {
    //菜单类构造方法
    public Menu() {
        this.setSize(916, 637);
        this.setVisible(true);
        this.setLayout(null);//取消默认布局
        addMouseListener(this);//加入鼠标监听
    }

    // 画菜单
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/menu.png")).getImage(), 0, 0, null);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/start.png")).getImage(), 470, 90, null);
    }

    @Override
    //点击指定区域即可开始游戏
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 470 && e.getX() < 750 && e.getY() > 90 && e.getY() < 240) {
            GameWindow.startPlay();//调用startPlay方法，加载游戏界面
        }
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
