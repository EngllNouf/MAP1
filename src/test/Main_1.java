package test;


import javax.swing.*;
import java.awt.*;
import data.RoutingData;
import data.RoutingService;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import org.slf4j.Marker;
import static sun.jvm.hotspot.HelloWorld.e;
import waypoint.EventWaypoint;
import waypoint.MyWaypoint;
import waypoint.WaypointRender;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;


public class Main_1 extends javax.swing.JFrame {

    private final Set<MyWaypoint> waypoints = new HashSet<>();
    private List<RoutingData> routingData = new ArrayList<>();
    private EventWaypoint event;
    private Point mousePosition;
    

    public Main_1() {
        initComponents();
        init();
        
       
        
    }

    private void init() {
       Component[] components = new Component[]{new PanelTest(1), new PanelTest(2), new PanelTest(3), new PanelTest(4)};
        panelSlider1.setSliderComponent(components);
        progressIndicator2.initSlider(panelSlider1);
        
        
        
        
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(21.4488312,39.8706231);
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(12);
        
         
        
        

        //  Create event mouse move
        MouseInputListener mm = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mm);
        jXMapViewer.addMouseMotionListener(mm);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
        event = getEvent();
 
     
    

        
    }
 
    

    private void addWaypoint(MyWaypoint waypoint) {
        for (MyWaypoint d : waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        Iterator<MyWaypoint> iter = waypoints.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPointType() == waypoint.getPointType()) {
                iter.remove();
            }
        }
        waypoints.add(waypoint);
        initWaypoint();
    }


    private void initWaypoint() {
        WaypointPainter<MyWaypoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jXMapViewer.setOverlayPainter(wp);
        for (MyWaypoint d : waypoints) {
            jXMapViewer.add(d.getButton());
        }
        //  Routing Data
        if (waypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;
            for (MyWaypoint w : waypoints) {
                if (w.getPointType() == MyWaypoint.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == MyWaypoint.PointType.END) {
                    end = w.getPosition();
                }
            }
            if (start != null && end != null) {
                routingData = RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());

            } else {
                routingData.clear();
            }
            jXMapViewer.setRoutingData(routingData);
        }
    }

    private void clearWaypoint() {
        for (MyWaypoint d : waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        routingData.clear();
        waypoints.clear();
        initWaypoint();
    }

    private EventWaypoint getEvent() {
        return new EventWaypoint() {
            @Override
            public void selected(MyWaypoint waypoint) {
                JOptionPane.showMessageDialog(Main_1.this, waypoint.getName());
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuStart = new javax.swing.JMenuItem();
        menuEnd = new javax.swing.JMenuItem();
        jXMapViewer = new data.JXMapViewerCustom();
        cmdAdd = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();
        progressIndicator2 = new raven.progressindicator.ProgressIndicator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        panelSlider1 = new raven.progressindicator.PanelSlider();
        comboMapType = new javax.swing.JComboBox<>();

        menuStart.setText("Start");
        menuStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStartActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuStart);

        menuEnd.setText("End");
        menuEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEndActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuEnd);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jXMapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jXMapViewerMouseReleased(evt);
            }
        });

        cmdAdd.setText("Add Waypoint");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdClear.setText("Clear Waypoint");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        progressIndicator2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "step 1", "step 2", "step 3", "step 4" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        progressIndicator2.setProgress(0.0F);
        progressIndicator2.setProgressColorGradient(java.awt.Color.red);

        jButton1.setText("next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        panelSlider1.setOpaque(false);

        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Stree", "Virtual Earth", "Hybrid", "Satellite" }));
        comboMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMapTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXMapViewerLayout.createSequentialGroup()
                        .addComponent(cmdAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressIndicator2, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jXMapViewerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(802, 802, 802)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addComponent(panelSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXMapViewerLayout.createSequentialGroup()
                        .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jXMapViewerLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmdClear)
                                    .addComponent(cmdAdd)))
                            .addGroup(jXMapViewerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(progressIndicator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXMapViewerLayout.createSequentialGroup()
                        .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGap(18, 18, 18)
                .addComponent(panelSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jXMapViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void comboMapTypeActionPerformed(java.awt.event.ActionEvent evt) {                                             
        TileFactoryInfo info;
        int index = comboMapType.getSelectedIndex();
        if (index == 0) {
            info = new OSMTileFactoryInfo();
        } else if (index == 1) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        } else if (index == 2) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        } else {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
    }                                            

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
    waypoints.add(new MyWaypoint("Test 001", new GeoPosition(21.6183122,39.1093823)));
    
    initWaypoint();
    }                                      

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        clearWaypoint();
    }                                        

    private void menuStartActionPerformed(java.awt.event.ActionEvent evt) {                                          
        GeoPosition geop = jXMapViewer.convertPointToGeoPosition(mousePosition);
        MyWaypoint wayPoint = new MyWaypoint("Start Location", MyWaypoint.PointType.START, event, new GeoPosition(geop.getLatitude(), geop.getLongitude()));
        addWaypoint(wayPoint);
    }                                         

    private void menuEndActionPerformed(java.awt.event.ActionEvent evt) {                                        
        GeoPosition geop = jXMapViewer.convertPointToGeoPosition(mousePosition);
        MyWaypoint wayPoint = new MyWaypoint("End Location", MyWaypoint.PointType.END, event, new GeoPosition(geop.getLatitude(), geop.getLongitude()));
        addWaypoint(wayPoint);
    }                                       

    private void jXMapViewerMouseReleased(java.awt.event.MouseEvent evt) {                                          
        if (SwingUtilities.isRightMouseButton(evt)) {
            mousePosition = evt.getPoint();
            jPopupMenu1.show(jXMapViewer, evt.getX(), evt.getY());
        }
    }                                         

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       progressIndicator2.previous();
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       progressIndicator2.next();
    }                                        

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdClear;
    private javax.swing.JComboBox<String> comboMapType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private data.JXMapViewerCustom jXMapViewer;
    private javax.swing.JMenuItem menuEnd;
    private javax.swing.JMenuItem menuStart;
    private raven.progressindicator.PanelSlider panelSlider1;
    private raven.progressindicator.ProgressIndicator progressIndicator2;
    // End of variables declaration                   
}