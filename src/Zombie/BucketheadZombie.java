package Zombie;

import javax.swing.*;

public class BucketheadZombie extends Zombie{
    //构造方法
    public BucketheadZombie(int LabelX, int LabelY, JLayeredPane layeredPane) {
        super(LabelX, LabelY, 90, 145, layeredPane);
        px0 = -52;//修正参数
        py0 = -5;
        pw = -10;
        ly = -15;
        super.life = 40;//铁桶僵尸生命值
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/BucketheadZombieImage/BucketheadZombie.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/BucketheadZombieImage/BucketheadZombieAttack.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/BucketheadZombieImage/frozenBucketheadZombie.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/BucketheadZombieImage/frozenBucketheadZombieAttack.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieHead.gif")).getImage();
    }
}
