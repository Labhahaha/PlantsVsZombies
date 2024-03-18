package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Win extends JPanel implements MouseListener {
    //构造方法
    public Win() {
        this.setSize(845, 950);
        this.setVisible(true);
        this.setLayout(null);//取消默认布局
        Sound gameWin = new Sound(this.getClass().getResource("/Music/gameWin.wav"), false);
        gameWin.start();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/PlayerWin.jpg")).getImage(), 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameWindow.selectModel();
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