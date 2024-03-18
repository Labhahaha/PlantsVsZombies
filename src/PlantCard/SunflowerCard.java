package PlantCard;
import javax.swing.*;
import java.awt.event.MouseEvent;
import Plant.*;
import Game.*;

public class SunflowerCard extends PlantCard {

    public SunflowerCard(int X , int Y , JLayeredPane layeredPane){
        super(X , Y , layeredPane);
        super.PlantCardImage = new ImageIcon(this.getClass().getResource("/Image/PlantCardImage/SunflowerCard.jpg"));
        super.setIcon(PlantCardImage);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.getX()+e.getX()>260 && this.getY()+e.getY() > 90 && this.getX()+e.getX()< 260+80*9 && this.getY()+e.getY()< 90+ 100*5){
            GamePlay gamePlay = (GamePlay) layeredPane;
            if(!gamePlay.getGrass(this.getX()+e.getX(),this.getY()+e.getY()).isAssigned() && gamePlay.getSunLabel().getCurrentSun() >= 50){
                gamePlay.getGrass(this.getX()+e.getX(),this.getY()+e.getY()).setAssignedPlant(new Sunflower((GamePlay) layeredPane));
            }
        }
        this.setLocation(super.X,super.Y);
    }
}
