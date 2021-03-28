package utilities;
import components.Junction;
import components.LightedJunction;
import java.util.Random;
public interface JFactory {
    static Junction getJunction(String x)
    {
        if(x=="city") {
            return new LightedJunction();
        }
        if(x=="country")
        {
            if(new Random().nextInt(2)==1)
                return new LightedJunction();
            else
                return new Junction();
        }
        return null;
    }
}
