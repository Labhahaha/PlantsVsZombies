package PlantCard;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//植物卡片抽象类
public abstract class PlantCard extends JLabel implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;//游戏界面面板的引用
    int X;//植物卡片的坐标
    int Y;
    int x0;//点击卡片瞬间鼠标的位置
    int y0;
    int dragCounter = 0;
    ImageIcon PlantCardImage;
    final int WIDTH = 50;//卡片的高度和宽度
    final int HEIGHT = 70;
    //构造方法
    public PlantCard(int X, int Y , JLayeredPane layeredPane){
        this.X = X;
        this.Y = Y;
        this.setSize(WIDTH,HEIGHT);
        this.setLocation(X,Y);
        this.setVisible(true);
        this.layeredPane = layeredPane;
        layeredPane.setLayer(this,3);//设置卡片图片到面板第3层
        layeredPane.add(this);//将图片加到面板
        this.addMouseListener(this);//加入鼠标监听
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    //实现卡片的拖拽
    @Override
    public void mouseDragged(MouseEvent e) {
        //获取第一次点击卡片时鼠标的位置，保证鼠标相对卡片位置不动
        if(dragCounter == 0){
            x0 = e.getX();
            y0 = e.getY();
            dragCounter++;
        }
        this.setLocation(this.getX()+e.getX()-x0 ,this.getY()+e.getY()-y0);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
