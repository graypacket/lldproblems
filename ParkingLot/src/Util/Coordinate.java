/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
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
