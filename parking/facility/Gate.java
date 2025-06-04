package parking.facility;

import vehicle.*;
import parking.*;
import java.util.ArrayList;

public class Gate{
    private final ArrayList<Car> cars;
    private final ParkingLot parkingLot;
    private int currentTicketId;
    
    public Gate(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
        cars = new ArrayList<Car>();
        currentTicketId = 0;
    }

    private Space findTakenSpaceByCar(Car c){
        Space[][] floorPlan = parkingLot.getFloorPlan();
        for (int i = 0; i < floorPlan.length; i++){
            for (int j = 0; j < floorPlan[0].length; j++){
                if (floorPlan[i][j].isTaken() && floorPlan[i][j].getCarLicensePlate().equals(c.getLicensePlate())){
                    return floorPlan[i][j];
                }
            }
        }
        return null;
    }

    private Space findAvailableSpaceOnFloor(int floor, Car c){
        Size size = c.getSpotOccupation();
        Space[][] floorPlan = parkingLot.getFloorPlan();
        if (size == Size.SMALL){
            for (int j = 0; j < floorPlan[floor].length; j++){
                if (!floorPlan[floor][j].isTaken()){
                    return floorPlan[floor][j];
                }
            }
        }else {
            for (int j = 0; j < floorPlan[floor].length - 1; j++){
                if (!floorPlan[floor][j].isTaken() && !floorPlan[floor][j + 1].isTaken()){
                    return floorPlan[floor][j + 1];
                }
            }
        }
        return null;
    }

    public Space findAnyAvailableSpaceForCar(Car c){
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Space space = null;
        for (int i = 0; i < floorPlan.length; i++){
            space = findAvailableSpaceOnFloor(i, c);
            if (space != null){
                return space;
            }
        }
        return space;
    }

    public Space findPreferredAvailableSpaceForCar(Car c){
        int prefferedFloor = c.getPreferredFloor();
        int floorCount = parkingLot.getFloorPlan().length;
        Space space = findAvailableSpaceOnFloor(prefferedFloor,c);
        if (space != null){
            return space;
        }
        for (int i = 1; i < floorCount; i++){
            int down = prefferedFloor - i;
            int up = prefferedFloor + i;
            if (down >= 0){
                space = findAvailableSpaceOnFloor(down, c);
                if (space != null){
                    return space;
                }
             }
            if (up < floorCount){
                 space = findAvailableSpaceOnFloor(up, c);
                if (space != null){
                    return space;
                }
            }
        }
        return null;
    }

    public boolean registerCar(Car c){
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Space space = findPreferredAvailableSpaceForCar(c);
        int floorNumber;
        int spaceNumber;
        if (space == null){
            return false;
        } else if (c.getSpotOccupation() == Size.SMALL){
            space.addOccupyingCar(c);
        } else {
            space.addOccupyingCar(c);
            floorNumber = space.getFloorNumber();
            spaceNumber = space.getSpaceNumber();
            floorPlan[floorNumber][spaceNumber - 1].addOccupyingCar(c);
        }
        c.setTicketId("T" + currentTicketId);
        currentTicketId++;
        cars.add(c);
        return true;
    }

    public void registerCars(Car... cars){
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Space space;
        int floorNumber;
        int spaceNumber;
        for (Car c : cars){
            space = findAnyAvailableSpaceForCar(c);
            if (space == null){
                System.err.printf("%nCar with license plate %s could not be assigned to any parking space.", c.getLicensePlate());
            } else if (c.getSpotOccupation() == Size.SMALL){
                space.addOccupyingCar(c);
                 c.setTicketId("T" + currentTicketId);
                 currentTicketId++;
                 this.cars.add(c);
            } else {
                space.addOccupyingCar(c);
                floorNumber = space.getFloorNumber();
                spaceNumber = space.getSpaceNumber();
                floorPlan[floorNumber][spaceNumber - 1].addOccupyingCar(c);
                c.setTicketId("T" + currentTicketId);
                currentTicketId++;
                this.cars.add(c);
            }
        }
    }

    public void deRegisterCar(String ticketId){
        Space[][] floorPlan = parkingLot.getFloorPlan();
        Car c = null;
        for (Car car : cars){
            if (car.getTicketId().equals(ticketId)){
                c = car;
            }
        }

        if (c != null){
            for (int i = 0; i < floorPlan.length; i++){
                for (int j = 0; j < floorPlan[0].length; j++){
                    if (floorPlan[i][j].isTaken() && floorPlan[i][j].getCarLicensePlate().equals(c.getLicensePlate())){
                        floorPlan[i][j].removeOccupyingCar();  
                        if (c.getSpotOccupation() == Size.LARGE){
                            floorPlan[i][j + 1].removeOccupyingCar();
                        }
                        c.setTicketId(null);
                        cars.remove(c);
                    }
                }
            }
        }
    }
}