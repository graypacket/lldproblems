/*
*author @graypacket
*/
package parkingticket;
public class Ticket {
    private int slotId;
    private String vehicleRegNo;
    private int entryTime;

    public Ticket(int slotId, String vehicleRegNo, int entryTime) {
        this.slotId = slotId;
        this.vehicleRegNo = vehicleRegNo;
        this.entryTime = entryTime;
    }

    public String getVehicleRegNo() {
        return this.vehicleRegNo;
    }

    public int getSlotId() {
        return this.slotId;
    }

    public int getEntryTime() {
        return this.entryTime;
    }
}
