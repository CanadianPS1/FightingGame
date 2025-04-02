package FightingGame.Model;
import FightingGame.View.MainMenuView;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
public class Gon extends Character{
    private final ImageIcon idleRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonIdleRight.png");
    private final ImageIcon idleLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonIdleLeft.png");
    private final ImageIcon walk1Right = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonWalk1Right.png");
    private final ImageIcon walk1Left = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonWalk1Left.png");
    private final ImageIcon walk2Right = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonWalk2Right.png");
    private final ImageIcon walk2Left = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonWalk2Left.png");
    private final ImageIcon kickRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonKickRight.png");
    private final ImageIcon kickLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonKickLeft.png");
    private final ImageIcon downKickRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonDownKickRight.png");
    private final ImageIcon downKickLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonDownKickLeft.png");
    private final ImageIcon sleepRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonSleepRight.png");
    private final ImageIcon sleepLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonSleepLeft.png");
    private final ImageIcon punchRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonPunchRight.png");
    private final ImageIcon punchLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonPunchLeft.png");
    private final ImageIcon downPunchRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonDownPunchRight.png");
    private final ImageIcon downPunchLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonDownPunchLeft.png");
    private final ImageIcon fartRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonFartRight.png");
    private final ImageIcon fartLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonFartLeft.png");
    private final ImageIcon headButRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonHeadButRight.png");
    private final ImageIcon headButLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonHeadButLeft.png");
    private final ImageIcon deadRight = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonDeadRight.png");
    private final ImageIcon deadLeft = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonDeadLeft.png");
    private final ImageIcon leftCrouch = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonCorchLeft.png");
    private final ImageIcon rightCrouch = new ImageIcon("FightingGame\\Assets\\Sprites\\Characters\\Gon\\gonCorchRight.png");
    private boolean falling = false, jumping = false, walking = false, healing = false, spinning = false, locked = false, charging = false;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private int walkAnimationSpot = 0, spinAnimationSpot = 0, comboCounter = 0, playerNumber;
    private String keys = "", combo = "";
    private boolean crouching = false;
    private final int controllType;
    private JLabel character;
    private String derection;
    private final Random rand = new Random();
    public Gon(int ct, String d, int startingX, int startingY, int pn){
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
        Runnable healer = () -> {
            if(healing){
                if(playerNumber == 1){
                    FightingMap.p1Health += 1;
                }else{
                    FightingMap.p2Health += 1;
                }
            }
        };
        scheduler.scheduleAtFixedRate(healer, 0, 500, TimeUnit.MILLISECONDS);
        Thread heal = new Thread(healer);
        heal.start();
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
        Runnable charger = () -> {
            if(charging){
                locked = true;
                if(derection.equals("r")){
                    System.out.println("ramming");
                    if(character.getX() <= 800){
                        character.setLocation(character.getX() + 25, character.getY());
                        attack(character.getX() + 175, character.getY() + 170, 10, "na", derection);
                    }else{
                        charging = false;
                    }
                }else{
                    System.out.println("ramming");
                    if(character.getX() >= 0){
                        character.setLocation(character.getX() - 25, character.getY());
                        attack(character.getX(), character.getY() + 170, 10, "na", derection);
                    }else{
                        charging = false;
                    }
                }
            }else{
                locked = false;
            }
        };
        scheduler.scheduleAtFixedRate(charger, 0, 50, TimeUnit.MILLISECONDS);
        Thread charge = new Thread(charger);
        charge.start();
        Runnable walker = () -> {
            if(walking == true){
                if(derection.equals("r") && character.getX() + 15 <= 820) character.setLocation(character.getX() + 25, character.getY());
                else if(derection.equals("l") && character.getX() -15 >= 0) character.setLocation(character.getX() - 25, character.getY());
                walkAnimationSpot += 5;
                if(derection.equals("r")){
                    switch (walkAnimationSpot) {
                        case 0:
                            character.setIcon(walk2Right);
                            break;
                        case 10:
                            character.setIcon(walk1Right);
                            break;
                        case 20:
                            character.setIcon(idleRight);
                            break;
                        case 30:
                            walkAnimationSpot = 0;
                            character.setIcon(walk1Right);
                            break;
                        default:
                            break;
                    }
                }else{
                    switch (walkAnimationSpot) {
                        case 0 -> character.setIcon(walk2Left);
                        case 10 -> character.setIcon(walk1Left);
                        case 20 -> character.setIcon(idleLeft);
                        case 30 -> {
                            walkAnimationSpot = 0;
                            character.setIcon(walk1Left);
                        }
                        default -> {
                        }
                    }
                }
            }
        };
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
        scheduler.scheduleAtFixedRate(walker, 0, 80, TimeUnit.MILLISECONDS);
        Thread walk = new Thread(walker);
        walk.start();        
        Runnable combing = () -> {
            if(derection.equals("r")){
                if(combo.equals("HrK")){
                    System.out.println("ram exicuted");
                    charging = true;
                    combo = "";
                }else if(combo.contains("DrL")){
                    spinning = true;
                    combo = "";
                }else if(combo.contains("rH")){
                    character.setIcon(fartRight);
                    attack(character.getX() + 200, character.getY() + 170, 20, "na", derection);
                    combo = "";
                }else if(combo.contains("DL")){
                    character.setIcon(sleepRight);
                    healing = true;
                    combo = "";
                }else if(combo.contains("UH")){
                    character.setIcon(headButRight);
                    attack(character.getX() + 185, character.getY() + 170, 20, "U", derection);
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
                if(combo.equals("HlK")){
                    charging = true;
                    combo = "";
                }else if(combo.contains("DlL")){
                    spinning = true;
                    combo = "";
                }else if(combo.contains("lH")){
                    character.setIcon(fartLeft);
                    attack(character.getX() - 25, character.getY() + 170, 20, "na", derection);
                    combo = "";
                }else if(combo.contains("DL")){
                    character.setIcon(sleepLeft);
                    healing = true;
                    combo = "";
                }else if(combo.contains("UH")){
                    character.setIcon(headButLeft);
                    attack(character.getX() - 15, character.getY() + 170, 20, "U", derection);
                    combo = "";
                }else if(combo.contains("DK")){
                    if(rand.nextInt(2) == 0){
                        character.setIcon(downKickLeft);
                        character.setLocation(character.getX() - 30, character.getY());
                        attack(character.getX() - 15, character.getY() + 170, 8, "D", derection);
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
                if(charging) charging = false;
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
        if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_F4) if(crouching) crouching = false;
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
