package game_manager;

import lombok.Getter;
import org.springframework.stereotype.Component;
import qualities.Color;
import qualities.Relief;
import qualities.Shape;
import qualities.Size;

import java.util.LinkedList;
import java.util.List;

@Component
public class FiguresPool {
    @Getter
    private List<Figure> figures = new LinkedList<>();

    public FiguresPool() {
        for (Size size : Size.values()) {
            for (Color color : Color.values()) {
                for (Shape shape : Shape.values()) {
                    for (Relief relief : Relief.values()) {
                        Figure figure = new Figure(size, color, shape, relief);
                        figures.add(figure);
                    }
                }
            }
        }
    }

    public void initImages() {
        figures.forEach(Figure::initImage);
    }
}
