package components;
import utilities.State;

import java.util.ArrayList;
import java.util.Observer;

public class Mediator{
    public Mediator(){};
    public static void setstate(Vehicle vehicle ,State tmp)
    {
        vehicle.setState(tmp);
    };
}
