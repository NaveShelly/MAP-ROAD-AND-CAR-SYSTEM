package utilities;

import components.Vehicle;

public class NoReports implements State {

    public void setState(Vehicle vehicle) {
        System.out.println("No Reports");
        vehicle.setState(this);
    }

    public String toString(){
        return "No Reports";
    }
}

