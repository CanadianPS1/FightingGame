package FightingGame.Model;
import FightingGame.View.MainMenuView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.Clip;
import javax.swing.*;
public class FightingMap implements KeyListener{
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final ImageIcon battleFieldIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\fightingMap.png");
    private final ImageIcon timerIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\timer.png");
    private final String battleMusicPath = "FightingGame\\Assets\\Sounds\\Music\\FightMusic.wav";
    protected static int timer, p1Health = 200, p2Health = 200, p1ControllType, p2ControllType;
    protected static JLabel p1CharacterLabel, p2CharacterLabel, battleField, timerBackground;
    protected static JTextField time, player1CharacterText, player2CharacterText;
    protected static Character player1Character, player2Character;
    private boolean victoryScreenCalled = false;
    public static boolean canMove = true;
    protected static JProgressBar p1HealthBar, p2HealthBar;
    protected static Clip battleMusic;
    protected static JFrame game;
    public FightingMap(JFrame g, int p1Character, int p2Character, int p1ct, int p2ct){
        p1ControllType = p1ct;
        p2ControllType = p2ct;
        SoundHandler soundHandler = new SoundHandler();
        battleMusic = soundHandler.soundControl(battleMusicPath, true);
        battleMusic.loop(Clip.LOOP_CONTINUOUSLY);
        battleMusic.start();
        game = g;
        game.setFocusable(true);
        game.requestFocus();
        battleField = new MainMenuView().lableMaker(0, 0, 1000, 700, battleFieldIcon);
        timerBackground = new MainMenuView().lableMaker(420, 0, 100, 100, timerIcon);
        timer = 60;
        time = new MainMenuView().textMaker(450, 25, 70, 50, timer + "");
        Runnable timeCounter = () -> {
            if(timer > 0){
                timer--;
                time.setText(timer + "");
            }
        };
        scheduler.scheduleAtFixedRate(timeCounter, 0, 1, TimeUnit.SECONDS);
        Thread timeCounterThread = new Thread(timeCounter);
        timeCounterThread.start();
        p1HealthBar = new MainMenuView().barMaker(10, 10, 400, 20, 0, 100);
        String name = "";
        if(p1Character == 1) name = "ET";
        else name = "GON";
        player1CharacterText = new MainMenuView().textMaker(20, 10, 100, 100, name);
        p2HealthBar = new MainMenuView().barMaker(550, 10, 400, 20, 0, 100);
        if(p2Character == 1) name = "ET";
        else name = "GON";
        player2CharacterText = new MainMenuView().textMaker(570, 10, 100, 100, name);
        Runnable healthTracker = () -> {
            p1HealthBar.setValue(p1Health);
            p2HealthBar.setValue(p2Health);
            if(p1Health <= 0 || p2Health <= 0 || timer == 0){
                if(!victoryScreenCalled){
                    canMove = false;
                    new VictoryScreen();
                    victoryScreenCalled = true;
                }
            }
        };
        scheduler.scheduleAtFixedRate(healthTracker, 0, 100, TimeUnit.MILLISECONDS);
        Thread healthThread = new Thread(healthTracker);
        healthThread.start();
        if(p2Character == 1){
            player2Character = new ET(p2ControllType, "L", 600,400 , 2);
            p2CharacterLabel = player2Character.getCharacter();
        }else if(p2Character == 2){
            player2Character = new Gon(p2ControllType, "L", 600,400 , 2);
            p2CharacterLabel = player2Character.getCharacter();
        }
        if(p1Character == 1){
            player1Character = new ET(p1ControllType, "L", 200,400 , 1);
            p1CharacterLabel = player1Character.getCharacter();
        }else if(p1Character == 2){
            player1Character = new Gon(p1ControllType, "r", 200,400 , 1);
            p1CharacterLabel = player1Character.getCharacter();
        }
        game.setIgnoreRepaint(true);
        game.add(p1CharacterLabel);
        game.add(p2CharacterLabel);
        game.add(player1CharacterText);
        game.add(player2CharacterText);
        game.add(p1HealthBar);
        game.add(p2HealthBar);
        game.add(time);
        game.add(timerBackground);
        game.add(battleField);
        game.addKeyListener(this);
        game.update(game.getGraphics());
    }
    public FightingMap(){}
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        if(canMove){
            player1Character.KeyPressed(e.getKeyCode() + "");
            player2Character.KeyPressed(e.getKeyCode() + "");
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        player1Character.keyReleased(e);
        player2Character.keyReleased(e);
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D){
            if(p1ControllType == 2){
                player1Character.setWalking(false);
            }if(p2ControllType == 2){
                player2Character.setWalking(false);
            }
        }
    }
    public static void setCanMove(boolean c){
        canMove = c;
    }
    public Character getPlayer1(){
        return player1Character;
    }
    public Character getPlayer2(){
        return player2Character;
    }
}
