package parking;

import parking.facility.*;
import vehicle.*;

public class ParkingLot{
    private final Space[][] floorPlan;

    public ParkingLot(int floorNumber, int spaceNumber){
        if (floorNumber < 1 || spaceNumber < 1) {
            throw new IllegalArgumentException();
        }

        floorPlan = new Space[floorNumber][spaceNumber];

        for (int i = 0; i < floorNumber; i++){
            for (int j = 0; j < spaceNumber; j++){
                floorPlan[i][j] = new Space(i, j);
            }
        }
    }

    public Space[][] getFloorPlan(){
        return floorPlan;
    }

    public String toString(){
        int floorNumber = floorPlan.length;
        int spaceNumber = floorPlan[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = floorNumber - 1; i >= 0; i--){
            for (int j = 0; j < spaceNumber; j++){
                Space occupyingCar = floorPlan[i][j];
                if (!occupyingCar.isTaken()){
                    sb.append("X");
                } else if (occupyingCar.isTaken() && occupyingCar.getOccupyingCarSize() == Size.SMALL){
                    sb.append("S");
                } else{
                    sb.append("L");
                }
                if (j != (spaceNumber - 1)) sb.append(" ");
            }
            if (i != 0) sb.append("\n");
        }
        return sb.toString();
    }
}