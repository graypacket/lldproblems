/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package parkingfloor;

import util.Coordinate;

public class Gate implements FloorObject {
    private Coordinate coord;
    private boolean entryGate;
    private int gateNo;

    public Gate(boolean entryGate, Coordinate coord, int gateNo) {
        this.entryGate = entryGate;
        this.coord = coord;
        this.gateNo = gateNo;
    }

    public int getGateNo() {
        return this.gateNo;
    }

    public boolean isEntryGate() {
        return entryGate;
    }

    public Coordinate getCoordinates() {
        return this.coord;
    }
}