package stuff;

import lombok.Getter;
import qualities.Color;
import qualities.Relief;
import qualities.Shape;
import qualities.Size;

import java.util.LinkedList;
import java.util.List;

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

    public boolean isEmpty() {
        for (Figure figure : figures) {
            if (figure != null) {
                return false;
            }
        }
        return true;
    }
}
