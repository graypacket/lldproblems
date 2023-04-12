/*
*author @graypacket
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int BOARD_LENGTH = 10;
    private static final int BOARD_WIDTH = 10;
    private Map<Integer, Cell> cells;

    Board(List<Snake> snakes, List<Ladder> ladders) {
        cells = new HashMap<>();

        int cellNo = 1;
        for(int len=0; len<BOARD_LENGTH; len++) {
            for(int wid=0; wid<BOARD_WIDTH; wid++) {
                cells.put(cellNo, new Cell(cellNo++));
            }
        }

        for(Snake snake : snakes) {
            Cell cell = cells.get(snake.getStart());
            cell.setConnectedCellId(snake.getEnd());
            cell.setSnakePresent(true);
        }

        for(Ladder ladder : ladders) {
            Cell cell = cells.get(ladder.getStart());
            cell.setConnectedCellId(ladder.getEnd());
            cell.setLadderPresent(true);
        }
    }

    public static int getRow(int cellNo) {
        return (cellNo-1) / BOARD_WIDTH;
    }

    public Map<Integer, Cell> getCells() {
        return this.cells;
    }
}
