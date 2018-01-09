package Project;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class ComFlow extends JComponent {
    
    Node[] node;
    int level;
    String file;
    
    BufferedReader br = null;
    String line = new String();
    String csvSplitBy = ";";
        ArrayList<String> RxnLvl1 = new ArrayList<>();
    ArrayList<String> RxnLvl2 = new ArrayList<>();
    ArrayList<String> RxnLvl3 = new ArrayList<>();
    ArrayList<String> RxnLvl4 = new ArrayList<>();
    ArrayList<String> RxnLvl5 = new ArrayList<>();
    ArrayList<String> RxnLvl6 = new ArrayList<>();
    ArrayList<String> RxnLvl7 = new ArrayList<>();
    ArrayList<String> RxnLvl8 = new ArrayList<>();
    ArrayList<String> RxnLvl9 = new ArrayList<>();
    ArrayList<String> RxnLvl10 = new ArrayList<>();
    ArrayList<String> RxnLvl11 = new ArrayList<>();
    ArrayList<String> RxnLvl12 = new ArrayList<>();
    ArrayList<String> RxnLvl13 = new ArrayList<>();
    ArrayList<String> RxnLvl14 = new ArrayList<>();
    
    ArrayList<String> TxnLvl1 = new ArrayList<>();
    ArrayList<String> TxnLvl2 = new ArrayList<>();
    ArrayList<String> TxnLvl3 = new ArrayList<>();
    ArrayList<String> TxnLvl4 = new ArrayList<>();
    ArrayList<String> TxnLvl5 = new ArrayList<>();
    ArrayList<String> TxnLvl6 = new ArrayList<>();
    ArrayList<String> TxnLvl7 = new ArrayList<>();
    ArrayList<String> TxnLvl8 = new ArrayList<>();
    ArrayList<String> TxnLvl9 = new ArrayList<>();
    ArrayList<String> TxnLvl10 = new ArrayList<>();
    ArrayList<String> TxnLvl11 = new ArrayList<>();
    ArrayList<String> TxnLvl12 = new ArrayList<>();
    ArrayList<String> TxnLvl13 = new ArrayList<>();
    ArrayList<String> TxnLvl14 = new ArrayList<>();
    
    String[] RxxnLvl1;
    String[] RxxnLvl2;
    String[] RxxnLvl3;
    String[] RxxnLvl4;
    String[] RxxnLvl5;
    String[] RxxnLvl6;
    String[] RxxnLvl7;
    String[] RxxnLvl8;
    String[] RxxnLvl9;
    String[] RxxnLvl10;
    String[] RxxnLvl11;
    String[] RxxnLvl12;
    String[] RxxnLvl13;
    String[] RxxnLvl14;

    
    
    String[] TxxnLvl1;
    String[] TxxnLvl2;
    String[] TxxnLvl3;
    String[] TxxnLvl4;
    String[] TxxnLvl5;
    String[] TxxnLvl6;
    String[] TxxnLvl7;
    String[] TxxnLvl8;
    String[] TxxnLvl9;
    String[] TxxnLvl10;
    String[] TxxnLvl11;
    String[] TxxnLvl12;
    String[] TxxnLvl13;
    String[] TxxnLvl14;

    
    int[] RecNLvl1;
    int[] RecNLvl2;
    int[] RecNLvl3;
    int[] RecNLvl4;
    int[] RecNLvl5;
    int[] RecNLvl6;
    int[] RecNLvl7;
    int[] RecNLvl8;
    int[] RecNLvl9;
    int[] RecNLvl10;
    int[] RecNLvl11;
    int[] RecNLvl12;
    int[] RecNLvl13;
    int[] RecNLvl14;
    
    int[] TransNLvl1;
    int[] TransNLvl2;
    int[] TransNLvl3;
    int[] TransNLvl4;
    int[] TransNLvl5;
    int[] TransNLvl6;
    int[] TransNLvl7;
    int[] TransNLvl8;
    int[] TransNLvl9;
    int[] TransNLvl10;
    int[] TransNLvl11;
    int[] TransNLvl12;
    int[] TransNLvl13;
    int[] TransNLvl14;

    Color[] c = new Color[15];
 
    public ComFlow(Node[] node, int level, String file) throws FileNotFoundException, IOException{
        this.node = node;
        this.level = level;
        
        if(level >0){
                this.file = file;

                br = new BufferedReader(new FileReader(file));

                while((line = br.readLine()) != null){
                    String[] fields = line.split(csvSplitBy);
                    switch(Integer.parseInt(fields[3])){
                        case 1: RxnLvl1.add(fields[0]);
                                TxnLvl1.add(fields[1]);
                                break;
                        case 2: RxnLvl2.add(fields[0]);
                                TxnLvl2.add(fields[1]);
                                break;
                        case 3: RxnLvl3.add(fields[0]);
                                TxnLvl3.add(fields[1]);
                                break;
                        case 4: RxnLvl4.add(fields[0]);
                                TxnLvl4.add(fields[1]);
                                break;
                        case 5: RxnLvl5.add(fields[0]);
                                TxnLvl5.add(fields[1]);
                                break;
                        case 6: RxnLvl6.add(fields[0]);
                                TxnLvl6.add(fields[1]);   
                                break;
                        case 7: RxnLvl7.add(fields[0]);
                                TxnLvl7.add(fields[1]);
                                break;
                        case 8: RxnLvl8.add(fields[0]);
                                TxnLvl8.add(fields[1]);
                                break;
                        case 9: RxnLvl9.add(fields[0]);
                                TxnLvl9.add(fields[1]);
                                break;
                        case 10: RxnLvl10.add(fields[0]);
                                TxnLvl10.add(fields[1]);
                                break;
                        case 11: RxnLvl11.add(fields[0]);
                                TxnLvl11.add(fields[1]);
                                break;
                        case 12: RxnLvl12.add(fields[0]);
                                TxnLvl12.add(fields[1]);   
                                break;
                        case 13: RxnLvl13.add(fields[0]);
                                TxnLvl13.add(fields[1]);
                                break;
                        case 14: RxnLvl14.add(fields[0]);
                                TxnLvl14.add(fields[1]);
                                break;
                        default: 
                                break;

                    }
                }
                
                br.close();

                    RxxnLvl1 = RxnLvl1.toArray(new String[RxnLvl1.size()]);
                    RxxnLvl2 = RxnLvl2.toArray(new String[RxnLvl2.size()]);
                    RxxnLvl3 = RxnLvl3.toArray(new String[RxnLvl3.size()]);
                    RxxnLvl4 = RxnLvl4.toArray(new String[RxnLvl4.size()]);
                    RxxnLvl5 = RxnLvl5.toArray(new String[RxnLvl5.size()]);
                    RxxnLvl6 = RxnLvl6.toArray(new String[RxnLvl6.size()]);
                    RxxnLvl7 = RxnLvl7.toArray(new String[RxnLvl7.size()]);
                    RxxnLvl8 = RxnLvl8.toArray(new String[RxnLvl8.size()]);
                    RxxnLvl9 = RxnLvl9.toArray(new String[RxnLvl9.size()]);
                    RxxnLvl10 = RxnLvl10.toArray(new String[RxnLvl10.size()]);
                    RxxnLvl11 = RxnLvl11.toArray(new String[RxnLvl11.size()]);
                    RxxnLvl12 = RxnLvl12.toArray(new String[RxnLvl12.size()]);
                    RxxnLvl13 = RxnLvl13.toArray(new String[RxnLvl13.size()]);
                    RxxnLvl14 = RxnLvl14.toArray(new String[RxnLvl14.size()]);


                    TxxnLvl1 = TxnLvl1.toArray(new String[TxnLvl1.size()]);
                    TxxnLvl2 = TxnLvl2.toArray(new String[TxnLvl2.size()]);
                    TxxnLvl3 = TxnLvl3.toArray(new String[TxnLvl3.size()]);
                    TxxnLvl4 = TxnLvl4.toArray(new String[TxnLvl4.size()]);
                    TxxnLvl5 = TxnLvl5.toArray(new String[TxnLvl5.size()]);
                    TxxnLvl6 = TxnLvl6.toArray(new String[TxnLvl6.size()]);
                    TxxnLvl7 = TxnLvl7.toArray(new String[TxnLvl7.size()]);
                    TxxnLvl8 = TxnLvl8.toArray(new String[TxnLvl8.size()]);
                    TxxnLvl9 = TxnLvl9.toArray(new String[TxnLvl9.size()]);
                    TxxnLvl10 = TxnLvl10.toArray(new String[TxnLvl10.size()]);
                    TxxnLvl11 = TxnLvl11.toArray(new String[TxnLvl11.size()]);
                    TxxnLvl12 = TxnLvl12.toArray(new String[TxnLvl12.size()]);
                    TxxnLvl13 = TxnLvl13.toArray(new String[TxnLvl13.size()]);
                    TxxnLvl14 = TxnLvl14.toArray(new String[TxnLvl14.size()]);

                    
                    TransNLvl1 = new int[TxxnLvl1.length];
                    for (int i = 0; i < TxxnLvl1.length; i++) {
                       TransNLvl1[i] = Integer.parseInt(TxxnLvl1[i]);
                    }
                    
                    TransNLvl2 = new int[TxxnLvl2.length];
                    for (int i = 0; i < TxxnLvl2.length; i++) {
                       TransNLvl2[i] = Integer.parseInt(TxxnLvl2[i]);
                    }
                    
                    TransNLvl3 = new int[TxxnLvl3.length];
                    for (int i = 0; i < TxxnLvl3.length; i++) {
                       TransNLvl3[i] = Integer.parseInt(TxxnLvl3[i]);
                    }
                    
                    TransNLvl4 = new int[TxxnLvl4.length];
                    for (int i = 0; i < TxxnLvl4.length; i++) {
                       TransNLvl4[i] = Integer.parseInt(TxxnLvl4[i]);
                    }

                    TransNLvl5 = new int[TxxnLvl5.length];
                    for (int i = 0; i < TxxnLvl5.length; i++) {
                       TransNLvl5[i] = Integer.parseInt(TxxnLvl5[i]);
                    }
                    
                    TransNLvl6 = new int[TxxnLvl6.length];
                    for (int i = 0; i < TxxnLvl6.length; i++) {
                       TransNLvl6[i] = Integer.parseInt(TxxnLvl6[i]);
                    }
                    
                    TransNLvl7 = new int[TxxnLvl7.length];
                    for (int i = 0; i < TxxnLvl7.length; i++) {
                       TransNLvl7[i] = Integer.parseInt(TxxnLvl7[i]);
                    }
                    
                    TransNLvl8 = new int[TxxnLvl8.length];
                    for (int i = 0; i < TxxnLvl8.length; i++) {
                       TransNLvl8[i] = Integer.parseInt(TxxnLvl8[i]);
                    }
                    
                    TransNLvl9 = new int[TxxnLvl9.length];
                    for (int i = 0; i < TxxnLvl9.length; i++) {
                       TransNLvl9[i] = Integer.parseInt(TxxnLvl9[i]);
                    }
                    
                    TransNLvl10 = new int[TxxnLvl10.length];
                    for (int i = 0; i < TxxnLvl10.length; i++) {
                       TransNLvl10[i] = Integer.parseInt(TxxnLvl10[i]);
                    }
                    
                    TransNLvl11 = new int[TxxnLvl11.length];
                    for (int i = 0; i < TxxnLvl11.length; i++) {
                       TransNLvl11[i] = Integer.parseInt(TxxnLvl11[i]);
                    }
                    
                    TransNLvl12 = new int[TxxnLvl12.length];
                    for (int i = 0; i < TxxnLvl12.length; i++) {
                       TransNLvl12[i] = Integer.parseInt(TxxnLvl12[i]);
                    }
                    
                    TransNLvl13 = new int[TxxnLvl13.length];
                    for (int i = 0; i < TxxnLvl13.length; i++) {
                       TransNLvl13[i] = Integer.parseInt(TxxnLvl13[i]);
                    }
                    
                    TransNLvl14 = new int[TxxnLvl14.length];
                    for (int i = 0; i < TxxnLvl14.length; i++) {
                       TransNLvl14[i] = Integer.parseInt(TxxnLvl14[i]);
                    }
                    RecNLvl1 = new int[RxxnLvl1.length];
                    for (int i = 0; i < RxxnLvl1.length; i++) {
                       RecNLvl1[i] = Integer.parseInt(RxxnLvl1[i]);
                    }
                    
                    RecNLvl2 = new int[RxxnLvl2.length];
                    for (int i = 0; i < RxxnLvl2.length; i++) {
                       RecNLvl2[i] = Integer.parseInt(RxxnLvl2[i]);
                    }
                    
                    RecNLvl3 = new int[RxxnLvl3.length];
                    for (int i = 0; i < RxxnLvl3.length; i++) {
                       RecNLvl3[i] = Integer.parseInt(RxxnLvl3[i]);
                    }
                    
                    RecNLvl4 = new int[RxxnLvl4.length];
                    for (int i = 0; i < RxxnLvl4.length; i++) {
                       RecNLvl4[i] = Integer.parseInt(RxxnLvl4[i]);
                    }

                    RecNLvl5 = new int[RxxnLvl5.length];
                    for (int i = 0; i < RxxnLvl5.length; i++) {
                       RecNLvl5[i] = Integer.parseInt(RxxnLvl5[i]);
                    }
                    
                    RecNLvl6 = new int[RxxnLvl6.length];
                    for (int i = 0; i < RxxnLvl6.length; i++) {
                       RecNLvl6[i] = Integer.parseInt(RxxnLvl6[i]);
                    }
                    
                    RecNLvl7 = new int[RxxnLvl7.length];
                    for (int i = 0; i < RxxnLvl7.length; i++) {
                       RecNLvl7[i] = Integer.parseInt(RxxnLvl7[i]);
                    }
                    
                    RecNLvl8 = new int[RxxnLvl8.length];
                    for (int i = 0; i < RxxnLvl8.length; i++) {
                       RecNLvl8[i] = Integer.parseInt(RxxnLvl8[i]);
                    }
                    
                    RecNLvl9 = new int[RxxnLvl9.length];
                    for (int i = 0; i < RxxnLvl9.length; i++) {
                       RecNLvl9[i] = Integer.parseInt(RxxnLvl9[i]);
                    }
                    
                    RecNLvl10 = new int[RxxnLvl10.length];
                    for (int i = 0; i < RxxnLvl10.length; i++) {
                       RecNLvl10[i] = Integer.parseInt(RxxnLvl10[i]);
                    }
                    
                    RecNLvl11 = new int[RxxnLvl11.length];
                    for (int i = 0; i < RxxnLvl11.length; i++) {
                       RecNLvl11[i] = Integer.parseInt(RxxnLvl11[i]);
                    }
                    
                    RecNLvl12 = new int[RxxnLvl12.length];
                    for (int i = 0; i < RxxnLvl12.length; i++) {
                       RecNLvl12[i] = Integer.parseInt(RxxnLvl12[i]);
                    }
                    
                    RecNLvl13 = new int[RxxnLvl13.length];
                    for (int i = 0; i < RxxnLvl13.length; i++) {
                       RecNLvl13[i] = Integer.parseInt(RxxnLvl13[i]);
                    }
                    
                    RecNLvl14 = new int[RxxnLvl14.length];
                    for (int i = 0; i < RxxnLvl14.length; i++) {
                       RecNLvl14[i] = Integer.parseInt(RxxnLvl14[i]);
                    }
            
            }
        
            c[0] = Color.black;
            c[1] = Color.blue;
            c[2] = Color.red;
            c[3] = Color.yellow;
            c[4] = Color.green;
            c[5] = Color.gray;
            c[6] = Color.PINK;
            c[7] = Color.ORANGE;
            c[8] = Color.MAGENTA;
            c[9] = new Color(148,0,211);
            c[10] = new Color(95,2,31);
            c[11] = new Color(139,69,19);
            c[12] = new Color(255,218,185);
            c[13] = new Color(145, 255,18);
            c[14] = new Color(18,248,255);

    }
    
        
          
    @Override
    public void paint(Graphics g) {
        
        switch(level){
            case 0: 
                    g.setColor(c[0]);
                    g.fillOval(node[0].x_coord-1, node[0].y_coord-14, 16, 16);
                    
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(0,0,630,630);
                    g.drawRect(1,1,628,628);
                    g.drawRect(2,2,626,626);
                    break;
            case 1: 
                    for(int i = 0; i<RecNLvl1.length; i++){
                       
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl1[i]].x_coord+7;
                        int y1 = node[TransNLvl1[i]].y_coord-6;
                        int x2 = node[RecNLvl1[i]].x_coord+7;
                        int y2 = node[RecNLvl1[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};
                    
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl1[i]), node[RecNLvl1[i]].x_coord, node[RecNLvl1[i]].y_coord);
                        g.setColor(c[1]);
                        g.drawOval(x2-8, y2-8, 16, 16);
                        
                        g.setColor(Color.black);
                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);

                    }
                    
                    break;
            case 2: 
                    for(int i = 0; i<RecNLvl2.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl2[i]), node[RecNLvl2[i]].x_coord, node[RecNLvl2[i]].y_coord);
                        g.setColor(c[2]);
                        g.drawOval(node[RecNLvl2[i]].x_coord-1, node[RecNLvl2[i]].y_coord-14, 16, 16);
                        
                        g.setColor(c[1]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl2[i]].x_coord+7;
                        int y1 = node[TransNLvl2[i]].y_coord-6;
                        int x2 = node[RecNLvl2[i]].x_coord+7;
                        int y2 = node[RecNLvl2[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }

                
                    break;
            case 3: 
                                 for(int i = 0; i<RecNLvl3.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl3[i]), node[RecNLvl3[i]].x_coord, node[RecNLvl3[i]].y_coord);
                        g.setColor(c[3]);
                        g.drawOval(node[RecNLvl3[i]].x_coord-1, node[RecNLvl3[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl3.length; i++){
                        g.setColor(c[2]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl3[i]].x_coord+7;
                        int y1 = node[TransNLvl3[i]].y_coord-6;
                        int x2 = node[RecNLvl3[i]].x_coord+7;
                        int y2 = node[RecNLvl3[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    
                    break;
                    
            case 4: 
                                 for(int i = 0; i<RecNLvl4.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl4[i]), node[RecNLvl4[i]].x_coord, node[RecNLvl4[i]].y_coord);
                        g.setColor(c[4]);
                        g.drawOval(node[RecNLvl4[i]].x_coord-1, node[RecNLvl4[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl4.length; i++){
                        g.setColor(c[3]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl4[i]].x_coord+7;
                        int y1 = node[TransNLvl4[i]].y_coord-6;
                        int x2 = node[RecNLvl4[i]].x_coord+7;
                        int y2 = node[RecNLvl4[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    
                    break;
            case 5: 
                                 for(int i = 0; i<RecNLvl5.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl5[i]), node[RecNLvl5[i]].x_coord, node[RecNLvl5[i]].y_coord);
                        g.setColor(c[5]);
                        g.drawOval(node[RecNLvl5[i]].x_coord-1, node[RecNLvl5[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl5.length; i++){
                        g.setColor(c[4]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl5[i]].x_coord+7;
                        int y1 = node[TransNLvl5[i]].y_coord-6;
                        int x2 = node[RecNLvl5[i]].x_coord+7;
                        int y2 = node[RecNLvl5[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    break;
            case 6: 
                                 for(int i = 0; i<RecNLvl6.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl6[i]), node[RecNLvl6[i]].x_coord, node[RecNLvl6[i]].y_coord);
                        g.setColor(c[6]);
                        g.drawOval(node[RecNLvl6[i]].x_coord-1, node[RecNLvl6[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl6.length; i++){
                        g.setColor(c[5]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl6[i]].x_coord+7;
                        int y1 = node[TransNLvl6[i]].y_coord-6;
                        int x2 = node[RecNLvl6[i]].x_coord+7;
                        int y2 = node[RecNLvl6[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    
                    break;
            case 7: 
                    for(int i = 0; i<RecNLvl7.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl7[i]), node[RecNLvl7[i]].x_coord, node[RecNLvl7[i]].y_coord);
                        g.setColor(c[7]);
                        g.drawOval(node[RecNLvl7[i]].x_coord-1, node[RecNLvl7[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl7.length; i++){
                        g.setColor(c[6]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl7[i]].x_coord+7;
                        int y1 = node[TransNLvl7[i]].y_coord-6;
                        int x2 = node[RecNLvl7[i]].x_coord+7;
                        int y2 = node[RecNLvl7[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    
                    break;
            case 8: 
                                 for(int i = 0; i<RecNLvl8.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl8[i]), node[RecNLvl8[i]].x_coord, node[RecNLvl8[i]].y_coord);
                        g.setColor(c[8]);
                        g.drawOval(node[RecNLvl8[i]].x_coord-1, node[RecNLvl8[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl8.length; i++){
                        g.setColor(c[7]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl8[i]].x_coord+7;
                        int y1 = node[TransNLvl8[i]].y_coord-6;
                        int x2 = node[RecNLvl8[i]].x_coord+7;
                        int y2 = node[RecNLvl8[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    
                    break;
            case 9: 
            for(int i = 0; i<RecNLvl9.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl9[i]), node[RecNLvl9[i]].x_coord, node[RecNLvl9[i]].y_coord);
                        g.setColor(c[9]);
                        g.drawOval(node[RecNLvl9[i]].x_coord-1, node[RecNLvl9[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl9.length; i++){
                        g.setColor(c[8]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl9[i]].x_coord+7;
                        int y1 = node[TransNLvl9[i]].y_coord-6;
                        int x2 = node[RecNLvl9[i]].x_coord+7;
                        int y2 = node[RecNLvl9[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                                    
                    break;
            case 10: 
            for(int i = 0; i<RecNLvl10.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl10[i]), node[RecNLvl10[i]].x_coord, node[RecNLvl10[i]].y_coord);
                        g.setColor(c[10]);
                        g.drawOval(node[RecNLvl10[i]].x_coord-1, node[RecNLvl10[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl10.length; i++){
                        g.setColor(c[9]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl10[i]].x_coord+7;
                        int y1 = node[TransNLvl10[i]].y_coord-6;
                        int x2 = node[RecNLvl10[i]].x_coord+7;
                        int y2 = node[RecNLvl10[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                                    
                    break;
            case 11: 
            for(int i = 0; i<RecNLvl11.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl11[i]), node[RecNLvl11[i]].x_coord, node[RecNLvl11[i]].y_coord);
                        g.setColor(c[11]);
                        g.drawOval(node[RecNLvl11[i]].x_coord-1, node[RecNLvl11[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl11.length; i++){
                        g.setColor(c[10]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl11[i]].x_coord+7;
                        int y1 = node[TransNLvl11[i]].y_coord-6;
                        int x2 = node[RecNLvl11[i]].x_coord+7;
                        int y2 = node[RecNLvl11[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                                    
                    break;
            case 12:
            for(int i = 0; i<RecNLvl12.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl12[i]), node[RecNLvl12[i]].x_coord, node[RecNLvl12[i]].y_coord);
                        g.setColor(c[12]);
                        g.drawOval(node[RecNLvl12[i]].x_coord-1, node[RecNLvl12[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl12.length; i++){
                        g.setColor(c[11]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl12[i]].x_coord+7;
                        int y1 = node[TransNLvl12[i]].y_coord-6;
                        int x2 = node[RecNLvl12[i]].x_coord+7;
                        int y2 = node[RecNLvl12[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                                    
                    break;
            case 13:
            for(int i = 0; i<RecNLvl13.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl13[i]), node[RecNLvl13[i]].x_coord, node[RecNLvl13[i]].y_coord);
                        g.setColor(c[13]);
                        g.drawOval(node[RecNLvl13[i]].x_coord-1, node[RecNLvl13[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl13.length; i++){
                        g.setColor(c[12]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl13[i]].x_coord+7;
                        int y1 = node[TransNLvl13[i]].y_coord-6;
                        int x2 = node[RecNLvl13[i]].x_coord+7;
                        int y2 = node[RecNLvl13[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                    
                    break;
            case 14:    
            for(int i = 0; i<RecNLvl14.length; i++){
                        g.setColor(c[0]);
                        g.drawString(Integer.toString(RecNLvl14[i]), node[RecNLvl14[i]].x_coord, node[RecNLvl14[i]].y_coord);
                        g.setColor(c[14]);
                        g.drawOval(node[RecNLvl14[i]].x_coord-1, node[RecNLvl14[i]].y_coord-14, 16, 16);
                    }
                    
                    for(int i = 0; i<RecNLvl14.length; i++){
                        g.setColor(c[13]);
                        int d = 3;
                        int h = 3;
                        int x1 = node[TransNLvl14[i]].x_coord+7;
                        int y1 = node[TransNLvl14[i]].y_coord-6;
                        int x2 = node[RecNLvl14[i]].x_coord+7;
                        int y2 = node[RecNLvl14[i]].y_coord-6;
                        int dx = x2 - x1, dy = y2 - y1;
                        double D = Math.sqrt(dx*dx + dy*dy);
                        double xm = D - d, xn = xm, ym = h, yn = -h, x;
                        double sin = dy / D, cos = dx / D;

                        x = xm*cos - ym*sin + x1;
                        ym = xm*sin + ym*cos + y1;
                        xm = x;

                        x = xn*cos - yn*sin + x1;
                        yn = xn*sin + yn*cos + y1;
                        xn = x;

                        int[] xpoints = {x2, (int) xm, (int) xn};
                        int[] ypoints = {y2, (int) ym, (int) yn};

                        g.drawLine(x1, y1, x2, y2);

                        g.fillPolygon(xpoints, ypoints, 3);
                    }
                                    
                    break;
            default: 
                    g.setColor(Color.white);
                    g.fillRect(0,0,630,630);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(0,0,630,630);
                    g.drawRect(1,1,628,628);
                    g.drawRect(2,2,626,626);
                    break;
        }
    }
    
  }
