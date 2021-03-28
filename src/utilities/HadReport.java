package utilities;

import components.Vehicle;
public class HadReport implements State {
    public void setState(Vehicle vehicle) {
        System.out.println("Had Report");
        vehicle.setState(this);
    }

    public String getState(){
        return "Had Report";
    }
}
