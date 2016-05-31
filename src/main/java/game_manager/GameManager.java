package game_manager;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameManager {
    @Getter
    @Autowired
    private Board board;
    @Getter
    @Autowired
    private FiguresPool figuresPool;
    @Getter
    @Autowired
    private Frame frame;

    private boolean result;

    public void choseFigure(int x, int y) {
        frame.setCoordinates(new Point(x, y));
        frame.setFigureWasSelected(true);

        selectedFiguresCoordinates.add(new Point(x, y));
    }

    public void placeFigure(int x, int y) {
        Point coordinates = frame.getCoordinates();
        List<Figure> figures = figuresPool.getFigures();
        int index = coordinates.y * 4 + coordinates.x;

        Figure figure = figures.get(index);
        boolean wasPlaced = board.placeFigure(figure, x, y);
        if (wasPlaced) {
            figures.set(index, null);
            result = board.checkWin(x, y);
        }

        frame.setCoordinates(new Point(-1, -1));
        frame.setFigureWasSelected(false);

        placedFiguresCoordinates.add(new Point(x, y));
    }

    private List<Point> selectedFiguresCoordinates = new ArrayList<>();
    private List<Point> placedFiguresCoordinates = new ArrayList<>();

    public void cancel() {
        if (selectedFiguresCoordinates.isEmpty()) {
            return;
        }
        if (selectedFiguresCoordinates.size() > placedFiguresCoordinates.size()) {
            frame.setCoordinates(new Point(-1, -1));
            frame.setFigureWasSelected(false);
            selectedFiguresCoordinates.remove(selectedFiguresCoordinates.size() - 1);
        } else {
            Point figureCoordinates = placedFiguresCoordinates.remove(placedFiguresCoordinates.size() - 1);
            Figure figure = board.removeFigure(figureCoordinates.x, figureCoordinates.y);
            Point poolCoordinates = selectedFiguresCoordinates.get(selectedFiguresCoordinates.size() - 1);
            int index = poolCoordinates.y * 4 + poolCoordinates.x;
            figuresPool.returnToPool(figure, index);
            frame.setCoordinates(poolCoordinates);
            frame.setFigureWasSelected(true);
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
