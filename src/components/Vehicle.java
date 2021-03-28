package components;
import utilities.*;
import java.util.Observable;
/**
 *@author Nave shelly 312190796 Koral Moyal 318855038
 *
 */
public class Vehicle extends Observable implements Cloneable,Utilities, Timer,Runnable{
	private int id;
	private double xp;
	private double yp;
	private VehicleType vehicleType;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private int timeFromRouteStart;
	private static int objectsCount=1;
	private int timeOnCurrentPart;
	private Road lastRoad;
	private String status;
	private boolean flag;
	private BigBrother supervisor;
	private utilities.State state;
	public void setState(utilities.State state){this.state=state;}
	public BigBrother getSupervisor(){return supervisor;}
	public utilities.State getstate(){return this.state;}
	public Vehicle (Road currentLocation) {
		id=objectsCount++;
		vehicleType=currentLocation.getVehicleTypes()[getRandomInt(0,currentLocation.getVehicleTypes().length-1)];
		System.out.println();
		successMessage(this.toString());
		currentRoute=new Route(currentLocation, this); //creates a new route for the vehicle and checks it in
		lastRoad=currentLocation;
		status=null;
		xp=lastRoad.startJunction.getX();
		yp=lastRoad.startJunction.getY();
	}
	public Vehicle (Vehicle tmp) {
		vehicleType=tmp.vehicleType;
		System.out.println();
		successMessage(this.toString());
		tmp.getLastRoad().getStartJunction().getExitingRoads().get(getRandomInt(0,tmp.getLastRoad().getStartJunction().getExitingRoads().size()-1));
		currentRoute=new Route(tmp.getLastRoad(), this); //creates a new route for the vehicle and checks it in
		lastRoad=tmp.getLastRoad();
		status=null;
		xp=lastRoad.startJunction.getX();
		yp=lastRoad.startJunction.getY();
	}
	public double getx(){return xp;}
	public double gety(){return yp;}
	public Object Clone(){
		return new Vehicle(this);
	}
	/**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * @return the currentRoute
	 */
	public Route getCurrentRoute() {
		return currentRoute;
	}
	/**
	 * @param currentRoute the currentRoute to set
	 */
	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}
	/**
	 * @return the currentRoutePart
	 */
	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}
	/**
	 * @param currentRoutePart the currentRoutePart to set
	 */
	public void setCurrentRoutePart(RouteParts currentRoutePart) {
		this.currentRoutePart = currentRoutePart;
	}
	/**
	 * @return the timeFromRouteStart
	 */
	public int getTimeFromRouteStart() {
		return timeFromRouteStart;
	}
	/**
	 * @param timeFromRouteStart the timeFromRouteStart to set
	 */
	public void setTimeFromRouteStart(int timeFromRouteStart) {
		this.timeFromRouteStart = timeFromRouteStart;
	}
	/**
	 * @return the timeOnCurrentPart
	 */
	public int getTimeOnCurrentPart() {
		return timeOnCurrentPart;
	}
	/**
	 * @param timeOnCurrentPart the timeOnCurrentPart to set
	 */
	public void setTimeOnCurrentPart(int timeOnCurrentPart) {
		this.timeOnCurrentPart = timeOnCurrentPart;
	}
	/**
	 * @return the lastRoad
	 */
	public Road getLastRoad() {
		return lastRoad;
	}
	/**
	 * @param lastRoad the lastRoad to set
	 */
	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the objectsCount
	 */
	public void run() {
			supervisor=BigBrother.getInstance();
			flag=true;
			while (flag==true) {
			try {
					Thread.sleep((long) this.getCurrentRoutePart().calcEstimatedTime(this));
					System.out.println(this);
					incrementDrivingTime();
					System.out.println();
				} catch (InterruptedException e) {
				}
				if(flag==false) {
					synchronized (this)
					{
						try{
							this.wait();
						}catch (InterruptedException e){}
					}
				}
			}
	}
	public static int getObjectsCount() {
		return objectsCount;
	}
	@Override
	public void incrementDrivingTime() {
		timeFromRouteStart++;
		timeOnCurrentPart++;
		move();
	}
	/**controls the vehicle moving from one route part to the next one
	 * 
	 */
	public void move()
		 {
			if (currentRoutePart.canLeave(this)) {
				currentRoutePart.checkOut(this);
				currentRoute.findNextPart(this).checkIn(this);
			} else {
				currentRoutePart.stayOnCurrentPart(this);
			}
			if(currentRoutePart instanceof Junction)
			{
				setChanged();
				notifyObservers();
			}
	}
		@Override
	public String toString() {
		return new String("Vehicle "+id+": "+ getVehicleType().name()+", average speed: "+getVehicleType().getAverageSpeed());
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false; 
	    if (getClass() != obj.getClass()) return false; 
	    if (! super.equals(obj)) return false;
		Vehicle other=(Vehicle)obj;
		if (this.currentRoute!=other.currentRoute||
			this.currentRoutePart!=other.currentRoutePart||
			this.id!=other.id||
			this.lastRoad!=other.lastRoad||
			this.status!=other.status||
			this.timeFromRouteStart!=other.timeFromRouteStart||
			this.timeOnCurrentPart!=other.timeOnCurrentPart||
			this.vehicleType!=other.vehicleType)
				return false;
		return true;
	}
	/**
	 * @param objectsCount the objectsCount to set
	 */
	public static void setObjectsCount(int objectsCount) {
		Vehicle.objectsCount = objectsCount;
	}
	public void setLocation()
	{
		double s=this.timeOnCurrentPart*Math.min(this.getVehicleType().getAverageSpeed(),lastRoad.getMaxSpeed());
		if(s==0) {
			this.xp = this.getLastRoad().getStartJunction().getX();
			this.yp =this.getLastRoad().getStartJunction().getY();
			return;
		}
		double L=s;
		double K=this.getLastRoad().getLength()-L;
		this.xp =(this.getLastRoad().getStartJunction().getX()*L+this.getLastRoad().getEndJunction().getX()*K)/(L+K);
		this.yp =(this.getLastRoad().getStartJunction().getY()*L+this.getLastRoad().getEndJunction().getY()*K)/(L+K);
	}
	public void changeFlag()
	{
		if(flag==true){
			flag=false;
			return;}
		if(flag==false)
			flag=true;
	}
}
