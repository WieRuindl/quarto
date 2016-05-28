package graphics;

import game_manager.Board;
import game_manager.Figure;
import game_manager.FiguresPool;
import game_manager.GameManager;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class Quarto extends BasicGame {

    @Autowired
    private final GameManager gameManager = new GameManager();

    public Quarto() {
        super("Quarto");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        gameManager.initImages();
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (!gameManager.gameIsEnded() && button == Input.MOUSE_LEFT_BUTTON) {
            if (x > 512 * 2) {
                return;
            }
            if (!gameManager.getFrame().isFigureWasSelected()) {
                if (x >= 512 && y < 512) {
                    int xx = (x - 512) / 128;
                    int yy = y / 128;
                    gameManager.choseFigure(xx, yy);
                    gameManager.getFrame().setFigureWasSelected(true);
                }
            } else {
                if (x < 512 && y < 512) {
                    int xx = x / 128;
                    int yy = y / 128;
                    gameManager.placeFigure(xx, yy);
                    gameManager.getFrame().setCoordinates(new Point(-1, -1));
                    gameManager.getFrame().setFigureWasSelected(false);
                }
            }
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (!gameManager.getFrame().isFigureWasSelected()) {
            if (newx >= 512 && newy < 512) {
                int xx = (newx - 512) / 128;
                int yy = newy / 128;
                if (gameManager.getFiguresPool().getFigures().get(yy * 4 + xx) != null) {
                    gameManager.getFrame().setCoordinates(new Point(xx, yy));
                } else {
                    gameManager.getFrame().setCoordinates(new Point(-1, -1));
                }
            }
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        drawBoard(g);
        drawFiguresOnBoard(g);
        drawPool(g);
        drawFrame(g);
        drawResult(g);
    }

    private void drawResult(Graphics g) {
        if (gameManager.gameIsEnded()) {
            g.drawString("END", 100, 100);
        }
    }

    private void drawFrame(Graphics g) {
        game_manager.Frame frame = gameManager.getFrame();
        g.drawImage(frame.getImage(), 512 + frame.getCoordinates().x * 128 + 32, frame.getCoordinates().y * 128 + 32);
    }

    private void drawFiguresOnBoard(Graphics g) {
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
                g.drawImage(image, x * 128 + 512 + 32, y * 128 + 32);
            }
        }
    }
}


