package Game;

import javax.swing.*;

//太阳面板类
public class SunLabel extends JLabel {

    int total;//太阳值

    //构造方法
    public SunLabel(JLayeredPane layeredPane) {
        total = 100;
        this.setVisible(true);
        this.setText(Integer.toString(total));//设置初始阳光值
        this.setSize(100, 100);
        this.setLocation(28, 25);
        layeredPane.add(this);//加入此标签到面板
        layeredPane.setLayer(this, 3);//设置标签为第三层
    }

    //得到当前太阳值
    public int getCurrentSun() {
        return total;
    }

    //当前太阳值
    public void addCurrentSun(int x) {
        total += x;
        this.setText(Integer.toString(total));
    }
}
