/*
*author @graypacket
*/
package parkingfloor;

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
