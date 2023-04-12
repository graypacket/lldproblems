/*
*author @graypacket
*/
package vehicle;

public class VehicleFactory {
    public static Vehicle getInstance(String vehicleType, String regNo, Colour colour, int slotId) {
        Vehicle vehicle = null;
        try {
            vehicle = (Vehicle) Class.forName("Vehicle."+vehicleType).getDeclaredConstructor(String.class, Colour.class, int.class).newInstance(regNo, colour, slotId);
        } catch (Exception | NoClassDefFoundError e) {
            e.printStackTrace();
        }
        
        return vehicle;
    }
}
