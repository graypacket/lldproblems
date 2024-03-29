/*
*author @graypacket
*/
package parkingfloor;

import util.Coordinate;

public class Slot implements FloorObject {
    private Coordinate coord;
    private int slotId;
    private double occupancy;

    public Slot(int slotId, Coordinate coord) {
        this.coord = coord;
        this.slotId = slotId;
        this.occupancy = 1;
    }

    public Coordinate getCoordinates() {
        return coord;
    }

    public double getOccupancy() {
        return occupancy;
    }

    public void addOccupancy(double occupancy) {
        this.occupancy -= occupancy;
    }

    public int getSlotId() {
        return slotId;
    }

    public void removeOccupancy(double freedOccupancy) {
        this.occupancy += freedOccupancy;
    }
}
