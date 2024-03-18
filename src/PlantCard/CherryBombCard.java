package PlantCard;

import Game.GamePlay;
import Game.Sound;
import Plant.CherryBomb;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class CherryBombCard extends PlantCard{
    //构造方法
    public CherryBombCard(int X , int Y , JLayeredPane layeredPane){
        super(X , Y , layeredPane);
        super.PlantCardImage = new ImageIcon(this.getClass().getResource("/Image/PlantCardImage/CherryBombCard.png"));
        super.setIcon(PlantCardImage);
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.getX()+e.getX()>260 && this.getY()+e.getY() > 90 && this.getX()+e.getX()< 260+80*9 && this.getY()+e.getY()< 90+ 100*5){
            GamePlay gamePlay = (GamePlay) layeredPane;
            if(!gamePlay.getGrass(this.getX()+e.getX(),this.getY()+e.getY()).isAssigned() && gamePlay.getSunLabel().getCurrentSun() >= 150){
                gamePlay.getGrass(this.getX()+e.getX(),this.getY()+e.getY()).setAssignedPlant(new CherryBomb((GamePlay) layeredPane));
            }
        }
        this.setLocation(super.X,super.Y);
    }
}
