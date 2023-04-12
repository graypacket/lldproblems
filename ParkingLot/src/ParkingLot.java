/*
*author @graypacket
*/
import parkingfloor.FloorObject;
import parkingfloor.Gate;
import parkingfloor.ParkingFloor;
import parkingfloor.Slot;
import parkingslotassigner.NearGateParkingSlotAssignmentStrategy;
import parkingslotassigner.ParkingSlotAssigner;
import parkingticket.Ticket;
import util.Coordinate;
import vehicle.Colour;
import vehicle.Vehicle;
import vehicle.VehicleFactory;
import vehicle.VehicleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ParkingLot {

    private List<ParkingFloor> parkingFloors;
    private Map<String, Ticket> tickets;    //regno -> ticket
    private VehicleManager vehicleManager;
    private ParkingSlotAssigner parkingSlotAssigner;
    private static ParkingLot parkingLotInstance;

    private ParkingLot(int numFloors, int floorWidth, int floorLength) {
        parkingFloors = new ArrayList<>();
        tickets = new HashMap<>();
        vehicleManager = new VehicleManager();
        
        int currSlotId = 1;

        for(int currFloor=0; currFloor<numFloors; currFloor++) {
            
            List<FloorObject> floorObjects = new ArrayList<>();
            
            for(int width=0; width<floorWidth; width++) {
                for(int length=0; length<floorLength; length++) {
                    floorObjects.add(new Slot(currSlotId++, new Coordinate(width, length, currFloor)));
                }
            }

            int[][] floorCorners = {
                {0, -1}, 
                {0, floorWidth}, 
                {floorLength, -1}, 
                {floorLength, floorWidth}
            };

            int currGateNo = 0;
            for(int[] floorCorner : floorCorners) {
                floorObjects.add(new Gate(true, new Coordinate(floorCorner[0], floorCorner[1], currFloor), currGateNo));
                floorObjects.add(new Gate(false, new Coordinate(floorCorner[0], floorCorner[1], currFloor), currGateNo));
                currGateNo++;
            }
            
            parkingFloors.add(new ParkingFloor(floorObjects));
        }

        parkingSlotAssigner = new ParkingSlotAssigner(new NearGateParkingSlotAssignmentStrategy(parkingFloors));
    }

    public static ParkingLot getInstance(int numFloors, int floorWidth, int floorLength) {
        return parkingLotInstance == null ? parkingLotInstance = new ParkingLot(numFloors, floorWidth, floorLength) : parkingLotInstance;
    }    
    
    public int park(String vehicleType, String regNo, String colour, int entryGateNo) {
        if(this.vehicleManager.searchVehicleByRegNo(regNo) != null) {
            return -1;
        }

        Vehicle vehicle = VehicleFactory.getInstance(vehicleType, regNo, Colour.valueOf(colour), -1);
        if(vehicle == null) return -1;

        int slotId = parkingSlotAssigner.getAssignedSlotId(vehicle, entryGateNo);
        if(slotId == -1) return -1;
        
        vehicle.setSlotId(slotId);

        Ticket ticket = new Ticket(slotId, regNo, (int)(System.currentTimeMillis()/1000));
        tickets.put(regNo, ticket);

        vehicleManager.addVehicle(vehicle);
        return slotId;
    }

    public String exit(String regNo) {
        int currTimeSec = (int)(System.currentTimeMillis()/1000);
        Ticket ticket = tickets.get(regNo);
        if(ticket == null) return "Vehicle not found!";
        
        Vehicle vehicle = vehicleManager.searchVehicleByRegNo(regNo);

        this.parkingSlotAssigner.slotFreed(ticket.getSlotId(), vehicle.getSlotOccupancy());
        
        int timeSpentMins = (currTimeSec - ticket.getEntryTime()) / 60 + 1;
        double charges = timeSpentMins * vehicle.getCharges();

        tickets.remove(regNo);
        vehicleManager.removeVehicle(regNo);
        
        return String.format("Vehicle %s exited. Time spent %d mins. Parking charges $%.2f.", regNo, timeSpentMins, charges);
    }

    public String status() {
        List<Vehicle> vehicles = this.vehicleManager.getVehiclesList();
        
        StringBuilder parkingLotStatus = new StringBuilder();
        
        for(Vehicle vehicle : vehicles) {
            parkingLotStatus.append(String.format("%s %s %s parked at %d\n", vehicle.getClass().getSimpleName(), vehicle.getRegNo(), vehicle.getColour(), vehicle.getSlotId()));
        }
        return parkingLotStatus.toString();
    }

    public String searchVehiclesByColour(String colour) {
        StringBuilder slots = new StringBuilder();
        for(Vehicle vehicle : vehicleManager.searchVehiclesByColour(Colour.valueOf(colour)))
            slots.append(vehicle.getSlotId() + " ");
        return slots.toString();
    }

    public String searchVehicleByRegNo(String regNo) {
        int slotId = this.vehicleManager.searchVehicleByRegNo(regNo) == null ? -1 : this.vehicleManager.searchVehicleByRegNo(regNo).getSlotId();
        return String.valueOf(slotId);
    }

    public String searchVehicleBySlotId(int slotId) {
        StringBuilder vehicles = new StringBuilder();
        for(Vehicle vehicle : this.vehicleManager.searchVehicleBySlotId(slotId)) {
            vehicles.append(String.format("%s %s %s\n", vehicle.getClass().getSimpleName(), vehicle.getRegNo(), vehicle.getColour()));
        }
        return vehicles.toString();
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = null;

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] params = input.split(" ");
            try {
                String cmd = params[0];
                String searchResult;
                switch(cmd) {
                    case "create_parking_lot":
                        if(parkingLot != null)
                            System.out.println("Parking lot already created!");
                        else {
                            parkingLot = ParkingLot.getInstance(Integer.valueOf(params[1]), Integer.valueOf(params[2]), Integer.valueOf(params[3]));
                            System.out.printf("Created parking lot with %s floors of size %sx%s\n", params[1], params[2], params[3]);
                        }
                        break;
                    
                    case "park":
                        int slotId = parkingLot.park(params[1], params[2], params[3], Integer.valueOf(params[4]));
                        if(slotId == -1) System.out.println("Sorry! Cannot allocate slot.");
                        else System.out.printf("Allocated slot %d\n", slotId);
                        break;
                    
                    case "exit":
                        String billInfo = parkingLot.exit(params[1]);
                        System.out.println(billInfo);
                        break;
                    
                    case "status":
                        String status = parkingLot.status();
                        System.out.println(status);
                        break;

                    case "search_colour":
                        searchResult = parkingLot.searchVehiclesByColour(params[1]);
                        System.out.println(searchResult);
                        break;

                    case "search_regno":
                        searchResult = parkingLot.searchVehicleByRegNo(params[1]);
                        System.out.println(searchResult);
                        break;

                    case "search_slot":
                        searchResult = parkingLot.searchVehicleBySlotId(Integer.valueOf(params[1]));
                        System.out.println(searchResult);
                        break;

                    default:
                        System.out.println("INVALID CMD!");
                }
            } catch(Exception e) {
                System.out.println("INVALID CMD!");
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}