package stuff;

import lombok.Getter;

public class GameManager {
    @Getter
    private final Board board = new Board();
    @Getter
    private final FiguresPool figuresPool = new FiguresPool();

//    private final Player player1;
//    private final Player player2;
//
//    public GameManager(Player player1, Player player2) {
//        this.player1 = player1;
//        this.player2 = player2;
//    }

    public void play() {

    }
}
