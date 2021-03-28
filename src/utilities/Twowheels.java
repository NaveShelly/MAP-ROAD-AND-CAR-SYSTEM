package utilities;

import java.util.ArrayList;

public class Twowheels implements Factory {
    ArrayList<VehicleType> Cars;

    public Twowheels() {
        Cars = new ArrayList<VehicleType>();
        Cars.add(VehicleType.bicycle);
        Cars.add(VehicleType.motorcycle);
    }
    public VehicleType getVehicle(String y) {
        if (y.equalsIgnoreCase("slow")) {
            return Cars.get(0);
        }
        if (y.equalsIgnoreCase("fast")) {
            return Cars.get(1);
        }
        return null;
    }
}
