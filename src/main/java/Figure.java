import lombok.Data;
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
}
