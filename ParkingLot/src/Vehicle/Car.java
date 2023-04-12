/*
*author @graypacket
*/
package vehicle;
public class Car extends Vehicle {
    public Car(String regNo, Colour colour, int slotId) {
        super(regNo, colour, slotId);
    }

    public double getSlotOccupancy() {
        return 0.5;
    }

    public double getCharges() {
        return 0.2;
    }
}
