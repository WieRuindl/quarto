package game_manager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

@Component
public class Frame {
    @Getter
    private Image image;

    @Getter
    @Setter
    private boolean figureWasSelected = false;

    @Getter
    @Setter
    private Point coordinates = new Point(-1, -1);

    public void initImage() {
        try {
            image = new Image("images/frame.png");
        } catch (SlickException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
