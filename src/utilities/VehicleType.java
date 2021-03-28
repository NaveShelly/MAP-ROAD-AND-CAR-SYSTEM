/**
 * 
 */
package utilities;


import java.util.ArrayList;

/**
 * @author Nave shelly 312190796 Koral Moyal 318855038
 *
 */
public enum VehicleType {
	car(90), bus(60), bicycle(40), motorcycle(120), truck(80), tram(50), semitrailer(85);
	
	
	private int averageSpeed;
	
	
	VehicleType(int speed) {
		averageSpeed=speed; 
		
	}

	public int getAverageSpeed() {
		return averageSpeed;
	}
}
