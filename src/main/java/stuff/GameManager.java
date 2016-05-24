package stuff;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GameManager {
    @Getter
    private final Board board = new Board();
    @Getter
    private final FiguresPool figuresPool = new FiguresPool();

    @Getter
    @Setter
    private Figure chosenFigure;

    private boolean result;

    public void move(int x, int y) {
        if (chosenFigure == null) {
            choseFigure(x, y);
        } else {
            placeFigure(x, y);
        }
    }

    private void choseFigure(int x, int y) {
        List<Figure> figures = figuresPool.getFigures();
        int index = y * 4 + x;
        Figure figure = figures.get(index);
        if (figure != null) {
            chosenFigure = figure;
            figures.set(index, null);
        }
    }

    private void placeFigure(int x, int y) {
        boolean wasPlaced = board.putFigure(chosenFigure, x, y);
        if (wasPlaced) {
            result = board.checkWin(x, y);
            chosenFigure = null;
        }
    }

    public boolean gameIsEnded() {
        return result || figuresPool.isEmpty();
    }
}
