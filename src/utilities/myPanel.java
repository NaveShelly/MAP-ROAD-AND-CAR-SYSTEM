package utilities;
import components.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
/**
 * @author Nave shelly 312190796 Koral Moyal 318855038
 *
 */
public class myPanel extends JPanel
{
    private JButton createRoadSystemButton;
    private Driving driving;
    private Color ColorCar;
    private boolean flag;
    private int infoclicks;
    private boolean resumeflag;
    Thread tmp;
    public void setColor(Color x)
    {
        ColorCar=x;
    }
public myPanel(GUI Main)
    {
        this.driving=Main.getDriving();
        infoclicks=0;
        resumeflag=true;
        setSize(new Dimension(1000,800));
        validate();
        JButton createRoadSystemButton=new JButton("Create road System");
        JButton Start=new JButton("Start");
        JButton Stop=new JButton("Stop");
        JButton Resume=new JButton("Resume");
        JButton info=new JButton("Info");
        JButton Exit=new JButton("Exit");
        Exit.setBackground(Color.lightGray);
        createRoadSystemButton.setBounds(3500, 371,150,30);
        Start.setBounds(3500, 371,150,30);
        Stop.setBounds(3500, 371,150,30);
        Resume.setBounds(3500, 371,150,30);
        info.setBounds(3500, 371,150,30);
        createRoadSystemButton.setBackground(Color.lightGray);
        Start.setBackground(Color.lightGray);
        Stop.setBackground(Color.lightGray);
        Resume.setBackground(Color.lightGray);
        info.setBackground(Color.lightGray);
        Start.setMnemonic(KeyEvent.VK_G);
        Stop.setMnemonic(KeyEvent.VK_G);
        Resume.setMnemonic(KeyEvent.VK_G);
        info.setMnemonic(KeyEvent.VK_G);
        add(createRoadSystemButton);
        add(Start);
        add(Stop);
        add(Resume);
        add(info);
        add(Exit);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<driving.getVehicles().size();i++)
                {
                    if(driving.getVehicles().get(i).getstate() instanceof HadReport)
                    {
                        JOptionPane.showMessageDialog( null,"Try Later there Reports open\n");
                        return;
                    }

                }
                System.exit(0);
            }
        });
        tmp=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(flag==false) {
                        synchronized (tmp)
                        {
                            try{
                                tmp.wait();
                            }catch (InterruptedException d){};
                        }
                    }
                }
            }
        });
        createRoadSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSlider NumOfJunctions=new JSlider(3,20,3);
                NumOfJunctions.setLabelTable(NumOfJunctions.createStandardLabels(1));
                NumOfJunctions.setPaintLabels(true);
                NumOfJunctions.setPaintTicks(true);
                NumOfJunctions.setPaintTrack(true);
                NumOfJunctions.setMajorTickSpacing(1);
                NumOfJunctions.setMinorTickSpacing(10);
                JSlider NumofVeh=new JSlider(0,50,0);
                NumofVeh.setPaintLabels(true);
                NumofVeh.setPaintTicks(true);
                NumofVeh.setMajorTickSpacing(5);
                NumofVeh.setMinorTickSpacing(1);
                JFrame CreateFrame=new JFrame("Create road system");
                CreateFrame.setLayout(new FlowLayout());
                JPanel Junctionslider;
                Junctionslider=new JPanel();
                JPanel strNumberofJunc;
                strNumberofJunc=new JPanel();
                strNumberofJunc.add(new JLabel("Number of junctions"));
                Junctionslider.setLayout(new GridLayout(0, 1));
                JPanel Vehslider;
                Vehslider=new JPanel();
                Vehslider.setLayout(new GridLayout(0, 1));
                Junctionslider.add(NumOfJunctions,BorderLayout.CENTER);
                CreateFrame.add(strNumberofJunc);
                CreateFrame.add(Junctionslider);
                JPanel strNumberofVeh;
                strNumberofVeh=new JPanel();
                strNumberofVeh.add(new JLabel("Number of vehicles"));
                CreateFrame.add(strNumberofVeh);
                Vehslider.add(NumofVeh);
                CreateFrame.add(Vehslider);
                JPanel btns;
                btns=new JPanel();
                btns.setLayout(new GridLayout(1, 0));
                JButton ok;
                ok=new JButton("OK");
                ok.setMnemonic(KeyEvent.VK_G);
                ok.setBackground(Color.lightGray);
                JButton cancel;
                cancel=new JButton("Cancel");
                cancel.setBackground(Color.lightGray);
                cancel.setMnemonic(KeyEvent.VK_G);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CreateFrame.dispose();
                    }
                });
                btns.setLayout(new GridLayout(1, 0,8,3));
                btns.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
                btns.add(ok);
                btns.add(cancel);
                CreateFrame.add(btns);
                CreateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                CreateFrame.setLayout(new GridLayout(0, 1));
                CreateFrame.setResizable(false);
                CreateFrame.setSize(600, 300);
                CreateFrame.setVisible(true);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Main.setNumOfJuncs(NumOfJunctions.getValue());
                        Main.setNumOfVeh(NumofVeh.getValue());
                        driving=new Driving(Main.getNumOfJuncs(),Main.getNumOfVeh());
                        Main.setDriving(driving);
                        repaint();
                        CreateFrame.dispose();
                    }
                });
             }
        });
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent d) {
                if(driving!=null)
                {
                    flag=true;
                    tmp.start();
                    driving.start();
                    for(Timer t:driving.getAllTimedElements())
                    {
                        if (t instanceof Vehicle) {
                           new Thread((Vehicle) t).start();
                        }
                        if (t instanceof TrafficLights)
                        {
                            ((TrafficLights) t).start();

                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog( null,"Create Road System Firsts");
                }
        }
    });
         Stop.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 flag = false;
                 resumeflag=true;
                 driving.changeFlag();
                 for (Timer t : driving.getAllTimedElements()) {
                     if (t instanceof Vehicle) {
                         ((Vehicle) t).changeFlag();
                     }
                     if (t instanceof TrafficLights) {
                         ((TrafficLights) t).changeFlag();
                     }
                 }

             }
        });
        Resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resumeflag == true) {

                    flag = true;
                    synchronized (tmp) {
                        tmp.notify();
                    }
                    driving.changeFlag();
                    synchronized (driving) {
                        driving.notify();
                    }
                    for (Timer t : driving.getAllTimedElements()) {
                        if (t instanceof Vehicle) {
                            synchronized (t) {
                                ((Vehicle) t).changeFlag();
                                t.notify();
                            }
                        }
                        if (t instanceof TrafficLights) {
                            synchronized (t) {
                                ((TrafficLights) t).changeFlag();
                                t.notify();
                            }
                        }
                    }
                }
                resumeflag=false;
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JWindow x=new JWindow();
                x.setSize(500,300);
                x.setLocation(40,70);
                JTable VehicleTable=new JTable(5,5);
                if(driving!=null&&infoclicks==0)
                {
                    infoclicks++;
                    DefaultTableModel  tablemodel;
                    tablemodel=(DefaultTableModel) VehicleTable.getModel();
                    VehicleTable.setBounds(100, 100, 50, 50);
                    x.add(VehicleTable,BorderLayout.LINE_START);
                    tablemodel.setRowCount(driving.getVehicles().size());
                    tablemodel.setValueAt("Vihicle #", 0, 0);
                    tablemodel.setValueAt("Type", 0, 1);
                    tablemodel.setValueAt("Location", 0, 2);
                    tablemodel.setValueAt("Time on Loc", 0, 3);
                    tablemodel.setValueAt("Speed", 0, 4);
                    for(int i=1;i<driving.getVehicles().size();i++)
                    {

                        tablemodel.setValueAt(driving.getVehicles().get(i).getId(), i, 0);
                        tablemodel.setValueAt(driving.getVehicles().get(i).getVehicleType(), i, 1);
                        tablemodel.setValueAt(driving.getVehicles().get(i).getLastRoad(), i, 2);
                        tablemodel.setValueAt(driving.getVehicles().get(i).getTimeOnCurrentPart(), i, 3);
                        tablemodel.setValueAt(driving.getVehicles().get(i).getLastRoad().getMaxSpeed(), i, 4);

                    }
                    x.setVisible(true);
                    x.pack();
                }
                else
                {
                    x.setVisible(false);
                    Main.setVisible(true);
                    infoclicks=0;
                }
            }
        });
    }
        public void paintComponent (Graphics g) {
            super.paintComponent(g);
            if(driving!=null) {
                for (int i = 0; i < driving.getMap().getRoads().size(); i++) {
                        if (driving.getMap().getRoads().get(i).getEndJunction() instanceof LightedJunction && driving.getMap().getRoads().get(i) instanceof Road && driving.getMap().getRoads().get(i).getGreenLight() == true) {
                            g.setColor(Color.GREEN);
                            g.fillOval((int) driving.getMap().getRoads().get(i).getEndJunction().getX(), (int) driving.getMap().getRoads().get(i).getEndJunction().getY(), 10, 10);
                        } else if (driving.getMap().getRoads().get(i).getEndJunction() instanceof LightedJunction && driving.getMap().getRoads().get(i) instanceof Road && driving.getMap().getRoads().get(i).getGreenLight() == false) {
                            g.setColor(Color.RED);
                            g.fillOval((int) driving.getMap().getRoads().get(i).getEndJunction().getX(), (int) driving.getMap().getRoads().get(i).getEndJunction().getY(), 10, 10);
                        } else {
                            g.setColor(Color.BLACK);
                            g.fillOval((int) driving.getMap().getRoads().get(i).getEndJunction().getX(), (int) driving.getMap().getRoads().get(i).getEndJunction().getY(), 10, 10);
                        }
                }

                for (int i = 0; i < driving.getMap().getRoads().size(); i++) {
                    g.setColor(Color.black);
                    g.drawLine((int) driving.getMap().getRoads().get(i).getStartJunction().getX(), (int) driving.getMap().getRoads().get(i).getStartJunction().getY(), (int) driving.getMap().getRoads().get(i).getEndJunction().getX(), (int) driving.getMap().getRoads().get(i).getEndJunction().getY());
                    g.setColor(Color.GREEN);
                    int []xcord={(int) driving.getMap().getRoads().get(i).getEndJunction().getX(),(int) driving.getMap().getRoads().get(i).getEndJunction().getX()-10,(int) driving.getMap().getRoads().get(i).getEndJunction().getX()+10,(int) driving.getMap().getRoads().get(i).getEndJunction().getX()+2};
                    int []ycords={(int) driving.getMap().getRoads().get(i).getEndJunction().getY(),(int) driving.getMap().getRoads().get(i).getEndJunction().getY()+10,(int) driving.getMap().getRoads().get(i).getEndJunction().getY()+10,(int) driving.getMap().getRoads().get(i).getEndJunction().getY()+2};
                    g.fillPolygon(xcord,ycords,4);
                }
                for (int i = 0; i < driving.getVehicles().size(); i++) {
                    if(driving.getVehicles().get(i).getCurrentRoutePart() instanceof  Road) {
                        driving.getVehicles().get(i).setLastRoad((Road) driving.getVehicles().get(i).getCurrentRoutePart());
                    }
                    driving.getVehicles().get(i).setLocation();
                    int x1 = (int) driving.getVehicles().get(i).getx();
                    int y1 = (int) driving.getVehicles().get(i).gety();
                    int x2 = (int) driving.getVehicles().get(i).getLastRoad().getEndJunction().getX();
                    int y2 = (int) driving.getVehicles().get(i).getLastRoad().getEndJunction().getY();
                    int d = 10, h = 4;
                    int dx = x2 - x1, dy = y2 - y1, delta = 10;
                    double D = Math.sqrt(dx * dx + dy * dy);
                    double xm = delta, xn = xm, ym = h, yn = -h, x;
                    double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
                    double sin = dy / D, cos = dx / D;
                    x = xm * cos - ym * sin + x1;
                    xx = xm1 * cos - ym1 * sin + x1;
                    ym = xm * sin + ym * cos + y1;
                    ym1 = xm1 * sin + ym1 * cos + y1;
                    xm = x;
                    xm1 = xx;
                    x = xn * cos - yn * sin + x1;
                    xx = xn1 * cos - yn1 * sin + x1;
                    yn = xn * sin + yn * cos + y1;
                    yn1 = xn1 * sin + yn1 * cos + y1;
                    xn = x;
                    xn1 = xx;
                    int[] xpoints = {(int) xm1, (int) xn1, (int) xn, (int) xm};
                    int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
                    g.fillPolygon(xpoints, ypoints, 4);
                    if(ColorCar!=null)
                    {
                        g.setColor(ColorCar);
                    }
                    else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillOval((int) xm1 - 2, (int) ym1 - 2, 4, 4);
                    g.fillOval((int) xn1 - 2, (int) yn1 - 2, 4, 4);
                    g.fillOval((int) xm - 2, (int) ym - 2, 4, 4);
                    g.fillOval((int) xn - 2, (int) yn - 2, 4, 4);
                }
            }
        }
        public void setDriving(String str,int Numofveh){this.driving=new Driving(str,Numofveh);repaint();}
        public Driving getDriving() {
            return driving;
        }
    }
