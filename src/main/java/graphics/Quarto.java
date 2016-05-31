package graphics;

import game_manager.Board;
import game_manager.Frame;
import game_manager.Figure;
import game_manager.FiguresPool;
import game_manager.GameManager;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class Quarto extends BasicGame {

    @Value(value = "${cell.size}")
    private int CELL_SIZE;

    @Value(value = "#{${cell.size}*4}")
    private int BOARD_SIZE;

    @Value(value = "#{${cell.size}/4}")
    private int SPACE;

    @Autowired
    private GameManager gameManager;

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
            if (x > BOARD_SIZE * 2) {
                return;
            }
            if (!gameManager.getFrame().isFigureWasSelected()) {
                if (x >= BOARD_SIZE && y < BOARD_SIZE) {
                    int xx = (x - BOARD_SIZE) / CELL_SIZE;
                    int yy = y / CELL_SIZE;
                    gameManager.choseFigure(xx, yy);
                }
            } else {
                if (x < BOARD_SIZE && y < BOARD_SIZE) {
                    int xx = x / CELL_SIZE;
                    int yy = y / CELL_SIZE;
                    gameManager.placeFigure(xx, yy);
                }
            }
        }
        if (button == Input.MOUSE_RIGHT_BUTTON) {
            gameManager.cancel();
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (!gameManager.getFrame().isFigureWasSelected()) {
            if (newx >= BOARD_SIZE && newy < BOARD_SIZE) {
                int xx = (newx - BOARD_SIZE) / CELL_SIZE;
                int yy = newy / CELL_SIZE;
                if (gameManager.getFiguresPool().getFigures().get(yy * 4 + xx) != null) {
                    gameManager.getFrame().setCoordinates(new Point(xx, yy));
                } else {
                    gameManager.getFrame().setCoordinates(new Point(-1, -1));
                }
            } else {
                gameManager.getFrame().setCoordinates(new Point(-1, -1));
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
            g.drawString("END", CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawFrame(Graphics g) {
        Frame frame = gameManager.getFrame();
        g.drawImage(frame.getImage(), BOARD_SIZE + frame.getCoordinates().x * CELL_SIZE + SPACE, frame.getCoordinates().y * CELL_SIZE + SPACE);
    }

    private void drawFiguresOnBoard(Graphics g) {
        Board board = gameManager.getBoard();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Image image = board.getCellImage(x, y);
                if (image != null) {
                    g.drawImage(image, x * CELL_SIZE + SPACE, y * CELL_SIZE + SPACE);
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
                g.drawImage(image, x * CELL_SIZE + BOARD_SIZE + SPACE, y * CELL_SIZE + SPACE);
            }
        }
    }
}


