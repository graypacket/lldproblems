# Design a Parking Lot

The problem statement can be found at /low-level-design/problem/parking-lot.

## Approach

The idea is to view the parking lot as a 3d grid. Each floor is a 2d grid with two types of objects, a slot and a gate, a gate could be an entry gate or an exit gate. Each floor object can be identified using a coordinate. As given in the problem statement we have entry gates present at each of the 4 corners of the parking floor. We are assuming that each pair of entry and exit gate is built side by side. Look at the below representation of a parking lot with 2 floors and 3 x 3 slots on each floor:  

——————————-Floor1——————————-  

entry_g0                                    entry_g1  
exit_g0(0,-1)   s1(0,0) s2(0,1) s3(0,2)     exit_g1(0,3)  
                s4(1,0) s5(1,1) s6(1,2)  
entry_g2(2,-1)  s7(2,0) s8(2,1) s9(2,2)     entry_g3(2,3)  
exit_g2                                     exit_g3  

——————————-Floor2——————————-  

entry_g0                                            entry_g1   
exit_g0(0,-1)   s10(0,0)    s11(0,1)    s12(0,2)    exit_g1(0,3)  
                s13(1,0)    s14(1,1)    s15(1,2)  
entry_g2(2,-1)  s16(2,0)    s17(2,1)    s18(2,2)    entry_g3(2,3)  
exit_g2                                             exit_g3  

Note that a vehicle can only enter from the ground floor and assume that there is a route from every entry gate to the corresponding entry gate on the floor above and below the current floor.

Thinking of the Parking Lot as a 3d grid will help in the implementation of assigning the nearest slot to the vehicles. Each floor object (slots and gates) has a coordinate associated with it so to assign the nearest slot from a particular gate we can store the slots in a priority queue for every entry gate and the priority can be calculated by measuring the distance between the slot and the gate. For measuring distance we will only be considering the x-axis and y-axis distance between the slot and the gate and we'll be giving priority to the slots present on the same floor or on the nearest vacant floor. Check the implementation at ./src/ParkingSlotAssigner/NearGateParkingSlotAssignmentStrategy.java for more details.

To understand the overall design, you can look at the class diagram ParkingLot.jpg. There is a Coordinate class to represent an object in 3d space. FloorObject is using Coordinate class object to store the coordinates. Slot and Gate classes are implementing the FloorObject interface.

Vehicle is an Abstract class and is extended by Car, Bike, Bus and Truck classes. There is a VehicleManager class to store the list of vehicles and provide methods for searching vehicles on different parameters. We are using Factory pattern (VehicleFactory) to instantiate different types of Vehicle objects.

ParkingLot class is using ParkingSlotAssigner to assign the slots. Here we are using Strategy pattern (ParkingSlotAssignmentStrategy) so that new assignment strategies can be implemented in future without changing a lot of code.