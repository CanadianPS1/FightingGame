package FightingGame.Model;
import FightingGame.View.MainMenuView;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
public class ET extends Character{
    private final ImageIcon idleRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etIdleRight.png");
    private final ImageIcon idleLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etIdleLeft.png");
    private final ImageIcon walk1Right = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etWalk1Right.png");
    private final ImageIcon walk1Left = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etWalk1Left.png");
    private final ImageIcon kickRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etKickRight.png");
    private final ImageIcon kickLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etKickLeft.png");
    private final ImageIcon downKickRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDownKickRight.png");
    private final ImageIcon downKickLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDownKickLeft.png");
    private final ImageIcon punchRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etPunchRight.png");
    private final ImageIcon punchLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etPunchLeft.png");
    private final ImageIcon downPunchRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDownPunchRight.png");
    private final ImageIcon downPunchLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDownPunchLeft.png");
    private final ImageIcon deadRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDeadRight.png");
    private final ImageIcon deadLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDeadLeft.png");
    private final ImageIcon leftCrouch = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etCrouchLeft.png");
    private final ImageIcon rightCrouch = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etCrouchRight.png");
    private final ImageIcon digRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDigRight.png");
    private final ImageIcon digLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etDigLeft.png");
    private final ImageIcon kneckRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etKneckLongRight.png");
    private final ImageIcon kneckLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etKneckLongLeft.png");
    private final ImageIcon fingerRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etFingerRight.png");
    private final ImageIcon fingerLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etFingerLeft.png");
    private final ImageIcon bikeRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etBikeRight.png");
    private final ImageIcon bikeLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etBikeLeft.png");
    private final ImageIcon ultRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etPhoneHomeRight.png");
    private final ImageIcon ultLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\ET\\etPhoneHomeLeft.png");
    private boolean falling = false, jumping = false, walking = false, healing = false, spinning = false, locked = false;
    private int walkAnimationSpot = 0, spinAnimationSpot = 0, phonePieces = 0, comboCounter = 0, playerNumber;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private String keys = "", combo = "";
    private boolean crouching = false;
    private final int controllType;
    private JLabel character;
    private String derection;
    private final Random rand = new Random();
    public ET(int ct, String d, int startingX, int startingY, int pn){
        derection = d;
        controllType = ct;
        playerNumber = pn;
        if(derection.equals("r")) character = new MainMenuView().lableMaker(startingX, startingY, 175, 194, idleRight);
        else character = new MainMenuView().lableMaker(startingX, startingY, 175, 194, idleLeft);
        Runnable jumper = () -> {
            if(jumping){
                if(character.getY() >= 200) character.setLocation(character.getX(), character.getY() - 20);
                else{
                    jumping = false;
                    falling = true;
                }
            }else if(falling){
                if(character.getY() <= 380) character.setLocation(character.getX(), character.getY() + 20);
                else falling = false;
            }
        };
        scheduler.scheduleAtFixedRate(jumper, 0, 50, TimeUnit.MILLISECONDS);
        Thread jump = new Thread(jumper);
        jump.start();
        Runnable locker = () -> {
            if(locked){
                locked = false;
            }
        };
        scheduler.scheduleAtFixedRate(locker, 0, 500, TimeUnit.MILLISECONDS);
        Thread lock = new Thread(locker);
        lock.start();
        Runnable spinner = () -> {
            if(spinning){
                if(spinAnimationSpot == 0){
                    spinAnimationSpot = 1;
                    character.setIcon(downKickLeft);
                }else if(spinAnimationSpot == 1){
                    character.setIcon(downKickRight);
                    spinAnimationSpot = 0;
                }
                if(derection.equals("r")){
                    character.setLocation(character.getX() + 20, character.getY());
                    attack(character.getX() + 175, character.getY() + 140, 2, "na", derection);
                }else{
                    character.setLocation(character.getX() - 20, character.getY());
                    attack(character.getX(), character.getY() + 140, 2, "na", derection);
                }
            }
        };
        scheduler.scheduleAtFixedRate(spinner, 0, 80, TimeUnit.MILLISECONDS);
        Thread spin = new Thread(spinner);
        spin.start();
        Runnable walker = () -> {
            if(walking == true){
                if(derection.equals("r") && character.getX() + 15 <= 820) character.setLocation(character.getX() + 25, character.getY());
                else if(derection.equals("l") && character.getX() -15 >= 0) character.setLocation(character.getX() - 25, character.getY());
                walkAnimationSpot += 5;
                if(derection.equals("r")){
                    switch (walkAnimationSpot) {
                        case 0:
                            character.setIcon(walk1Right);
                            break;
                        case 10:
                            character.setIcon(idleRight);
                            break;
                        case 20:
                            character.setIcon(walk1Right);
                            break;
                        case 30:
                            walkAnimationSpot = 0;
                            character.setIcon(idleRight);
                            break;
                        default:
                            break;
                    }
                }else{
                    switch (walkAnimationSpot) {
                        case 0 -> character.setIcon(walk1Left);
                        case 10 -> character.setIcon(idleLeft);
                        case 20 -> character.setIcon(walk1Left);
                        case 30 -> {
                            walkAnimationSpot = 0;
                            character.setIcon(idleLeft);
                        }
                        default -> {
                        }
                    }
                }
            }
        };
        scheduler.scheduleAtFixedRate(walker, 0, 80, TimeUnit.MILLISECONDS);
        Thread walk = new Thread(walker);
        walk.start();
        Runnable croucher = () -> {
            if(character.getIcon() != leftCrouch || character.getIcon() != rightCrouch){
                if(crouching){
                    if(derection.equals("l")){
                        character.setIcon(leftCrouch);
                    }else{
                        character.setIcon(rightCrouch);
                    }
                }  
            }
        };
        scheduler.scheduleAtFixedRate(croucher, 0, 50, TimeUnit.MILLISECONDS);
        Thread crouch = new Thread(croucher);
        crouch.start();
        Runnable combing = () -> {
            if(derection.equals("r")){
                if(combo.equals("rUrHL")){
                    if(phonePieces >= 3){
                        character.setIcon(ultRight);
                        FightingMap.setCanMove(false);
                        if(playerNumber == 1){
                            FightingMap.p2Health = 0;
                        }else{
                            FightingMap.p1Health = 0;
                        }
                    }
                    FightingMap.setCanMove(true);
                    combo = "";
                }else if(combo.contains("DrL")){
                    spinning = true;
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 2;
                    }else{
                        FightingMap.p2Health -= 2;
                    }
                    combo = "";
                }else if(combo.contains("UrH")){
                    character.setIcon(bikeRight);
                    attack(character.getX() + 200, character.getY() + 170, 30, "U", derection);
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 10;
                    }else{
                        FightingMap.p2Health -= 10;
                    }
                    combo = "";
                }else if(combo.contains("rL")){
                    character.setIcon(fingerRight);
                    attack(character.getX() + 200, character.getY() + 140, 10, "D", derection);
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 5;
                    }else{
                        FightingMap.p2Health -= 5;
                    }
                    combo = "";
                }else if(combo.contains("UL")){
                    character.setIcon(kneckRight);
                    attack(character.getX(), character.getY() - 300, 30, "U", derection);
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 5;
                    }else{
                        FightingMap.p2Health -= 5;
                    }
                    combo = "";
                }else if(combo.contains("DH")){
                    character.setIcon(digRight);
                    phonePieces++;
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 30;
                    }else{
                        FightingMap.p2Health -= 30;
                    }
                    combo = "";
                }else if(combo.contains("DK")){
                    if(rand.nextInt(2) == 0){
                        character.setIcon(downKickRight);
                        character.setLocation(character.getX() + 30, character.getY());
                        attack(character.getX() + 185, character.getY() + 170, 8, "D", derection);
                    }
                    combo = "";
                }else if(combo.contains("DJ")){
                    character.setIcon(downPunchRight);
                    attack(character.getX() + 200, character.getY() + 140, 6, "na", derection);
                    combo = "";
                }else if(combo.contains("K")){
                    character.setIcon(kickRight);
                    attack(character.getX() + 200, character.getY() + 140, 5, "na", derection);
                    combo = "";
                }else if(combo.contains("J")){
                    character.setIcon(punchRight);
                    attack(character.getX() + 200, character.getY() + 140, 2, "na", derection);
                    combo = "";
                }
            }else{
                if(combo.equals("lUlHL")){
                    if(phonePieces >= 3){
                        character.setIcon(ultLeft);
                        FightingMap.setCanMove(false);
                        if(playerNumber == 1){
                            FightingMap.p2Health = 0;
                        }else{
                            FightingMap.p1Health = 0;
                        }
                    }
                    FightingMap.setCanMove(true);
                    combo = "";
                }else if(combo.contains("DlL")){
                    spinning = true;
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 2;
                    }else{
                        FightingMap.p2Health -= 2;
                    }
                    combo = "";
                }else if(combo.contains("UlH")){
                    character.setIcon(bikeLeft);
                    attack(character.getX() - 25, character.getY() + 170, 30, "U", derection);
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 10;
                    }else{
                        FightingMap.p2Health -= 10;
                    }
                    combo = "";
                }else if(combo.contains("lL")){
                    character.setIcon(fingerLeft);
                    attack(character.getX() - 25, character.getY() + 140, 10, "D", derection);
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 5;
                    }else{
                        FightingMap.p2Health -= 5;
                    }
                    combo = "";
                }else if(combo.contains("UL")){
                    character.setIcon(kneckLeft);
                    attack(character.getX(), character.getY() - 300, 30, "U", derection);
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 5;
                    }else{
                        FightingMap.p2Health -= 5;
                    }
                    combo = "";
                }else if(combo.contains("DH")){
                    character.setIcon(digLeft);
                    phonePieces++;
                    if(playerNumber == 1){
                        FightingMap.p1Health -= 30;
                    }else{
                        FightingMap.p2Health -= 30;
                    }
                    combo = "";
                }else if(combo.contains("DK")){
                    if(rand.nextInt(2) == 0){
                        character.setIcon(downKickLeft);
                        character.setLocation(character.getX() - 30, character.getY());
                        attack(character.getX() - 95, character.getY() + 170, 8, "D", derection);
                    }
                    combo = "";
                }else if(combo.contains("DJ")){
                    character.setIcon(downPunchLeft);
                    attack(character.getX() - 25, character.getY() + 140, 6, "na", derection);
                    combo = "";
                }else if(combo.contains("K")){
                    character.setIcon(kickLeft);
                    attack(character.getX() - 25, character.getY() + 140, 5, "na", derection);
                    combo = "";
                }else if(combo.contains("J")){
                    character.setIcon(punchLeft);
                    attack(character.getX() - 25, character.getY() + 140, 2, "na", derection);
                    combo = "";
                }
                
            }
           
        };
        scheduler.scheduleAtFixedRate(combing, 0, 10, TimeUnit.MILLISECONDS);
        Thread comboer = new Thread(combing);
        comboer.start();        
    }
    @Override
    public void KeyPressed(String key){
        if(!locked){
            if(!combo.isEmpty()){
                if(!combo.endsWith(keys)){
                    comboCounter = 0;
                    combo += keys;
                }else{
                    comboCounter++;
                }
                if(comboCounter == 3){
                    combo = "";
                }
            }else{
                combo += keys;
            }
            if(controllType == 1) keys = new JoyStickControll().KeyPressed(key);
            else keys = new KeyBoardControll().KeyPressed(key);
            if(keys.equals("U")){
                if(!jumping && !falling) jumping = true;
            } 
            if(keys.equals("r")){
                derection = "r";
                walking = true;
            }else if(keys.equals("l")){
                derection = "l";
                walking = true;
            }else{
                walking = false;
            }
            if(keys.equals("D")){
                crouching = true;
            }
        }
    }
    @Override
    public JLabel getCharacter(){return character;}
    @Override
    public void attack(int x, int y, int damage, String moveType, String d){
        if(playerNumber == 1){
            FightingMap.player2Character.checkHit(x, y, damage, moveType, derection);
        }else{
            FightingMap.player1Character.checkHit(x,y,damage, moveType, derection);
        }
    }
    @Override
    public void checkHit(int x, int y, int damage, String moveType, String d){
        if(!derection.equals(d)){
            if(x < character.getX() + 175 && x > character.getX()){
                if(playerNumber == 1){
                    FightingMap.p2Health -= damage;
                }else{
                    FightingMap.p1Health -= damage;
                }
                if(moveType.equals("D")){
                    locked = true;
                    if(derection.equals("r")){
                        character.setIcon(deadRight);
                    }else{
                        character.setIcon(deadLeft);
                    }
                    locked = true;
                }else if(moveType.equals("U")){
                    jumping = true;
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        if(healing) healing = false;
        if(spinning) spinning = false;
        if(crouching) crouching = false;
        if(character.getIcon() != idleRight || character.getIcon() != idleLeft){
            if(derection.equals("r")){
                character.setIcon(idleRight);
            }else{
                character.setIcon(idleLeft);
            }
        }
        if(controllType == 1) if(e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_F2) walking = false;
        else{
            if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) walking = false;
        }
        if(controllType == 1){
            if(e.getKeyCode() == KeyEvent.VK_F4){
                if(derection.equals("r")) character.setIcon(idleRight);
                else character.setIcon(idleLeft);
            }
        }else{
            if(e.getKeyCode() == KeyEvent.VK_S){
                if(derection.equals("r")) character.setIcon(idleRight);
                else character.setIcon(idleLeft);
            }
        }
    }
    @Override
    public void setWalking(boolean w){walking = w;}
}
