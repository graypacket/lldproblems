/*
*author @graypacket
*/
package util;
public class Coordinate {
    private int coordX;
    private int coordY;
    private int coordZ;

    public Coordinate(int coordX, int coordY, int coordZ) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getCoordZ() {
        return coordZ;
    }
}
