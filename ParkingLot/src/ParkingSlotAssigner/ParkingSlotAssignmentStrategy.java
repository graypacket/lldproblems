/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package ParkingSlotAssigner;

import Vehicle.Vehicle;

public interface ParkingSlotAssignmentStrategy {
    public int getAssignedSlotId(Vehicle vehicle, int entryGateNo);
    public void slotFreed(int slotId, double freedOccupancy);
}
