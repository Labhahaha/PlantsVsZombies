package Zombie;

import javax.swing.*;

public class ConeheadZombie extends Zombie {
    //构造方法
    public ConeheadZombie(int LabelX, int LabelY, JLayeredPane layeredPane) {
        super(LabelX, LabelY, 75, 145, layeredPane);
        super.setLocation(labelX, labelY + ly);
        px0 = -81;//修正参数
        py0 = 0;
        ly = -15;
        pw = 6;
        super.life = 20;//路障僵尸生命值
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/ConeheadZombie/ConeheadZombie.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/ConeheadZombie/ConeheadZombieAttack.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/ConeheadZombie/frozenConeheadZombie.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/ConeheadZombie/frozenConeheadZombieAttack.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieHead.gif")).getImage();
    }
}
