/**
 * 
 */
package components;
import java.util.ArrayList;

/**Represents the traffic lights with random choice of road that receives a green light
 * @author Nave shelly 312190796 Koral Moyal 318855038
 *
 */
public class RandomTrafficLights extends TrafficLights {
	
	/**Constructor
	 * @param roads
	 */
	public RandomTrafficLights(ArrayList<Road> roads) {
		super(roads);
	}

	@Override
	public void changeIndex() {
		
		this.setGreenLightIndex((getRandomInt(1,200))%this.getRoads().size());
		
	}
	
	@Override
	public String toString() {
		return new String("Random "+super.toString());
	}
	
	

}
