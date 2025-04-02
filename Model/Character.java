package FightingGame.Model;
import java.awt.event.KeyEvent;
import javax.swing.*;
public abstract class Character{
    abstract void attack(int x, int y, int damage, String moveType, String d);
    abstract void checkHit(int x, int y, int damage, String moveType, String d);
    abstract void keyReleased(KeyEvent e);
    public abstract void KeyPressed(String key);
    public abstract JLabel getCharacter();
    public abstract void setWalking(boolean w);
}
