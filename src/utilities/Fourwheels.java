package utilities;

import java.util.ArrayList;

public class Fourwheels implements Factory {
    ArrayList<VehicleType> Cars;

    public Fourwheels() {
        Cars = new ArrayList<VehicleType>();
        Cars.add(VehicleType.car);
        Cars.add(VehicleType.truck);
        Cars.add(VehicleType.bus);
    }

    public VehicleType getVehicle(String y) {
        if (y.equalsIgnoreCase("private")) {
            return Cars.get(0);
        }
        if (y.equalsIgnoreCase("work")) {
            return Cars.get(1);
        }
        if (y.equalsIgnoreCase("public")) {
            return Cars.get(2);
        }
        return null;
    }
}
