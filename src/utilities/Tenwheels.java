package utilities;

import java.util.ArrayList;

public class Tenwheels implements Factory {
    ArrayList<VehicleType> Cars;

    public Tenwheels() {
        Cars = new ArrayList<VehicleType>();
        Cars.add(VehicleType.tram);
        Cars.add(VehicleType.semitrailer);
    }

    public VehicleType getVehicle(String y) {
        if (y.equalsIgnoreCase("public")) {
            return Cars.get(0);
        }
        if (y.equalsIgnoreCase("work")) {
            return Cars.get(1);
        }
        return null;
    }
}
