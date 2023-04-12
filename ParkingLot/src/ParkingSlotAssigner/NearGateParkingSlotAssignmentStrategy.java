/*
*author @graypacket
*/
package parkingslotassigner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

import vehicle.Vehicle;
import parkingfloor.FloorObject;
import parkingfloor.Gate;
import parkingfloor.ParkingFloor;
import parkingfloor.Slot;

public class NearGateParkingSlotAssignmentStrategy implements ParkingSlotAssignmentStrategy {
    
    private Map<Integer, PriorityQueue<Slot>> floorGraph;
    private Map<Integer, Slot> slots;
    private Set<Integer> fullyOccupiedSlots;

    public NearGateParkingSlotAssignmentStrategy(List<ParkingFloor> parkingFloors) {
        this.floorGraph = new HashMap<>();
        this.slots = new HashMap<>();
        this.fullyOccupiedSlots = new HashSet<>();
        
        ParkingFloor groundFloor = parkingFloors.get(0);
        List<FloorObject> entryGates = new ArrayList<>();
        for(FloorObject floorObject : groundFloor.getFloorObjects()) {
            if(floorObject instanceof Gate && ((Gate)(floorObject)).isEntryGate()) {
                entryGates.add(floorObject);
            }
        }

        for(FloorObject entryGate : entryGates) {
            int entryGateNo = ((Gate)entryGate).getGateNo();
            floorGraph.put(entryGateNo, new PriorityQueue<Slot>(new Comparator<Slot>() {
                public int compare(Slot slot1, Slot slot2) {
                    int slot1DisX = Math.abs(slot1.getCoordinates().getCoordX() - entryGate.getCoordinates().getCoordX());
                    int slot1DisY = Math.abs(slot1.getCoordinates().getCoordY() - entryGate.getCoordinates().getCoordY());
                    int slot1DisZ = Math.abs(slot1.getCoordinates().getCoordZ() - entryGate.getCoordinates().getCoordZ());

                    int slot2DisX = Math.abs(slot2.getCoordinates().getCoordX() - entryGate.getCoordinates().getCoordX());
                    int slot2DisY = Math.abs(slot2.getCoordinates().getCoordY() - entryGate.getCoordinates().getCoordY());
                    int slot2DisZ = Math.abs(slot2.getCoordinates().getCoordZ() - entryGate.getCoordinates().getCoordZ());
                    
                    if(slot1DisZ != slot2DisZ) return slot1DisZ < slot2DisZ ? -1 : 1;
                    return slot1DisX + slot1DisY + slot1DisZ < slot2DisX + slot2DisY + slot2DisZ ? -1 : 1;
                }
            }));

            for(ParkingFloor floor : parkingFloors) {
                for(FloorObject floorObject : floor.getFloorObjects()) {
                    if(floorObject instanceof Slot) {
                        Slot slot = (Slot)floorObject;
                        floorGraph.get(entryGateNo).add(slot);
                        this.slots.put(slot.getSlotId(), slot);
                    }
                }
            }
        }
    }

    public int getAssignedSlotId(Vehicle vehicle, int entryGateNo) {
        PriorityQueue<Slot> slots = floorGraph.get(entryGateNo);
        
        List<Slot> dequedSlots = new ArrayList<>();
        int slotId = -1;

        while(!slots.isEmpty()) {
            Slot slot = slots.peek();
            if(slot.getOccupancy() >= vehicle.getSlotOccupancy()) {
                slot.addOccupancy(vehicle.getSlotOccupancy());
                slotId = slot.getSlotId();
                if(slot.getOccupancy() == 0) {
                    slots.poll();
                    fullyOccupiedSlots.add(slotId);
                }
                break;
            }
            dequedSlots.add(slots.poll());
        }

        for(Slot dequedSlot : dequedSlots) {
            slots.add(dequedSlot);
        }
        return slotId;
    }

    public void slotFreed(int slotId, double freedOccupancy) {
        if(!this.slots.containsKey(slotId)) return;
        Slot slot = this.slots.get(slotId);
        slot.removeOccupancy(freedOccupancy);

        if(fullyOccupiedSlots.contains(slot.getSlotId())) {
            for(Map.Entry<Integer, PriorityQueue<Slot>> entry : floorGraph.entrySet()) {
                entry.getValue().add(slot);
            }
            fullyOccupiedSlots.remove(slot.getSlotId());
        }
    }
}
