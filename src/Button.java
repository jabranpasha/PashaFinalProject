import java.awt.*;

public class Button extends Rectangle{
    private boolean clickable;

    public Button(int x, int y, int width, int height, boolean c){
        super(x,y,width,height);
        clickable = c;
    }

    public boolean isClickable(){
        return clickable;
    }
    public void setClickable(boolean c){
        clickable = c;
    }
}
