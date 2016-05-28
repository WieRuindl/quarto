package game_manager;

import lombok.Data;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import qualities.Color;
import qualities.Relief;
import qualities.Shape;
import qualities.Size;

@Data
public class Figure {
    private final Size size;
    private final Color color;
    private final Shape shape;
    private final Relief relief;

    private Image image;

    public void initImage() {
        try {
            this.image = new Image("images/figures/" +size+color+shape+relief+".png");
        } catch (SlickException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
