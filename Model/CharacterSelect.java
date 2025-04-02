package FightingGame.Model;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import FightingGame.View.MainMenuView;
public class CharacterSelect implements KeyListener, ActionListener{
    private ImageIcon etSelectIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\etSelectImage.png");
    private ImageIcon gonSelectIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\gonSelectImage.png");
    private ImageIcon p1Unselected = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\p1Unselected.png");
    private ImageIcon p1Controller = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\p1Controller.png");
    private ImageIcon p1Keyboard = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\p1Keyboard.png");
    private ImageIcon p2Unselected = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\p2Unselected.png");
    private ImageIcon p2Controller = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\p2Controller.png");
    private ImageIcon p2Keyboard = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\p2Keyboard.png");
    private ImageIcon playButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\playButton.png");
    private ImageIcon etTextIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\etText.png");
    private ImageIcon gonTextIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\gonText.png");
    private ImageIcon backButtonIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\backButton.png");
    private final String selectionMusicPath = "FightingGame\\Assets\\Sounds\\Music\\CharacterSelect.wav";
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private JLabel etSelect, gonSelect, p1Label, p2Label, background, p1Text, p2Text;
    private int p1ControllType, p2ControllType, p1Character, p2Character;
    private boolean playButtonClickuble, picking;
    private JButton playButton, backButton;
    private int[] characters = new int[2];
    private final Clip selectionMusic;
    private JFrame game;    
    public CharacterSelect(JFrame g, JLabel b){
        game = g;
        background = b;
        playButtonClickuble = false;
        picking = true;
        game.addKeyListener(this);
        game.setFocusable(true);
        game.requestFocus();
        SoundHandler soundHandler = new SoundHandler();
        selectionMusic = soundHandler.soundControl(selectionMusicPath, true);
        selectionMusic.loop(Clip.LOOP_CONTINUOUSLY);
        selectionMusic.start();
        p1ControllType = 0;
        p2ControllType = 0;
        etSelect = new MainMenuView().lableMaker(100, 50, 300, 300, etSelectIcon);
        gonSelect = new MainMenuView().lableMaker(600, 50, 300, 300, gonSelectIcon);
        p1Label = new MainMenuView().lableMaker(180, 400, 150, 100, p1Unselected);
        p2Label = new MainMenuView().lableMaker(680, 400, 150, 100, p2Unselected);
        p2Text = new MainMenuView().lableMaker(780, 550, 150, 100, gonTextIcon);
        p1Text = new MainMenuView().lableMaker(80, 550, 150, 100, etTextIcon);
        playButton = new MainMenuView().buttonMaker(425, 150, 150,100, playButtonIcon);
        backButton = new MainMenuView().buttonMaker(250, 550, 500, 100, backButtonIcon);
        playButton.addActionListener((ActionListener)this);
        backButton.addActionListener((ActionListener)this);
        game.remove(background);
        game.add(backButton);
        game.add(p1Text);
        game.add(p2Text);
        game.add(p1Label);
        game.add(p2Label);
        game.add(etSelect);
        game.add(gonSelect);
        game.add(background);
        game.update(game.getGraphics());
        characters[0] = 1;
        characters[1] = 2;
        p1Character = characters[0];
        p2Character = characters[1];
        Runnable playerControllTypeChecker = () -> {
            if(picking){
                if(p1ControllType == 1) p1Label.setIcon(p1Controller);
                if(p1ControllType == 2) p1Label.setIcon(p1Keyboard);
                if(p2ControllType == 1) p2Label.setIcon(p2Controller);
                if(p2ControllType == 2) p2Label.setIcon(p2Keyboard);
                if(p1ControllType != 0 && p2ControllType != 0 && !playButtonClickuble){
                    game.remove(background);
                    game.add(playButton);
                    game.add(background);
                    playButtonClickuble = true;
                    game.update(game.getGraphics());
                }
                if(p1Character == 1){
                    p1Text.setIcon(etTextIcon);
                    etSelect.setIcon(etSelectIcon);
                }else if(p1Character == 2){
                    p1Text.setIcon(gonTextIcon);
                    etSelect.setIcon(gonSelectIcon);
                }
                if(p2Character == 1){
                    p2Text.setIcon(etTextIcon);
                    gonSelect.setIcon(etSelectIcon);
                }else if(p2Character == 2){
                    p2Text.setIcon(gonTextIcon);
                    gonSelect.setIcon(gonSelectIcon);
                }
            }
        };
        scheduler.scheduleAtFixedRate(playerControllTypeChecker, 0, 300, TimeUnit.MILLISECONDS);
        Thread playerControllTypeCheckerThread = new Thread(playerControllTypeChecker);
        playerControllTypeCheckerThread.start();
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        //checks for controller
        if(e.getKeyCode() == KeyEvent.VK_F1){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F2){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F3){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F4){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F5){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F6){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F7){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }if(e.getKeyCode() == KeyEvent.VK_F8){
            if(p1ControllType == 0) p1ControllType = 1;
            else if(p1ControllType != 1) p2ControllType = 1;
        }
        //checks for keyboard
        if(e.getKeyCode() == KeyEvent.VK_W){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_A){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_S){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_D){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_Y){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_U){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_K){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }if(e.getKeyCode() == KeyEvent.VK_H){
            if(p1ControllType == 0) p1ControllType = 2;
            else if(p1ControllType != 2) p2ControllType = 2;
        }
        if(p1ControllType == 2){
            if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D){
                if(p1Character == 1) p1Character = 2;
                else p1Character = 1; 
            }
        }else{
            if(e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_F2){
                if(p1Character == 1) p1Character = 2;
                else p1Character = 1;
            }
        }
        if(p2ControllType == 2){
            if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D){
                if(p2Character == 1) p2Character = 2;
                else p2Character = 1;
            }
        }else{
            if(e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_F2){
                if(p2Character == 1) p2Character = 2;
                else p2Character = 1;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == playButton){
            selectionMusic.stop();
            game.remove(background);
            game.remove(p1Label);
            game.remove(p2Label);
            game.remove(playButton);
            game.remove(etSelect);
            game.remove(gonSelect);
            game.remove(p1Text);
            game.remove(p2Text);
            game.remove(backButton);
            game.update(game.getGraphics());
            new FightingMap(game,p1Character,p2Character,p1ControllType,p2ControllType);
        }else if(e.getSource() == backButton){
            selectionMusic.stop();
            game.remove(background);
            game.remove(p1Label);
            game.remove(p2Label);
            game.remove(playButton);
            game.remove(etSelect);
            game.remove(gonSelect);
            game.remove(p1Text);
            game.remove(p2Text);
            game.remove(backButton);
            game.update(game.getGraphics());
            new MainMenu();
        }
    }
}
