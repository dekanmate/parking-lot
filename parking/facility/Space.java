package parking.facility;

import vehicle.*;

public class Space{
    private final int floorNumber;
    private final int spaceNumber;
    private Car occupyingCar;

    public int getFloorNumber(){
        return floorNumber;
    }

    public int getSpaceNumber(){
        return spaceNumber;
    }

    public Space(int floorNumber, int spaceNumber){
        this.floorNumber = floorNumber;
        this.spaceNumber = spaceNumber;
        occupyingCar = null;
    }

    public boolean isTaken(){
        return occupyingCar != null;
    }

    public void addOccupyingCar(Car c){
        if (!this.isTaken()) occupyingCar = c;
    }

    public void removeOccupyingCar(){
        occupyingCar = null;
    }

    public String getCarLicensePlate(){
        if (occupyingCar == null) return null;
        return occupyingCar.getLicensePlate();
    }

    public Size getOccupyingCarSize(){
        if (occupyingCar == null) return null;
        return occupyingCar.getSpotOccupation();
    }
}