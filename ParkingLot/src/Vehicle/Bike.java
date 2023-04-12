/*
*author @graypacket
*/
package vehicle;
public class Bike extends Vehicle {
    
    public Bike(String regNo, Colour colour, int slotId) {
        super(regNo, colour, slotId);
    }

    public double getSlotOccupancy() {
        return 0.25;
    }

    public double getCharges() {
        return 0.1;
    }
}
