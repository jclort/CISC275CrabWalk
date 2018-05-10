import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
public enum Images{
    TRASH1("images/trash1.png"),
    TRASH2("images/trash2.png"),
    TRASH3("images/trash3.png"),
    TRASH4("images/trash4.png"),
    TRASH5("images/trash5.png"),
    INVA1("images/creature1.png"),
    INVA2("images/creature2.png"),
    CRAB("images/crab.png");

    private BufferedImage pic;
    private Images(String s){
        try {
           // This way we can get the image formatted how we need
            pic = ImageIO.read(new File(s));
        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage getPic(){
        return pic;
    }
} /* This file is because otherwise
we would have to bring the View into the Model and
nobody wants to have to deal with that */