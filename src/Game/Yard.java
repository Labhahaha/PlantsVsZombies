package Game;

import javax.swing.*;
import java.awt.*;

//园子类
public class Yard extends JPanel {
    //园子类的构造方法
    public Yard() {
        this.setSize(1400, 637);
        this.setVisible(true);
        this.setLayout(null);//取消默认布局
    }

    //画园子
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/yard.jpg")).getImage(),0,0,null);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/sunBoard.png")).getImage(),0,0,null);
        g.drawImage(new ImageIcon(this.getClass().getResource("/BackgroundImage/ShovelBack.png")).getImage(),600,0,null);
    }
}
