package FightingGame.Model;
public class JoyStickControll implements PlayerController{
    @Override
    public String KeyPressed(String keyCode) {
        if(keyCode.equals("114")) keyCode = "U";
        else if(keyCode.equals("112")) keyCode = "l";
        else if(keyCode.equals("115")) keyCode = "D";
        else if(keyCode.equals("113")) keyCode = "r";
        else if(keyCode.equals("118")) keyCode = "L";
        else if(keyCode.equals("119")) keyCode = "H";
        else if(keyCode.equals("117")) keyCode = "K";
        else if(keyCode.equals("116")) keyCode = "J";
        return keyCode;
    }
}