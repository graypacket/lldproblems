/*
*author @graypacket
*/
package vehicle;

public class Truck extends Vehicle {
    
    public Truck(String regNo, Colour colour, int slotId) {
        super(regNo, colour, slotId);
    }

    public double getSlotOccupancy() {
        return 1;
    }

    public double getCharges() {
        return 0.4;
    }
}