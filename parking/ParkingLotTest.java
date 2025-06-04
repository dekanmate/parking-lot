package parking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import vehicle.*;
import parking.facility.*;

public class ParkingLotTest{

    @Test
    public void testConstructorWithInvalidValues(){
        try {
            ParkingLot parkingLot = new ParkingLot(0, 5);
            fail("Nem dobott IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Ok
        }

        try {
            ParkingLot parkingLot = new ParkingLot(3, -1);
            fail("Nem dobott IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Ok
        }
    }
    
    @Test
    public void testTextualRepresentation(){
        ParkingLot parkingLot1 = new ParkingLot(1, 3);
        Space[][] floorPlan1 = parkingLot1.getFloorPlan();

        Car a1 = new Car("AAA-001", Size.SMALL, 0);
        Car a2 = new Car("AAA-002", Size.LARGE, 0);
        floorPlan1[0][0].addOccupyingCar(a1);
        floorPlan1[0][1].addOccupyingCar(a2);
        floorPlan1[0][2].addOccupyingCar(a2);
        
        ParkingLot parkingLot2 = new ParkingLot(2, 2);
        Space[][] floorPlan2 = parkingLot2.getFloorPlan();

        Car b1 = new Car("BBB-001", Size.SMALL, 0);
        Car b2 = new Car("BBB-002", Size.SMALL, 0);
        Car b3 = new Car("BBB-003", Size.LARGE, 1);

        floorPlan2[0][0].addOccupyingCar(b1);
        floorPlan2[0][1].addOccupyingCar(b2);

        floorPlan2[1][0].addOccupyingCar(b3);
        floorPlan2[1][1].addOccupyingCar(b3);

        String expected1 = "S L L";
        String expected2 = "L L\nS S";

        assertEquals(expected1, parkingLot1.toString());
        assertEquals(expected2, parkingLot2.toString());

        floorPlan1[0][0].removeOccupyingCar();
        
        floorPlan2[1][0].removeOccupyingCar();
        floorPlan2[1][1].removeOccupyingCar();

        expected1 = "X L L";
        expected2 = "X X\nS S";

        assertEquals(expected1, parkingLot1.toString());
        assertEquals(expected2, parkingLot2.toString());
    }
}