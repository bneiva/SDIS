package Project;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

public class RxSocket extends Thread{
    
    private volatile byte[] buf = new byte[512];
    
    Node n;
    Node id;
    JFrame j;
    public RxSocket(Node n, JFrame j, Node id){
        this.j = j;
        this.id = id;
        this.n = n;
    }
    
    public byte[] getValue(){
        return buf;
    }
    
    @Override
    public void run(){
        
        try {
                
            
            InetAddress group = InetAddress.getByName(n.ip);
            int porta = n.porta;
            
            MulticastSocket ms = new MulticastSocket(porta);
            
            ms.joinGroup(group);
            
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            
            ms.receive(dp);
       
            j.getContentPane().add(new PaintedCircle(n,0,0));
            j.setVisible(true);
            j.getContentPane().add(new PaintedCircle(id,0,0));
            j.setVisible(true);
            
            buf = dp.getData();
            
            File file = new File("Receivers.csv");
            
            if(!file.exists()){
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id.id+";");
            bw.write(n.id+";");
            for(int i = 0; i<buf.length; i++){
                if(buf[i]>= 32 && buf[i]<=125){
                    bw.write(buf[i]);
                }
            }
            bw.write(";");
            bw.write(id.level+";");
            bw.newLine();
            bw.close();
            ms.close();
            
            System.out.println(new String(dp.getData()));
            
        }catch (Exception e){
            System.err.println("Error Rx Socket");
        }
    }
    
}
