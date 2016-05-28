package game_manager;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class GameManager {
    @Getter
    @Autowired
    private Board board;
    @Getter
    @Autowired
    private final FiguresPool figuresPool = new FiguresPool();
    @Getter
    @Autowired
    private game_manager.Frame frame = new game_manager.Frame();

    private boolean result;

    public void choseFigure(int x, int y) {
        frame.setCoordinates(new Point(x, y));
    }

    public void placeFigure(int x, int y) {
        Point coordinates = frame.getCoordinates();
        List<Figure> figures = figuresPool.getFigures();
        int index = coordinates.y * 4 + coordinates.x;

        Figure figure = figures.get(index);
        boolean wasPlaced = board.putFigure(figure, x, y);
        if (wasPlaced) {
            result = board.checkWin(x, y);
            figures.set(index, null);

        }
    }

    public boolean gameIsEnded() {
        return result || board.isFull();
    }

    public void initImages() {
        board.initImage();
        figuresPool.initImages();
        frame.initImage();
    }
}
