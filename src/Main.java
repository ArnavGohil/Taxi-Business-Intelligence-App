
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author arnavgohil
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Main() {
        initComponents();
        showDate();
        showTime();
        //showTrips()
        showWeather();
        showPredictions();
    }

    String day, hour, month, hi, ws, temp, wc;
    int l ;

    void showDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy"), s1 = new SimpleDateFormat("EEEE");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));
        s1.setTimeZone(TimeZone.getTimeZone("EST"));
        dt.setText(sdf.format(d));
        da.setText(s1.format(d));

        day = new SimpleDateFormat("dd").format(d);
        hour = new SimpleDateFormat("HH").format(d);
        month = new SimpleDateFormat("MM").format(d);
    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH : mm : ss");
                sdf.setTimeZone(TimeZone.getTimeZone("EST"));
                ti.setText(sdf.format(d));
            }

        }).start();
    }

    void showWeather() {

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.weatherapi.com/v1/current.json?key=25489929747d4d389ec181248212901&q=New%20York")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            parseWeather(response); 

            System.out.println("Pre Processing ✓ ");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    void parseWeather(Response res) throws IOException, ParseException
    {
        Object obj = new JSONParser().parse(res.body().string()) ;
        JSONObject jo = (JSONObject) obj;
        JSONObject curr = (JSONObject) jo.get("current");
        hi = "0";
        ws = curr.get("wind_kph").toString();
        temp = curr.get("temp_c").toString();
        wc = curr.get("feelslike_c").toString();
        
        te.setText(temp + "° C");
        wind.setText(ws + "Kt");
        
    }

    void showPredictions() {

        try {

            String loc = parseOutput(runQuery("https://taxi-pickup.herokuapp.com/")) ;
            l = Integer.parseInt(loc.substring(1,loc.length() - 1));
            l1.setText("Taxi demand would be highest at " + map.get(l).name);
            System.out.println("Prediction 1 ✓ ");

            String pay = (parseOutput(runQuery("https://payment-method-api.herokuapp.com/")));
            int pm = Integer.parseInt(pay.substring(1,pay.indexOf('.')));
            switch(pm)
            {
                case 1 : l3.setText("Expect online payment methods");
                    break;
                case 2 : l3.setText("Expect cash payments");
                    break;
            }
            System.out.println("Prediction 2 ✓ ");

            String cong = parseOutput(runQuery("https://congesion-api.herokuapp.com/"));
            if(cong.equals("[0]"))
            {
                l2.setText("No congesion predicted.");
            }
            else
            {
                l2.setText("Heavy congesion predicted.");
            }
            System.out.println("Prediction 3 ✓ ");

            String pas = parseOutput(runQuery("https://passenger-api.herokuapp.com/"));
            l4.setText("Expected number of passengers per cab - " + pas.substring(1,pas.indexOf('.')) );
            System.out.println("Prediction 4 ✓ ");

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    Response runQuery(String q) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        String qu = String.format("[{\"Day\": %d,\"Month\": %d,\"Hour\": %d,\"Heat_Index_Mean\": \"%f\",\"Wind_Speed\": \"%f\",\"Temperature\":\"%f\",\"Wind_Chill\": \"%f\"}]",
                Integer.parseInt(day) , Integer.parseInt(month) , Integer.parseInt(hour) , Double.parseDouble(hi) , Double.parseDouble(ws) , Double.parseDouble(temp) , Double.parseDouble(wc) );
        RequestBody body = RequestBody.create(mediaType, qu);
        Request request = new Request.Builder()
                .url(q)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    String parseOutput(Response res) throws IOException, ParseException {

        Object obj = new JSONParser().parse(res.body().string()) ;
        JSONObject jo = (JSONObject) obj; 
        String pre = (String) jo.get("prediction"); 
        
        return pre;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        ti = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        te = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dt = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rc = new javax.swing.JLabel();
        wind = new javax.swing.JLabel();
        da = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(40, 31, 61));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(149, 58, 170));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Logout");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 80, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/power-button.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 80, -1));

        jLabel8.setFont(new java.awt.Font("AppleMyungjo", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Valet");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        jLabel9.setForeground(new java.awt.Color(149, 58, 170));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Archives");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 80, -1));

        jPanel3.setBackground(new java.awt.Color(23, 28, 49));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N

        jLabel7.setForeground(new java.awt.Color(149, 58, 170));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Live");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 5, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(2, 2, 2)
                    .addComponent(jLabel7)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, 70));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/folder.png"))); // NOI18N
        jLabel10.setToolTipText("Under Construction");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 80, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo small.png"))); // NOI18N
        jLabel26.setToolTipText("Everyone's Private Driver");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel12.setFont(new java.awt.Font("AppleMyungjo", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Valet");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, 90, 590));

        jPanel2.setBackground(new java.awt.Color(23, 28, 49));

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TIME");

        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REVENUE");

        jLabel4.setForeground(new java.awt.Color(0, 255, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("WEATHER");

        jPanel4.setBackground(new java.awt.Color(23, 28, 49));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Predictions Based on Current Factors -");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("• Hotspots Predicted - ");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("• Congession Predicted - ");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("• Payment Method Predicted - ");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("• Passengers Pridicted - ");

        l1.setForeground(new java.awt.Color(255, 255, 255));
        l1.setText("Expect higher demand at East Harlem North");

        l2.setForeground(new java.awt.Color(255, 255, 255));
        l2.setText("No congession predicted");

        l3.setForeground(new java.awt.Color(255, 255, 255));
        l3.setText("Online methods predicted");

        l4.setForeground(new java.awt.Color(255, 255, 255));
        l4.setText("No of passengers/cab - 1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l2)
                                    .addComponent(l1)
                                    .addComponent(l3)
                                    .addComponent(l4))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l1)
                .addGap(28, 28, 28)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l2)
                .addGap(34, 34, 34)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l3)
                .addGap(26, 26, 26)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l4)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        ti.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        ti.setForeground(new java.awt.Color(255, 255, 255));
        ti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ti.setText("09 : 41 : 00");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("$ 41876");

        te.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        te.setForeground(new java.awt.Color(255, 255, 255));
        te.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        te.setText("31° C");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Live Data & Predictions");

        dt.setForeground(new java.awt.Color(255, 255, 255));
        dt.setText("February 4, 2021");

        jLabel16.setForeground(new java.awt.Color(51, 255, 51));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up-and-down.png"))); // NOI18N
        jLabel16.setText("15% ↑");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/taxi.png"))); // NOI18N
        jLabel17.setText("23459 Trips");

        rc.setForeground(new java.awt.Color(255, 255, 255));
        rc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rain.png"))); // NOI18N
        rc.setText("15%");

        wind.setForeground(new java.awt.Color(255, 255, 255));
        wind.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        wind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wind.png"))); // NOI18N
        wind.setText("4 Kt");
        wind.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        da.setForeground(new java.awt.Color(255, 255, 255));
        da.setText("Thursday");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/map_pre.png"))); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(375, 370));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ti, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dt)
                                .addGap(18, 18, 18)
                                .addComponent(da))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(113, 113, 113)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(te, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rc)
                                .addGap(18, 18, 18)
                                .addComponent(wind))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 228, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(205, 205, 205))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel13)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(te)
                    .addComponent(ti))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dt)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(rc)
                    .addComponent(wind)
                    .addComponent(da))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 0, 830, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked

    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new Map(map.get(l).p.x , map.get(l).p.y).setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    public class area {

        String name;
        Point p;

        area(String name, int x, int y) {
            this.name = name;
            p = new Point(x, y);
        }
    }

    public HashMap<Integer, area> map = new HashMap<>();   {
        map.put(1, new area("Newark Airport", 141, 432));
        map.put(2, new area("Jamaica Bay", 649, 565));
        map.put(3, new area("Allerton/Pelham Gardens", 613, 99));
        map.put(4, new area("Alphabet City", 427, 373));
        map.put(5, new area("Arden Heights", 122, 698));
        map.put(6, new area("Arrochar/Fort Wadsworth", 290, 608));
        map.put(7, new area("Astoria", 513, 299));
        map.put(8, new area("Astoria Park", 505, 268));
        map.put(9, new area("Auburndale", 708, 313));
        map.put(10, new area("Baisley Park", 698, 459));
        map.put(11, new area("Bath Beach", 382, 602));
        map.put(12, new area("Battery Park", 373, 413));
        map.put(13, new area("Battery Park City", 372, 395));
        map.put(14, new area("Bay Ridge", 350, 559));
        map.put(15, new area("Bay Terrace/Fort Totten", 707, 259));
        map.put(16, new area("Bayside", 726, 297));
        map.put(17, new area("Bedford", 469, 430));
        map.put(18, new area("Bedford Park", 560, 93));
        map.put(19, new area("Bellerose", 797, 347));
        map.put(20, new area("Belmont", 563, 118));
        map.put(21, new area("Bensonhurst East", 421, 601));
        map.put(22, new area("Bensonhurst West", 401, 587));
        map.put(23, new area("Bloomfield/Emerson Hill", 136, 582));
        map.put(24, new area("Bloomingdale", 448, 223));
        map.put(25, new area("Boerum Hill", 415, 444));
        map.put(26, new area("Borough Park", 411, 550));
        map.put(27, new area("Breezy Point/Fort Tilden/Riis Beach", 519, 691));
        map.put(28, new area("Briarwood/Jamaica Hills", 670, 396));
        map.put(29, new area("Brighton Beach", 451, 650));
        map.put(30, new area("Broad Channel", 657, 603));
        map.put(31, new area("Bronx Park", 578, 115));
        map.put(32, new area("Bronxdale", 595, 102));
        map.put(33, new area("Brooklyn Heights", 400, 427));
        map.put(34, new area("Brooklyn Navy Yard", 445, 418));
        map.put(35, new area("Brownsville", 529, 487));
        map.put(36, new area("Bushwick North", 512, 414));
        map.put(37, new area("Bushwick South", 509, 430));
        map.put(38, new area("Cambria Heights", 787, 428));
        map.put(39, new area("Canarsie", 541, 537));
        map.put(40, new area("Carroll Gardens", 401, 458));
        map.put(41, new area("Central Harlem", 467, 215));
        map.put(42, new area("Central Harlem North", 477, 192));
        map.put(43, new area("Central Park", 449, 254));
        map.put(44, new area("Charleston/Tottenville", 53, 744));
        map.put(45, new area("Chinatown", 400, 394));
        map.put(46, new area("City Island", 706, 134));
        map.put(47, new area("Claremont/Bathgate", 540, 145));
        map.put(48, new area("Clinton East", 413, 295));
        map.put(49, new area("Clinton Hill", 448, 437));
        map.put(50, new area("Clinton West", 402, 291));
        map.put(51, new area("Co-Op City", 645, 82));
        map.put(52, new area("Cobble Hill", 400, 443));
        map.put(53, new area("College Point", 628, 270));
        map.put(54, new area("Columbia Street", 391, 443));
        map.put(55, new area("Coney Island", 397, 659));
        map.put(56, new area("Corona", 600, 336));
        map.put(57, new area("Corona", 610, 320));
        map.put(58, new area("Country Club", 655, 146));
        map.put(59, new area("Crotona Park", 548, 150));
        map.put(60, new area("Crotona Park East", 563, 157));
        map.put(61, new area("Crown Heights North", 481, 464));
        map.put(62, new area("Crown Heights South", 466, 481));
        map.put(63, new area("Cypress Hills", 574, 447));
        map.put(64, new area("Douglaston", 781, 307));
        map.put(65, new area("Downtown Brooklyn/MetroTech", 414, 428));
        map.put(66, new area("DUMBO/Vinegar Hill", 417, 413));
        map.put(67, new area("Dyker Heights", 374, 570));
        map.put(68, new area("East Chelsea", 394, 329));
        map.put(69, new area("East Concourse/Concourse Village", 520, 164));
        map.put(70, new area("East Elmhurst", 587, 293));
        map.put(71, new area("East Flatbush/Farragut", 484, 527));
        map.put(72, new area("East Flatbush/Remsen Village", 512, 507));
        map.put(73, new area("East Flushing", 675, 314));
        map.put(74, new area("East Harlem North", 489, 223));
        map.put(75, new area("East Harlem South", 473, 241));
        map.put(76, new area("East New York", 580, 494));
        map.put(77, new area("East New York/Pennsylvania Avenue", 545, 480));
        map.put(78, new area("East Tremont", 559, 137));
        map.put(79, new area("East Village", 418, 365));
        map.put(80, new area("East Williamsburg", 482, 384));
        map.put(81, new area("Eastchester", 621, 65));
        map.put(82, new area("Elmhurst", 579, 345));
        map.put(83, new area("Elmhurst/Maspeth", 552, 344));
        map.put(84, new area("Eltingville/Annadale/Prince's Bay", 151, 729));
        map.put(85, new area("Erasmus", 464, 519));
        map.put(86, new area("Far Rockaway", 754, 606));
        map.put(87, new area("Financial District North", 386, 405));
        map.put(88, new area("Financial District South", 379, 409));
        map.put(89, new area("Flatbush/Ditmas Park", 453, 540));
        map.put(90, new area("Flatiron", 400, 336));
        map.put(91, new area("Flatlands", 500, 558));
        map.put(92, new area("Flushing", 646, 297));
        map.put(93, new area("Flushing Meadows-Corona Park", 625, 326));
        map.put(94, new area("Fordham South", 542, 114));
        map.put(95, new area("Forest Hills", 617, 376));
        map.put(96, new area("Forest Park/Highland Park", 584, 421));
        map.put(97, new area("Fort Greene", 433, 436));
        map.put(98, new area("Fresh Meadows", 723, 350));
        map.put(99, new area("Freshkills Park", 106, 654));
        map.put(100, new area("Garment District", 412, 316));
        map.put(101, new area("Glen Oaks", 821, 326));
        map.put(102, new area("Glendale", 574, 409));
        map.put(103, new area("Governor's Island/Ellis Island/Liberty Island", 367, 43));
        map.put(106, new area("Gowanus", 404, 469));
        map.put(107, new area("Gramercy", 420, 350));
        map.put(108, new area("Gravesend", 421, 627));
        map.put(109, new area("Great Kills", 165, 698));
        map.put(110, new area("Great Kills Park", 211, 708));
        map.put(111, new area("Green-Wood Cemetery", 410, 508));
        map.put(112, new area("Greenpoint", 472, 362));
        map.put(113, new area("Greenwich Village North", 405, 356));
        map.put(114, new area("Greenwich Village South", 398, 363));
        map.put(115, new area("Grymes Hill/Clifton", 260, 576));
        map.put(116, new area("Hamilton Heights", 471, 174));
        map.put(117, new area("Hammels/Arverne", 704, 625));
        map.put(118, new area("Heartland Village/Todt Hill", 172, 639));
        map.put(119, new area("Highbridge", 505, 148));
        map.put(120, new area("Highbridge Park", 494, 140));
        map.put(121, new area("Hillcrest/Pomonok", 687, 365));
        map.put(122, new area("Hollis", 749, 395));
        map.put(123, new area("Homecrest", 451, 602));
        map.put(124, new area("Howard Beach", 618, 495));
        map.put(125, new area("Hudson Sq", 385, 368));
        map.put(126, new area("Hunts Point", 562, 201));
        map.put(127, new area("Inwood", 512, 101));
        map.put(128, new area("Inwood Hill Park", 502, 87));
        map.put(129, new area("Jackson Heights", 562, 309));
        map.put(130, new area("Jamaica", 698, 404));
        map.put(131, new area("Jamaica Estates", 712, 377));
        map.put(132, new area("JFK Airport", 705, 516));
        map.put(133, new area("Kensington", 431, 531));
        map.put(134, new area("Kew Gardens", 645, 402));
        map.put(135, new area("Kew Gardens Hills", 659, 363));
        map.put(136, new area("Kingsbridge Heights", 533, 99));
        map.put(137, new area("Kips Bay", 432, 340));
        map.put(138, new area("LaGuardia Airport", 579, 273));
        map.put(139, new area("Laurelton", 769, 460));
        map.put(140, new area("Lenox Hill East", 462, 291));
        map.put(141, new area("Lenox Hill West", 453, 292));
        map.put(142, new area("Lincoln Square East", 421, 278));
        map.put(143, new area("Lincoln Square West", 412, 275));
        map.put(144, new area("Little Italy/NoLiTa", 400, 379));
        map.put(145, new area("Long Island City/Hunters Point", 469, 327));
        map.put(146, new area("Long Island City/Queens Plaza", 494, 312));
        map.put(147, new area("Longwood", 542, 190));
        map.put(148, new area("Lower East Side", 409, 381));
        map.put(149, new area("Madison", 467, 601));
        map.put(150, new area("Manhattan Beach", 486, 639));
        map.put(151, new area("Manhattan Valley", 441, 229));
        map.put(152, new area("Manhattanville", 464, 192));
        map.put(153, new area("Marble Hill", 527, 78));
        map.put(154, new area("Marine Park/Floyd Bennett Field", 535, 623));
        map.put(155, new area("Marine Park/Mill Basin", 524, 588));
        map.put(156, new area("Mariners Harbor", 156, 549));
        map.put(157, new area("Maspeth", 531, 372));
        map.put(158, new area("Meatpacking/West Village West", 383, 352));
        map.put(159, new area("Melrose South", 523, 190));
        map.put(160, new area("Middle Village", 571, 383));
        map.put(161, new area("Midtown Center", 427, 304));
        map.put(162, new area("Midtown East", 435, 312));
        map.put(163, new area("Midtown North", 425, 295));
        map.put(164, new area("Midtown South", 414, 329));
        map.put(165, new area("Midwood", 456, 565));
        map.put(166, new area("Morningside Heights", 448, 209));
        map.put(167, new area("Morrisania/Melrose", 534, 176));
        map.put(168, new area("Mott Haven/Port Morris", 519, 212));
        map.put(169, new area("Mount Hope", 532, 131));
        map.put(170, new area("Murray Hill", 428, 325));
        map.put(171, new area("Murray Hill-Queens", 671, 286));
        map.put(172, new area("New Dorp/Midland Beach", 241, 658));
        map.put(173, new area("North Corona", 596, 313));
        map.put(174, new area("Norwood", 571, 74));
        map.put(175, new area("Oakland Gardens", 750, 339));
        map.put(176, new area("Oakwood", 214, 681));
        map.put(177, new area("Ocean Hill", 523, 460));
        map.put(178, new area("Ocean Parkway South", 441, 577));
        map.put(179, new area("Old Astoria", 500, 279));
        map.put(180, new area("Ozone Park", 615, 461));
        map.put(181, new area("Park Slope", 425, 474));
        map.put(182, new area("Parkchester", 601, 153));
        map.put(183, new area("Pelham Bay", 641, 132));
        map.put(184, new area("Pelham Bay Park", 671, 78));
        map.put(185, new area("Pelham Parkway", 607, 120));
        map.put(186, new area("Penn Station/Madison Sq West", 408, 325));
        map.put(187, new area("Port Richmond", 186, 566));
        map.put(188, new area("Prospect-Lefferts Gardens", 462, 498));
        map.put(189, new area("Prospect Heights", 444, 463));
        map.put(190, new area("Prospect Park", 439, 491));
        map.put(191, new area("Queens Village", 777, 393));
        map.put(192, new area("Queensboro Hill", 667, 334));
        map.put(193, new area("Queensbridge/Ravenswood", 479, 305));
        map.put(194, new area("Randalls Island", 507, 245));
        map.put(195, new area("Red Hook", 381, 461));
        map.put(196, new area("Rego Park", 593, 365));
        map.put(197, new area("Richmond Hill", 643, 428));
        map.put(198, new area("Ridgewood", 535, 406));
        map.put(199, new area("Rikers Island", 564, 246));
        map.put(200, new area("Riverdale/North Riverdale/Fieldston", 527, 34));
        map.put(201, new area("Rockaway Park", 619, 652));
        map.put(202, new area("Roosevelt Island", 472, 298));
        map.put(203, new area("Rosedale", 783, 503));
        map.put(204, new area("Rossville/Woodrow", 97, 726));
        map.put(205, new area("Saint Albans", 732, 432));
        map.put(206, new area("Saint George/New Brighton", 269, 527));
        map.put(207, new area("Saint Michaels Cemetery/Woodside", 542, 294));
        map.put(208, new area("Schuylerville/Edgewater Park", 649, 180));
        map.put(209, new area("Seaport", 393, 400));
        map.put(210, new area("Sheepshead Bay", 477, 622));
        map.put(211, new area("SoHo", 393, 371));
        map.put(212, new area("Soundview/Bruckner", 583, 173));
        map.put(213, new area("Soundview/Castle Hill", 599, 189));
        map.put(214, new area("South Beach/Dongan Hills", 267, 630));
        map.put(215, new area("South Jamaica", 704, 424));
        map.put(216, new area("South Ozone Park", 664, 464));
        map.put(217, new area("South Williamsburg", 453, 408));
        map.put(218, new area("Springfield Gardens North", 727, 468));
        map.put(219, new area("Springfield Gardens South", 750, 488));
        map.put(220, new area("Spuyten Duyvil/Kingsbridge", 526, 65));
        map.put(221, new area("Stapleton", 297, 587));
        map.put(222, new area("Starrett City", 567, 519));
        map.put(223, new area("Steinway", 530, 270));
        map.put(224, new area("Stuy Town/Peter Cooper Village", 429, 358));
        map.put(225, new area("Stuyvesant Heights", 490, 439));
        map.put(226, new area("Sunnyside", 506, 340));
        map.put(227, new area("Sunset Park East", 388, 527));
        map.put(228, new area("Sunset Park West", 375, 504));
        map.put(229, new area("Sutton Place/Turtle Bay North", 447, 310));
        map.put(230, new area("Times Sq/Theatre District", 418, 301));
        map.put(231, new area("TriBeCa/Civic Center", 381, 385));
        map.put(232, new area("Two Bridges/Seward Park", 418, 387));
        map.put(233, new area("UN/Turtle Bay South", 438, 323));
        map.put(234, new area("Union Sq", 407, 343));
        map.put(235, new area("University Heights/Morris Heights", 515, 125));
        map.put(236, new area("Upper East Side North", 455, 267));
        map.put(237, new area("Upper East Side South", 446, 285));
        map.put(238, new area("Upper West Side North", 436, 243));
        map.put(239, new area("Upper West Side South", 423, 256));
        map.put(240, new area("Van Cortlandt Park", 561, 44));
        map.put(241, new area("Van Cortlandt Village", 548, 79));
        map.put(242, new area("Van Nest/Morris Park", 609, 136));
        map.put(243, new area("Washington Heights North", 494, 118));
        map.put(244, new area("Washington Heights South", 479, 148));
        map.put(245, new area("West Brighton", 243, 550));
        map.put(246, new area("West Chelsea/Hudson Yards", 248, 552));
        map.put(247, new area("West Concourse", 390, 317));
        map.put(248, new area("West Farms/Bronx River", 500, 179));
        map.put(249, new area("West Village", 585, 158));
        map.put(250, new area("Westchester Village/Unionport", 390, 352));
        map.put(251, new area("Westerleigh", 613, 166));
        map.put(252, new area("Whitestone", 212, 578));
        map.put(253, new area("Willets Point", 666, 245));
        map.put(254, new area("Williamsbridge/Olinville", 627, 302));
        map.put(255, new area("Williamsburg (North Side)", 599, 69));
        map.put(256, new area("Williamsburg (South Side)", 456, 380));
        map.put(257, new area("Windsor Terrace", 449, 394));
        map.put(258, new area("Woodhaven", 428, 507));
        map.put(259, new area("Woodlawn/Wakefield", 608, 439));
        map.put(260, new area("Woodside", 614, 34));
        map.put(261, new area("World Trade Center", 533, 328));
        map.put(262, new area("Yorkville East", 473, 273));
        map.put(263, new area("Yorkville West", 467, 269));
    }

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel da;
    private javax.swing.JLabel dt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel rc;
    private javax.swing.JLabel te;
    private javax.swing.JLabel ti;
    private javax.swing.JLabel wind;
    // End of variables declaration//GEN-END:variables
}
