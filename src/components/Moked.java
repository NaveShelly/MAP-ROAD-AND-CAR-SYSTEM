package components;
import utilities.NoReports;
import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class Moked {
    static int Objectcount;
    private BufferedWriter reports;
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
public Moked()
{
    Objectcount=0;
}
    public static void readFromFile(Vehicle vehicle) {
        lock.readLock().lock();
        try
        {
            String filetext="";
            File file=new File("reports.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuilder sb=new StringBuilder();
            while(filetext !=null)
            {
                filetext=br.readLine();
                if(filetext!=null) {
                    sb.append(filetext);
                    sb.append("\n");
                }
            }
            vehicle.setState(new NoReports());
        }
        catch(Exception ex){} finally {
            lock.readLock().unlock();
        }
    }
    public synchronized void writeToFile(Vehicle vehicle) {
            try {
                reports = new BufferedWriter(new FileWriter("reports.txt", true));
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            lock.writeLock().lock();
            try {
                reports.append("Reports id: " + Objectcount + " Car id: " + String.valueOf(vehicle.getId()) + " Time: " + vehicle.getTimeFromRouteStart() + " " + "\n");
                Objectcount++;
                reports.close();
            } catch (IOException e) {
            } finally {
                lock.writeLock().unlock();
            }
    }
}
