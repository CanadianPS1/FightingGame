package FightingGame.Model;
import FightingGame.View.MainMenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
public class VictoryScreen extends FightingMap implements ActionListener{
    private final ImageIcon player1WinsIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\playerOneWins.png");
    private final ImageIcon player2WinsIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\playerTwoWins.png");
    private final ImageIcon backButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\backButton.png");
    private final JLabel player1Wins, player2Wins;
    private final Random rand = new Random();
    private final JButton backButton;
    public VictoryScreen(){
        player1Wins = new MainMenuView().lableMaker(250, 100, 500, 500, player1WinsIcon);
        player2Wins = new MainMenuView().lableMaker(250, 100, 500, 500, player2WinsIcon);
        backButton = new MainMenuView().buttonMaker(250, 550, 500, 100, backButtonIcon);
        backButton.addActionListener((ActionListener)this);
        player1Wins.setVisible(false);
        player2Wins.setVisible(false);
        FightingMap.game.remove(FightingMap.battleField);
        FightingMap.game.add(backButton);
        FightingMap.game.add(player2Wins);
        FightingMap.game.add(FightingMap.battleField);
        FightingMap.game.update(FightingMap.game.getGraphics());
        if(p1Health > p2Health) player1Wins.setVisible(true);
        else if(p2Health > p1Health) player2Wins.setVisible(true);
        else{
            if(rand.nextInt(2) == 0) player1Wins.setVisible(true);              
            else player2Wins.setVisible(true);               
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            battleMusic.stop();
            FightingMap.game.remove(FightingMap.battleField);
            FightingMap.game.remove(FightingMap.player1CharacterText);
            FightingMap.game.remove(FightingMap.player2CharacterText);
            FightingMap.game.remove(FightingMap.p1HealthBar);
            FightingMap.game.remove(FightingMap.p2HealthBar);
            FightingMap.game.remove(FightingMap.time);
            FightingMap.game.remove(FightingMap.timerBackground);
            FightingMap.game.remove(backButton);
            FightingMap.game.remove(FightingMap.p1CharacterLabel);
            FightingMap.game.remove(FightingMap.p2CharacterLabel);
            player1Wins.setVisible(false);
            player2Wins.setVisible(false);
            FightingMap.p1Health = 200;
            FightingMap.p2Health = 200;
            FightingMap.setCanMove(true);
            new MainMenu();
        }
    }
}
