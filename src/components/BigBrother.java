package components;

import java.util.Observable;
import java.util.Observer;

public class BigBrother implements Observer {
        static private volatile BigBrother instance = null;
        private Moked Reports;
        private BigBrother(){
            Reports=new Moked();
        }
        public static BigBrother getInstance(){
            if (instance == null)
                synchronized(BigBrother.class){
                    if (instance == null)
                        instance = new BigBrother();
                }
            return instance;
        }
        public Moked getReports(){return Reports;}
        public void update(Observable o,Object obj)
        {
           Reports.writeToFile((Vehicle)o);
        }
}