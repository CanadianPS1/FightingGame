package FightingGame.View;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
public class MainMenuView{
    public JFrame FrameMaker(){
        JFrame game = new JFrame("Spielberg versus Gon 1");
        ImageIcon gameIcon = new ImageIcon("FightingGame\\Assets\\Sprites\\UI\\gon.png");
        game.setSize(1000,700);
        game.setLocation(450,100);
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(null);
        game.setFocusable(true);
        game.setIconImage(gameIcon.getImage());
        return game;
    }
    public JButton buttonMaker(int x, int y, int hight, int width, ImageIcon photo){
        JButton button = new JButton(photo);
        button.setSize(hight,width);
        button.setVisible(true);
        button.setLocation(x,y);
        return button;
    }
    public JLabel lableMaker(int x, int y, int hight, int width, ImageIcon photo){
        JLabel label = new JLabel(photo);
        label.setSize(hight,width);
        label.setVisible(true);
        label.setLocation(x,y);
        return label;
    }
    public JTextField textMaker(int x, int y, int hight, int width, String text){
        JTextField textField = new JTextField(text);
        textField.setSize(hight,width);
        textField.setVisible(true);
        textField.setLocation(x,y);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setFont(new Font("Arial", Font.PLAIN, 40));
        return textField;
    }
    public JProgressBar barMaker(int x, int y, int hight, int width, int min, int max){
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", Color.BLACK);
        JProgressBar progressBar = new JProgressBar(min, max);
        progressBar.setSize(hight,width);
        progressBar.setVisible(true);
        progressBar.setLocation(x,y);
        progressBar.setValue(max);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.RED);
        progressBar.setBackground(Color.YELLOW);
        
        return progressBar;
    }
}