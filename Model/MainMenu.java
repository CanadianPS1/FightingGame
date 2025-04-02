package FightingGame.Model;
import FightingGame.View.MainMenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import javax.swing.*;
public class MainMenu implements ActionListener{
    private final ImageIcon startButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\startButton.png");
    private final ImageIcon comboButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\combosButton.png");
    private final ImageIcon quitButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\quitButton.png");
    private final ImageIcon backButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\backButton.png");
    private final ImageIcon etbosButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\etCombos.png");
    private final ImageIcon gonbosButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\gonCombos.png");
    private final ImageIcon titleIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\title.png");
    private final ImageIcon etIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\et.png");
    private final ImageIcon gonCon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\gon.png");
    private final ImageIcon gonCombosChart = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\gonCombosChart.png");
    private final ImageIcon etCombosChart = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\etCombosChart.png");
    private final ImageIcon mainMenuBackground = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\MainMenuBackground.png");
    private final String clickSoundPath = "FightingGame\\Assets\\Sounds\\MenuSounds\\clickSound.wav";
    private final String lobbyMusicPath = "FightingGame\\Assets\\Sounds\\Music\\MenuMusic.wav";
    private JButton gonComboButton,ETComboButton,titleBackButton, comboBackButton;
    private final JLabel title, et, gon, background;
    private JLabel gonCombos, etCombos;
    private final JButton startButton, comboButton, quitButton;
    private static JFrame game = new JFrame();
    public static boolean firstRun = true;
    private final Clip lobbyMusic;
    private Clip clickSound;
    public MainMenu(){
        if(firstRun){
            game = new MainMenuView().FrameMaker();
            firstRun = false;
        }
        SoundHandler soundHandler = new SoundHandler();
        lobbyMusic = soundHandler.soundControl(lobbyMusicPath, true);
        lobbyMusic.loop(Clip.LOOP_CONTINUOUSLY);
        lobbyMusic.start();
        startButton = new MainMenuView().buttonMaker(250, 100, 500, 100, startButtonIcon);
        comboButton = new MainMenuView().buttonMaker(250, 250, 500, 100, comboButtonIcon);
        quitButton = new MainMenuView().buttonMaker(250, 400, 500, 100, quitButtonIcon);
        title = new MainMenuView().lableMaker(250, 0, 500, 100, titleIcon);
        et = new MainMenuView().lableMaker(433, 0, 433, 577, etIcon); 
        gon = new MainMenuView().lableMaker(115, 526, 515, 484, gonCon); 
        background = new MainMenuView().lableMaker(0,0,1000,700, mainMenuBackground);
        startButton.addActionListener((ActionListener) this);
        comboButton.addActionListener((ActionListener) this);
        quitButton.addActionListener((ActionListener) this);
        game.add(startButton);
        game.add(comboButton);
        game.add(quitButton);
        game.add(title);
        game.add(et);
        game.add(gon);
        game.add(background);
        game.update(game.getGraphics());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        SoundHandler soundHandler = new SoundHandler();
        clickSound = soundHandler.soundControl(clickSoundPath, true);
        clickSound.start();
        if(e.getSource() == comboButton){
            game.remove(background);
            game.remove(startButton);
            game.remove(comboButton);
            game.remove(quitButton);
            game.remove(title);
            gonComboButton = new MainMenuView().buttonMaker(250, 100, 500, 100, gonbosButtonIcon);
            ETComboButton = new MainMenuView().buttonMaker(250, 250, 500, 100, etbosButtonIcon);
            titleBackButton = new MainMenuView().buttonMaker(250, 400, 500, 100, backButtonIcon);
            comboBackButton = new MainMenuView().buttonMaker(250, 500, 500, 100, backButtonIcon);
            gonCombos = new MainMenuView().lableMaker(0, 0, 500, 500, gonCombosChart);
            etCombos = new MainMenuView().lableMaker(0, 0, 500, 500, etCombosChart);
            gonCombos.setVisible(false);
            etCombos.setVisible(false);
            comboBackButton.setVisible(false);
            gonComboButton.addActionListener((ActionListener)this);
            ETComboButton.addActionListener((ActionListener)this);
            titleBackButton.addActionListener((ActionListener)this);
            comboBackButton.addActionListener((ActionListener)this);
            game.add(etCombos);
            game.add(gonCombos);
            game.add(comboBackButton);
            game.add(gonComboButton);
            game.add(ETComboButton);
            game.add(titleBackButton);
            game.add(background);
            game.update(game.getGraphics());
        }else if(e.getSource() == quitButton) {System.exit(0);}
        else if(e.getSource() == titleBackButton){
            game.remove(gonComboButton);
            game.remove(ETComboButton);
            game.remove(titleBackButton);
            game.remove(background);
            game.add(startButton);
            game.add(comboButton);
            game.add(quitButton);
            game.add(title);
            game.add(background);
            game.update(game.getGraphics());
        }else if(e.getSource() == gonComboButton){
            game.remove(gonComboButton);
            game.remove(ETComboButton);
            game.remove(titleBackButton);
            game.remove(background);
            game.add(background);
            gonCombos.setVisible(true);
            comboBackButton.setVisible(true);
            game.update(game.getGraphics());
        }else if(e.getSource() == ETComboButton){
            game.remove(gonComboButton);
            game.remove(ETComboButton);
            game.remove(titleBackButton);
            game.add(background);
            etCombos.setVisible(true);
            comboBackButton.setVisible(true);
            game.update(game.getGraphics());
        }else if(e.getSource() == comboBackButton){
            etCombos.setVisible(false);
            gonCombos.setVisible(false);
            comboBackButton.setVisible(false);
            game.add(comboBackButton);
            game.add(gonComboButton);
            game.add(ETComboButton);
            game.add(titleBackButton);
            game.add(etCombos);
            game.add(gonCombos);
            game.add(background);
            game.update(game.getGraphics());
        }else if(e.getSource() == startButton){
            lobbyMusic.stop();
            game.remove(gon);
            game.remove(et);
            game.remove(startButton);
            game.remove(comboButton);
            game.remove(quitButton);
            game.remove(title);
            game.update(game.getGraphics());
            new CharacterSelect(game, background);
        }
    }  
}