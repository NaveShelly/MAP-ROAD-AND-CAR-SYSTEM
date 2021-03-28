package utilities;
import components.Driving;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class GUI extends JFrame {
    private myPanel tmp;
    private Driving driving;
    private int numOfJuncs;
    private int numOfVeh;
    public void setNumOfJuncs(int x){this.numOfJuncs=x;}
    public void setNumOfVeh(int x){this.numOfVeh=x;}
    public int getNumOfJuncs(){return this.numOfJuncs;}
    public int getNumOfVeh(){return this.numOfVeh;}
    public GUI(String str) {
        super(str);
        tmp = new myPanel(this);
        tmp.setBorder(BorderFactory.createEmptyBorder(600,30,0,30));
        JMenuBar MenuBar=new JMenuBar();
        JMenu Filee=new JMenu("File");
        JMenuItem City=new JMenuItem("City");
        JMenuItem Country=new JMenuItem("Country");
        JMenuItem reports=new JMenuItem("reports");
        JMenu mapBuild=new JMenu("Build a Map");
        JMenu CloneCar=new JMenu("Clone a Car");
        JMenu Report=new JMenu("Report");
        Report.add(reports);
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String filetext="",filecontent="";
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
                    filecontent=sb.toString();
                    JOptionPane.showMessageDialog(null,"Reports :\n"+filecontent);
                }
                catch(Exception ex){}
            }
        });
        JMenuItem CloneAcar=new JMenuItem("Clone a Car");
        CloneCar.add(CloneAcar);
        mapBuild.add(City);
        mapBuild.add(Country);
        JMenu Background=new JMenu("Background");
        JMenu VehicelsColor=new JMenu(" Vehicels color");
        JMenu help=new JMenu("Help");
        JMenuItem Helps=new JMenuItem("Help");
        JMenuItem vehblue=new JMenuItem("Blue");
        CloneAcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JWindow x=new JWindow();
                x.setLayout(new FlowLayout());
                x.setSize(500,300);
                x.setLocation(40,70);
                JLabel selectid=new JLabel("Select id :");
                String arr[]=new String[driving.getVehicles().size()];
                for(int i=0;i<driving.getVehicles().size();i++)
                    arr[i]=String.valueOf(i);
                JComboBox ChooseNum = new JComboBox(arr);
                x.add(selectid);
                x.add(ChooseNum);
                JButton ok=new JButton("Ok");
                JButton Cancel=new JButton("Cancel");
                x.add(ok);
                x.add(Cancel);
                x.pack();
                x.setVisible(true);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tmp.getDriving().newVehicle(parseInt(ChooseNum.getSelectedItem().toString()));
                        x.setVisible(false);
                    }
                });
                Cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.setVisible(false);
                    }
                });
            }
        });
        City.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tmp.setDriving("city",12);
            }
        });
        Country.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tmp.setDriving("country",6);
            }
        });
        vehblue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(driving!=null)
                {
                    tmp.setColor(Color.BLUE);
                    tmp.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Create Road System Firsts");
                }
            }
        });
        JMenuItem vehmgen=new JMenuItem("Magenta");
        vehmgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(driving!=null)
                {
                    tmp.setColor(Color.magenta);
                    tmp.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Create Road System Firsts");
                }
            }
        });
        JMenuItem vehorange=new JMenuItem("Orange");
        vehorange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(driving!=null)
                {
                    tmp.setColor(Color.orange);
                    tmp.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Create Road System Firsts");
                }
            }
        });
        JMenuItem vehrand=new JMenuItem("Random");
        vehrand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(driving!=null)
                {
                    Random rand = new Random();
                    int x = rand.nextInt(3);
                    if (x == 0) {
                        tmp.setColor(Color.BLUE);
                    }
                    if (x == 1)
                    {
                        tmp.setColor(Color.magenta);
                    }
                    if(x==2)
                    {
                        tmp.setColor(Color.orange);
                    }
                    tmp.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Create Road System Firsts");
                }
            }
        });
        JMenuItem Exit=new JMenuItem("Exit");
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenuItem Blue=new JMenuItem("Blue");
        Blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tmp.setBackground(Color.BLUE);
            }
        });
        Helps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog( null,"Home Work 3\n Gui @ Threads");
            }
        });
        JMenuItem None=new JMenuItem("None");
        None.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tmp.setBackground(Color.WHITE);
            }
        });
        Filee.add(Exit);
        help.add(Helps);
        Background.add(Blue);
        Background.add(None);
        VehicelsColor.add(vehblue);
        VehicelsColor.add(vehmgen);
        VehicelsColor.add(vehorange);
        VehicelsColor.add(vehrand);
        MenuBar.add(Filee);
        MenuBar.add(Background);
        MenuBar.add(VehicelsColor);
        MenuBar.add(mapBuild);
        MenuBar.add(CloneCar);
        MenuBar.add(Report);
        MenuBar.add(help);
        this.setJMenuBar(MenuBar);
        add(tmp);
    }
    public Driving getDriving(){return this.driving;}
    public void setDriving(Driving tmp){this.driving=tmp;}
}
