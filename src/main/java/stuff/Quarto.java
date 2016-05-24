package stuff;

import org.newdawn.slick.*;

import java.util.List;

public class Quarto extends BasicGame {

    private GameManager gameManager;
    private GameStage gameStage;

    public Quarto(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        gameManager = new GameManager();
        gameStage = GameStage.SELECT_FIGURE;
    }

    private Figure chosenFigure;

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == Input.MOUSE_LEFT_BUTTON) {
            if (gameStage == GameStage.SELECT_FIGURE) {
                Figure figure = selectFigure(x, y);
                if (figure != null) {
                    chosenFigure = figure;
                    gameStage = GameStage.PLACE_FIGURE;
                }
            } else {
                boolean wasPlaced = placeFigure(x, y);
                if (wasPlaced) {
                    gameStage = GameStage.SELECT_FIGURE;
                    chosenFigure = null;
                }
            }
        }
    }

    private boolean placeFigure(int x, int y) {
        if (x > 0 && x < 512 && y > 0 && y < 512) {
            int xx = x / 128;
            int yy = y / 128;

            Board board = gameManager.getBoard();
            return board.putFigure(chosenFigure, xx, yy);
        }
        return false;
    }

    private Figure selectFigure(int x, int y) {
        if (x > 512 + 72 && x < 512 * 2 + 72 && y > 0 && y < 512) {
            int xx = (x - 512 - 72) / 128;
            int yy = y / 128;

            int index = yy * 4 + xx;
            List<Figure> figures = gameManager.getFiguresPool().getFigures();

            Figure figure = figures.get(index);
            figures.set(index, null);
            return figure;
        }
        return null;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        drawBoard(g);
        drawFigures(g);
        drawPool(g);
        drawChosenFigure(g);
    }

    private void drawChosenFigure(Graphics g) {
        if (chosenFigure != null) {
            g.drawImage(chosenFigure.getImage(), 512 + 16, 256 - 32);
        }
    }

    private void drawFigures(Graphics g) {
        Board board = gameManager.getBoard();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Image image = board.getCellImage(x, y);
                if (image != null) {
                    g.drawImage(image, x * 128 + 32, y * 128 + 32);
                }
            }
        }
    }

    private void drawBoard(Graphics g) {
        Board board = gameManager.getBoard();
        g.drawImage(board.getImage(), 0, 0);
    }

    private void drawPool(Graphics g) {
        FiguresPool figuresPool = gameManager.getFiguresPool();
        List<Figure> figures = figuresPool.getFigures();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Figure figure = figures.get(y * 4 + x);
                if (figure == null) {
                    continue;
                }
                Image image = figure.getImage();
                g.drawImage(image, x * 128 + 512 + 72 + 32, y * 128 + 32);
            }
        }
    }
}


