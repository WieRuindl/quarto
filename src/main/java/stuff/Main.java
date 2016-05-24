package stuff;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    private static final int CELL_SIZE = 128;
    private static final int CELLS_NUM = 4;
    private static final int SPACE = 32;

    public static void main(String[] args) throws SlickException {
        int height = CELL_SIZE * CELLS_NUM;
        int width = height * 2 + SPACE * 2;

        AppGameContainer container = new AppGameContainer(new Quarto("Quarto"), width, height, false);
        container.setShowFPS(false);
        container.start();
    }
}
