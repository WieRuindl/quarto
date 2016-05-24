package stuff;

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

    private final Image image;

    public Figure(Size size, Color color, Shape shape, Relief relief) {
        this.size = size;
        this.color = color;
        this.shape = shape;
        this.relief = relief;

        try {
            this.image = new Image("figures/"+size+color+shape+relief+".png");
        } catch (SlickException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
