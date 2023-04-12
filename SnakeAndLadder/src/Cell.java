/*
*author @graypacket
*/
public class Cell {
    private int id;
    private int connectedCellId;
    private boolean ladderPresent;
    private boolean snakePresent;

    Cell(int id) {
        this.id = id;
        connectedCellId = id;
    }

    public boolean isSnakePresent() {
        return this.snakePresent;
    }
    
    public boolean isLadderPresent() {
        return this.ladderPresent;
    }

    public void setSnakePresent(boolean snakePresent) {
        this.snakePresent = snakePresent;
    }

    public void setLadderPresent(boolean ladderPresent) {
        this.ladderPresent = ladderPresent;
    }

    public int getId() {
        return this.id;
    }

    public int getConnectedCellId() {
        return this.connectedCellId;
    }

    public void setConnectedCellId(int connectedCellId) {
        this.connectedCellId = connectedCellId;
    }
}
