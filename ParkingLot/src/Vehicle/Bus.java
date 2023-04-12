/*
*author @graypacket
*/
package vehicle;

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