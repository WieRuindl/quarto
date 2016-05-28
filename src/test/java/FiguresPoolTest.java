import org.junit.Assert;
import org.junit.Test;
import game_manager.Figure;
import game_manager.FiguresPool;

import java.util.List;

public class FiguresPoolTest {
    @Test
    public void testFiguresPool() throws Exception {
        FiguresPool pool = new FiguresPool();
        List<Figure> figures = pool.getFigures();
        Assert.assertEquals(16, figures.size());
    }
}