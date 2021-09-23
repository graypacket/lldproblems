/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package ParkingFloor;

import java.util.List;

public class ParkingFloor {
    private List<FloorObject> floorObjects;
    
    public ParkingFloor(List<FloorObject> floorObjects) {
        this.floorObjects = floorObjects;
    }

    public List<FloorObject> getFloorObjects() {
        return this.floorObjects;
    }
}
