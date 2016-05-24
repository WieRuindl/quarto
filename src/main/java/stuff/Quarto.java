package stuff;

import org.newdawn.slick.*;

import java.util.List;

public class Quarto extends BasicGame {

    private GameManager gameManager;

    public Quarto(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        gameManager = new GameManager();
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == Input.MOUSE_LEFT_BUTTON) {
            if (x > 512 + 72) {
                x -= 512 + 72;
            }
            int xx = x / 128;
            int yy = y / 128;

            gameManager.move(xx, yy);
        }
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
        drawResult(g);
    }

    private void drawResult(Graphics g) {
        if (gameManager.gameIsEnded()) {
            g.drawString("WIN", 100, 100);
        }
    }

    private void drawChosenFigure(Graphics g) {
        Figure chosenFigure = gameManager.getChosenFigure();
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


