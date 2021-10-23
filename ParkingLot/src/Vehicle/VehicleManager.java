/*
* License Information
* 
* The provided codebase is for personal use only.
* You can alter/modify the code for your own personal use.
* Redistribution of code is not permitted.
* 
* Copyright (c) 2021 ProCoderFast
*/
package vehicle;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class VehicleManager {
    private List<Vehicle> vehicles;
    private Map<Colour, Set<Vehicle>> vehiclesByColourMap;
    private Map<String, Vehicle> vehicleByRegNo;
    private Map<Integer, Set<Vehicle>> vehiclesBySlotId;

    public VehicleManager() {
        vehicles = new ArrayList<>();
        vehiclesByColourMap = new HashMap<>();
        vehicleByRegNo = new HashMap<>();
        vehiclesBySlotId = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        
        if(!this.vehiclesByColourMap.containsKey(vehicle.getColour()))
            this.vehiclesByColourMap.put(vehicle.getColour(), new HashSet<>());
        this.vehiclesByColourMap.get(vehicle.getColour()).add(vehicle);
        
        this.vehicleByRegNo.put(vehicle.getRegNo(), vehicle);

        if(!this.vehiclesBySlotId.containsKey(vehicle.getSlotId()))
            this.vehiclesBySlotId.put(vehicle.getSlotId(), new HashSet<>());
        this.vehiclesBySlotId.get(vehicle.getSlotId()).add(vehicle);
    }

    public List<Vehicle> getVehiclesList() {
        return this.vehicles;
    }

    public Set<Vehicle> searchVehiclesByColour(Colour colour) {
        return this.vehiclesByColourMap.get(colour) == null ? new HashSet<>() : this.vehiclesByColourMap.get(colour);
    }

    public Set<Vehicle> searchVehicleBySlotId(int slotId) {
        return this.vehiclesBySlotId.get(slotId) == null ? new HashSet<>() : this.vehiclesBySlotId.get(slotId);
    }

    public Vehicle searchVehicleByRegNo(String regNo) {
        return this.vehicleByRegNo.get(regNo);
    } 
    
    public void removeVehicle(String regNo) {
        Vehicle vehicle = this.searchVehicleByRegNo(regNo);
        this.vehicles.remove(vehicle);
        this.vehicleByRegNo.remove(vehicle.getRegNo());
        this.vehiclesByColourMap.get(vehicle.getColour()).remove(vehicle);
        this.vehiclesBySlotId.get(vehicle.getSlotId()).remove(vehicle);
    }
}
