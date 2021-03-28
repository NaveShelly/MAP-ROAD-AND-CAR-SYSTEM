package utilities;
public interface Factory {
    static Factory getFactory(int x)
    {
        if(x==4) {
            return new Fourwheels();
        }
        if(x==2)
        {
            return new Twowheels();
        }
        if(x==10)
        {
            return new Tenwheels();
        }
        return null;
    }
    VehicleType getVehicle(String y);
}