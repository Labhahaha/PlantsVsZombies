package Zombie;

import javax.swing.*;

public class FlagZombie extends Zombie {
    //构造方法
    public FlagZombie(int LabelX, int LabelY, JLayeredPane layeredPane) {
        super(LabelX, LabelY, 110, 138, layeredPane);
        px0 = -58;//修正参数
        py0 = 0;
        ly = -15;
        pw = -12;
        super.life = 10;//旗帜僵尸生命值
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/FlagZombieImage/FlagZombie.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/FlagZombieImage/FlagZombieAttack.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/FlagZombieImage/frozenFlagZombie.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/FlagZombieImage/frozenFlagZombieAttack.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieHead.gif")).getImage();
    }
}

