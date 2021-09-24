/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package vehicle;

public abstract class Vehicle {
    private String regNo;
    private Colour colour;
    private int slotId;
    
    Vehicle(String regNo, Colour colour, int slotId) {
        this.regNo = regNo;
        this.colour = colour;
        this.slotId = slotId;
    }

    public String getRegNo() {
        return this.regNo;
    }

    public Colour getColour() {
        return this.colour;
    }
    
    public int getSlotId() {
        return this.slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public abstract double getSlotOccupancy();
    public abstract double getCharges();
}
