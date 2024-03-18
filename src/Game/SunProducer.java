package Game;

import javax.swing.*;
import java.util.Random;

//阳光生产类
public class SunProducer {
    Timer sunProduceTimer;//产阳光计时器
    JLayeredPane layeredPane;
    Random xRandom;

    //构造方法
    public SunProducer(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        sunProduceTimer = new Timer(10000, (ActionEvent) -> produceSun());//设置10秒产一个阳光
        xRandom = new Random();
        sunProduceTimer.start();
    }

    //产出阳光的位置
    public void produceSun() {
        int x = xRandom.nextInt(1000);//横坐标为0到1000的随机数
        if (x < 260)
            x += 260;//保证阳光产生在草坪区域
        new Sun(x, 0, layeredPane);
    }
}
