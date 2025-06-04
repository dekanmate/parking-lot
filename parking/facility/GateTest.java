package parking.facility;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vehicle.*;
import parking.*;
import java.util.ArrayList;


public class GateTest{
    @Test
    public void testFindAnyAvailableSpaceForCar(){
        Car car1 = new Car("AAA-001", Size.SMALL, 0);
        Car car2 = new Car("AAA-002", Size.LARGE, 1);
        Car car3 = new Car("AAA-003", Size.SMALL, 2);
        ParkingLot parkingLot = new ParkingLot(3,3);
        Space[][] floorPlan = parkingLot.getFloorPlan(); 
        Gate gate = new Gate(parkingLot);
        assertEquals(floorPlan[0][0], gate.findAnyAvailableSpaceForCar(car1));
        floorPlan[0][0].addOccupyingCar(car1);
        assertEquals(floorPlan[0][2], gate.findAnyAvailableSpaceForCar(car2));
        floorPlan[0][1].addOccupyingCar(car2);
        floorPlan[0][2].addOccupyingCar(car2);
        assertEquals(floorPlan[1][0], gate.findAnyAvailableSpaceForCar(car1));
    }

    @ParameterizedTest
    @CsvSource({
        "AAA-001,SMALL,0",
        "AAA-002,LARGE,1",
        "AAA-003,SMALL,2"
    })
    public void testFindPreferredAvailableSpaceForCar(String plate, Size size, int preferredFloor){
        Car car = new Car(plate, size, preferredFloor);
        ParkingLot parkingLot = new ParkingLot(3,2);
        Space[][] floorPlan = parkingLot.getFloorPlan(); 
        Gate gate = new Gate(parkingLot);
        int j = size == Size.SMALL ? 0 : 1;
        assertEquals(floorPlan[preferredFloor][j], gate.findPreferredAvailableSpaceForCar(car));
    }

    @ParameterizedTest
    @CsvSource({
        "AAA-001,SMALL,0",
        "AAA-002,LARGE,1",
        "AAA-003,SMALL,2"
    })
    public void testRegisterCar(String plate, Size size, int preferredFloor){
        Car car = new Car(plate, size, preferredFloor);
        ParkingLot parkingLot = new ParkingLot(3,1);
        Space[][] floorPlan = parkingLot.getFloorPlan(); 
        Gate gate = new Gate(parkingLot);
        if (size == Size.SMALL){
            assertTrue(gate.registerCar(car));
            assertEquals(floorPlan[preferredFloor][0].getCarLicensePlate(), car.getLicensePlate());
        }else{
            assertFalse(gate.registerCar(car));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "AAA-001,SMALL,0",
        "AAA-002,LARGE,1",
        "AAA-003,SMALL,2"
    })
    public void testDeRegisterCar(String plate, Size size, int preferredFloor){
        Car car = new Car(plate, size, preferredFloor);
        ParkingLot parkingLot = new ParkingLot(3,2);
        Space[][] floorPlan = parkingLot.getFloorPlan(); 
        Gate gate = new Gate(parkingLot);
        gate.registerCar(car);
        assertTrue(floorPlan[preferredFloor][0].isTaken());
        gate.deRegisterCar("T0");
        assertFalse(floorPlan[preferredFloor][0].isTaken());
    }
}