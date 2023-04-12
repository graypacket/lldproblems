/*
*author @graypacket
*/
public class Player {
    private int id;
    private int currPos;

    Player(int id) {
        this.id = id;
        this.currPos = 0;
    }

    public int getId() {
        return this.id;
    }

    public int getPos() {
        return this.currPos;
    }

    public void setPos(int pos) {
        this.currPos = pos;
    }
}
