package utilities;
import components.*;

import java.util.ArrayList;
import java.util.Random;

public class CityBuilder {
    private int junctionsNum;
    private ArrayList<Junction> junctions;
    private ArrayList<Road> roads;
    private ArrayList<TrafficLights> lights;
    public CityBuilder(){junctionsNum=12;};
    public void setJunctions(){
        junctions=new ArrayList<Junction>();
        System.out.println("\n================= CREATING JUNCTIONS=================");
        //create lighted and non-lighted junctions
        for (int i=0; i<junctionsNum; i++) {
                LightedJunction junc=(LightedJunction)JFactory.getJunction("city");
                junctions.add(junc);
        }
        System.out.println("\n================= GAME MAP HAS BEEN CREATED =================\n");
    }
    public void setRoads()
    {
        roads=new ArrayList<Road>();
        System.out.println("\n================= CREATING ROADS=================");
        for (int i=0; i<junctions.size();i++) {
            for (int j=0; j<junctions.size();j++) {
                if(i==j) {

                    continue;
                }
                roads.add(new Road(junctions.get(i), junctions.get(j)));
                for(int v=0;v<roads.get(i).getVehicleTypes().length;v++)
                    if(roads.get(i).getVehicleTypes()[v]==VehicleType.truck||VehicleType.semitrailer==roads.get(i).getVehicleTypes()[v])
                    {
                        int []arr={2,4,10};
                        int num=arr[new Random().nextInt(arr.length)];
                        if(num==2)
                        {
                            roads.get(i).getVehicleTypes()[v]=Factory.getFactory(2).getVehicle(("fast"));
                        }
                        if(num==4)
                        {
                            String []tmp={"public","private"};
                            String tmp1=tmp[new Random().nextInt(tmp.length)];
                            roads.get(i).getVehicleTypes()[v]=Factory.getFactory(4).getVehicle(tmp1);
                        }
                        if(num==10)
                        {
                            roads.get(i).getVehicleTypes()[v]=Factory.getFactory(10).getVehicle("public");
                        }
                    }
            }
        }
}
    public void setLights()
    {
        lights=new ArrayList<TrafficLights>();
        for (int i=0; i<junctionsNum; i++) {
           lights.add(((LightedJunction)junctions.get(i)).getLights());
        }
    }
    public Map Build()
    {
        setJunctions();
        setRoads();
        setLights();
        return new Map(junctions,roads,lights);
    }
}
