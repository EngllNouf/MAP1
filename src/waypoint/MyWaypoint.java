package waypoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;


public class MyWaypoint extends DefaultWaypoint {

    public MyWaypoint(String start_Location, PointType pointType, EventWaypoint event, GeoPosition geoPosition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public MyWaypoint(String name, GeoPosition coord) {
        super(coord);
        this.name = name;
        initButton();
    }

    public MyWaypoint() {
    }

    private String name;
    private JButton button;

    private void initButton() {
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click : " + name); 

}
                
            
        });
    }

    public PointType getPointType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static enum PointType {
        START, END
    }
}
