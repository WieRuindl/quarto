package stuff;

import lombok.Getter;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Board {

    @Getter
    private final Image image;

    private Figure[][] cells = new Figure[4][4];

    public Board() {
        try {
            this.image = new Image("figures/board.png");
        } catch (SlickException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean putFigure(Figure figure, int x, int y) {
        if (cells[x][y] != null) {
            return false;
        } else {
            cells[x][y] = figure;
            return true;
        }
    }

    public boolean checkWin(int x, int y) {
        return verifyRow(y) || verifyColumn(x) || verifyDiagonal(x, y);
    }

    private boolean verifyRow(int y) {
        return verify(cells[0][y], cells[1][y], cells[2][y], cells[3][y]);
    }

    private boolean verifyColumn(int x) {
        return verify(cells[x][0], cells[x][1], cells[x][2], cells[x][3]);
    }

    private boolean verifyDiagonal(int x, int y) {
        if (x == y) {
            return verify(cells[0][0], cells[1][1], cells[2][2], cells[3][3]);
        }

        if (x + y == 3) {
            return verify(cells[3][0], cells[2][1], cells[1][2], cells[0][3]);
        }

        return false;
    }

    private boolean verify(Figure figure1, Figure figure2, Figure figure3, Figure figure4) {
        return
                verifyNotEmpty(figure1, figure2, figure3, figure4) ||
                        verifySize(figure1, figure2, figure3, figure4) ||
                        verifyColor(figure1, figure2, figure3, figure4) ||
                        verifyShape(figure1, figure2, figure3, figure4) ||
                        verifyRelief(figure1, figure2, figure3, figure4);
    }

    private boolean verifyNotEmpty(Figure figure1, Figure figure2, Figure figure3, Figure figure4) {
        return (figure1 != null) && (figure2 != null) && (figure3 != null) && (figure4 != null);
    }

    private boolean verifySize(Figure figure1, Figure figure2, Figure figure3, Figure figure4) {
        return (figure1.getSize() == figure2.getSize()) &&
                (figure1.getSize() == figure3.getSize()) &&
                (figure1.getSize() == figure4.getSize());
    }

    private boolean verifyColor(Figure figure1, Figure figure2, Figure figure3, Figure figure4) {
        return (figure1.getColor() == figure2.getColor()) &&
                (figure1.getColor() == figure3.getColor()) &&
                (figure1.getColor() == figure4.getColor());
    }

    private boolean verifyShape(Figure figure1, Figure figure2, Figure figure3, Figure figure4) {
        return (figure1.getShape() == figure2.getShape()) &&
                (figure1.getShape() == figure3.getShape()) &&
                (figure1.getShape() == figure4.getShape());
    }

    private boolean verifyRelief(Figure figure1, Figure figure2, Figure figure3, Figure figure4) {
        return (figure1.getRelief() == figure2.getRelief()) &&
                (figure1.getRelief() == figure3.getRelief()) &&
                (figure1.getRelief() == figure4.getRelief());
    }

    public Image getCellImage(int x, int y) {
        return (cells[x][y] != null) ? cells[x][y].getImage() : null;
    }
}
