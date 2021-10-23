/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own personal use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package parkingslotassigner;

import vehicle.Vehicle;

public interface ParkingSlotAssignmentStrategy {
    public int getAssignedSlotId(Vehicle vehicle, int entryGateNo);
    public void slotFreed(int slotId, double freedOccupancy);
}
