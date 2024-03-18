package Plant;

import Game.GamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallNut extends Plant{
    Timer stateChangeTimer;
    int counter;
    public WallNut(GamePlay gamePlay){
        super(70,80,50 , gamePlay);
        px = 6;
        py = 8;
        counter = 0;
        life = 72;
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/WallNut.gif")).getImage();
        stateChangeTimer = new Timer(4000,(ActionEvent)->stateChange());
        stateChangeTimer.start();
    }


    public void stateChange(){
        if(life < 48 && life > 24 && counter == 0){
            counter++;
            setSize(70,70);
            py = -4;
            plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/WallNut_cracked1.gif")).getImage();
            repaint();
        }
        else if(life < 24){
            py = -8;
            setSize(70,70);
            plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/WallNut_cracked2.gif")).getImage();
            repaint();
            stateChangeTimer.stop();
        }
    }
}
