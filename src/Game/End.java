package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class End extends JPanel implements MouseListener {

    public End() {
        this.setSize(1400, 637);
        this.setVisible(true);
        this.setLayout(null);//取消默认布局
        Sound sound = new Sound(this.getClass().getResource("/Music/gameOver.wav"), false);
        sound.start();
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/ZombiesWon.jpg")).getImage(), 0, 0, null);
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
