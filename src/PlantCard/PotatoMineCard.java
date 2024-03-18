package PlantCard;

import Game.GamePlay;
import Game.Sound;
import Plant.CherryBomb;
import Plant.Peashooter;
import Plant.PotatoMine;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PotatoMineCard extends PlantCard{
    //构造方法
    public PotatoMineCard(int X , int Y , JLayeredPane layeredPane){
        super(X , Y , layeredPane);
        super.PlantCardImage = new ImageIcon(this.getClass().getResource("/Image/PlantCardImage/PotatoMineCard.gif"));
        super.setIcon(PlantCardImage);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.getX()+e.getX()>260 && this.getY()+e.getY() > 90 && this.getX()+e.getX()< 260+80*9 && this.getY()+e.getY()< 90+ 100*5){
            GamePlay gamePlay = (GamePlay) layeredPane;
            if(!gamePlay.getGrass(this.getX()+e.getX(),this.getY()+e.getY()).isAssigned() && gamePlay.getSunLabel().getCurrentSun() >= 25){
                gamePlay.getGrass(this.getX()+e.getX(),this.getY()+e.getY()).setAssignedPlant(new PotatoMine((GamePlay) layeredPane));
            }
        }
        this.setLocation(super.X,super.Y);
    }
}
