package FightingGame.Model;
public class KeyBoardControll implements PlayerController{
    @Override
    public String KeyPressed(String keyCode) {
        if(keyCode.equals("87")) keyCode = "U";
        else if(keyCode.equals("65")) keyCode = "l";
        else if(keyCode.equals("83")) keyCode = "D";
        else if(keyCode.equals("68")) keyCode = "r";
        else if(keyCode.equals("89")) keyCode = "L";
        else if(keyCode.equals("85")) keyCode = "H";
        else if(keyCode.equals("75")) keyCode = "K";
        else if(keyCode.equals("72")) keyCode = "J";
        return keyCode;
    }
}