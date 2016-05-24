import org.junit.Assert;
import org.junit.Test;
import stuff.Figure;
import stuff.FiguresPool;

import java.util.List;

public class FiguresPoolTest {
    @Test
    public void testFiguresPool() throws Exception {
        FiguresPool pool = new FiguresPool();
        List<Figure> figures = pool.getFigures();
        Assert.assertEquals(16, figures.size());
    }
}