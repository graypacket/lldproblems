/*
*author @graypacket
*/
package parkingslotassigner;

import vehicle.Vehicle;

public interface ParkingSlotAssignmentStrategy {
    public int getAssignedSlotId(Vehicle vehicle, int entryGateNo);
    public void slotFreed(int slotId, double freedOccupancy);
}
