/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package Vehicle;

public class Bus extends Vehicle {
    
    public Bus(String regNo, Colour colour, int slotId) {
        super(regNo, colour, slotId);
    }

    public double getSlotOccupancy() {
        return 1;
    }

    public double getCharges() {
        return 0.3;
    }
}