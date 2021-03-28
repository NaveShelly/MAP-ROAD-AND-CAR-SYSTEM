/**
 *
 */
package components;
import java.util.ArrayList;
import java.util.Random;

import utilities.*;

/**Traffic control game
 *@author Nave shelly 312190796 Koral Moyal 318855038
 *
 */
public class Driving extends Thread implements  Utilities, Timer{
	private Map map;
	private ArrayList<Vehicle> vehicles;
	private int drivingTime;
	boolean flag;
	Mediator mediator;
	private ArrayList<Timer> allTimedElements;
	/**Constructor
	 * @param junctionsNum quantity of junctions
	 * @param numOfVehicles quantity of vehicles
	 */
	public Driving(int junctionsNum, int numOfVehicles) {
		super("Driving");
		vehicles=new ArrayList<Vehicle>();
		allTimedElements=new ArrayList<Timer>();
		drivingTime=0;
		map=new Map(junctionsNum);
		System.out.println("\n================= CREATING VEHICLES =================");

		while(vehicles.size()<numOfVehicles) {
			Road temp=map.getRoads().get(getRandomInt(0,map.getRoads().size()));//random road from the map
			if( temp.getEnabled())
				vehicles.add(new Vehicle(temp));
		}

		allTimedElements.addAll(vehicles);
		for (TrafficLights light: map.getLights()) {
			if (light.getTrafficLightsOn()) {
				allTimedElements.add(light);
			}
		}
	}
	public Driving(String str,int numOfVehicles)
    {
        super("Driving");
        if(str=="city") {
            vehicles = new ArrayList<Vehicle>();
            allTimedElements = new ArrayList<Timer>();
            drivingTime = 0;
            CityBuilder tmp = new CityBuilder();
            this.map = tmp.Build();
            while (vehicles.size() < numOfVehicles) {
                Road temp = map.getRoads().get(getRandomInt(0, map.getRoads().size()));//random road from the map
                if (temp.getEnabled()) {
					if(new Random().nextInt(2)==0) {
						temp.setMaxSpeed((int) (temp.getMaxSpeed() * 1.3));
					}
                    Vehicle tmp2 = new Vehicle(temp);
                    if(new Random().nextInt(2)==0)
                    if(tmp2.getVehicleType()==VehicleType.truck||VehicleType.semitrailer==tmp2.getVehicleType())
                    {
                        int []arr={2,4,10};
                        int num=arr[new Random().nextInt(arr.length)];
                        if(num==2)
                        {
                            String []tmp3={"fast","slow"};
                            String tmp1=tmp3[new Random().nextInt(tmp3.length)];
                            tmp2.setVehicleType(Factory.getFactory(2).getVehicle(tmp1));
                        }
                        if(num==4)
                        {
                            String []tmp3={"public","private"};
                            String tmp1=tmp3[new Random().nextInt(tmp3.length)];
                            tmp2.setVehicleType(Factory.getFactory(4).getVehicle(tmp1));
                        }
                        if(num==10)
                        {
                            tmp2.setVehicleType(Factory.getFactory(10).getVehicle("public"));
                        }
                    }
                    vehicles.add(new Vehicle(temp));
                }
            }
            allTimedElements.addAll(vehicles);
            for (TrafficLights light : map.getLights()) {
                    allTimedElements.add(light);
            }
        }
        if(str=="country")
        {
                vehicles = new ArrayList<Vehicle>();
                allTimedElements = new ArrayList<Timer>();
                drivingTime = 0;
                CountryBuilder tmp = new CountryBuilder();
                this.map = tmp.Build();
                while (vehicles.size() < numOfVehicles) {
                    Road temp = map.getRoads().get(getRandomInt(0, map.getRoads().size()));//random road from the map
                    if (temp.getEnabled()) {
                        Vehicle tmp2 = new Vehicle(temp);
                        if(tmp2.getVehicleType()==VehicleType.truck||VehicleType.semitrailer==tmp2.getVehicleType())
                        {
                            int []arr={2,4,10};
                            int num=arr[new Random().nextInt(arr.length)];
                            if(num==2)
                            {
                                String []tmp3={"fast","slow"};
                                String tmp1=tmp3[new Random().nextInt(tmp3.length)];
                                tmp2.setVehicleType(Factory.getFactory(2).getVehicle(tmp1));
                            }
                            if(num==4)
                            {
                                String []tmp3={"public","private","work"};
                                String tmp1=tmp3[new Random().nextInt(tmp3.length)];
                                tmp2.setVehicleType(Factory.getFactory(4).getVehicle(tmp1));
                            }
                            if(num==10)
                            {
                                tmp2.setVehicleType(Factory.getFactory(10).getVehicle("work"));
                            }
                        }
                        vehicles.add(new Vehicle(temp));
                    }
                }
                for(Vehicle veh:vehicles)
                	allTimedElements.add(veh);
                for (TrafficLights light : map.getLights()) {
                    allTimedElements.add(light);
                }
            }
    }
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * @return the vehicles
	 */
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	/**
	 * @return the drivingTime
	 */
	public int getDrivingTime() {
		return drivingTime;
	}
	/**
	 * @param drivingTime the drivingTime to set
	 */
	public void setDrivingTime(int drivingTime) {
		this.drivingTime = drivingTime;
	}
	/**
	 * @return the allTimedElements
	 */
	public ArrayList<Timer> getAllTimedElements() {
		return allTimedElements;
	}

	/**
	 * @param allTimedElements the allTimedElements to set
	 */
	public void setAllTimedElements(ArrayList<Timer> allTimedElements) {
		this.allTimedElements = allTimedElements;
	}

	/**method run the Thread and calling drive
	 *
	 */
	public void run()
	{
			System.out.println("\n================= START DRIVING=================");
			drivingTime = 0;
			flag = true;
			while (true) {
				try {
					sleep((long) 100);
					drive();
				} catch (InterruptedException e) {
				}
				if(flag==false)
				{
					synchronized (this)
					{
						try {
							this.wait();
						}catch (InterruptedException e){};
					}
				}
			}
	}
	/**method runs the game for given quantity of turns
	 * @param
	 */
	public void newVehicle(int id)
	{
		Vehicle v=(Vehicle)vehicles.get(id).Clone();
		vehicles.add(vehicles.get(id));
		vehicles.get(id).setId(Vehicle.getObjectsCount());
		Vehicle.setObjectsCount(Vehicle.getObjectsCount()+1);
		vehicles.set(id,v);
		if(flag==true)
		{
			vehicles.get(id).changeFlag();
			vehicles.get(id).run();
		}
		allTimedElements.add(vehicles.get(id));
	}
	public void drive() {
			incrementDrivingTime();
	}
	@Override
	public void incrementDrivingTime() {
			System.out.println("\n***************TURN " + drivingTime++ + "***************");
			drivingTime++;
			System.out.println();
	}
	@Override
	public String toString() {
		return "Driving [map=" + map + ", vehicles=" + vehicles + ", drivingTime=" + drivingTime + ", allTimedElements="
				+ allTimedElements + "]";
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
