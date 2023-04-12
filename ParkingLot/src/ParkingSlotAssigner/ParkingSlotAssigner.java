/*
*author @graypacket
*/
package parkingslotassigner;

import vehicle.Vehicle;

public class ParkingSlotAssigner {
    private ParkingSlotAssignmentStrategy slotAssignmentStrategy;

    public ParkingSlotAssigner(ParkingSlotAssignmentStrategy slotAssignmentStrategy) {
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }

    public int getAssignedSlotId(Vehicle vehicle, int entryGateNo) {
        return slotAssignmentStrategy.getAssignedSlotId(vehicle, entryGateNo);
    }

    public void slotFreed(int slotId, double freedOccupancy) {
        slotAssignmentStrategy.slotFreed(slotId, freedOccupancy);
    }
}
