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
    
    String msg;
    int counter;
    
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
            
            byte[] buf2 = new byte[512];
        
        try {
                
            
            InetAddress group = InetAddress.getByName(n.ip);
            int porta = n.porta;
            
            MulticastSocket ms = new MulticastSocket(porta);
            
            ms.joinGroup(group);
            
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            
            
            Packet buf1 = new Packet();
            
            
            
            ms.setSoTimeout(10000);
            try{
                ms.receive(dp);

                j.getContentPane().add(new PaintedCircle(n,0,0));
                j.setVisible(true);
                j.getContentPane().add(new PaintedCircle(id,0,0));
                j.setVisible(true);

                buf = dp.getData();
                
                buf1.decodeData(buf);
                
                counter = buf1.counterReceive;
                buf2 = buf1.receiveData;
                
                
            } catch (SocketTimeoutException e){
                System.out.println("Timeout reached!!! " + e);
                ms.close();
            }
            
            File file = new File("Receivers.csv");
            
            if(!file.exists()){
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id.id+";");
            bw.write(n.id+";");
            
            for(int i = 0; i<buf2.length; i++){
                if(buf2[i]>= 32 && buf2[i]<=125){
                    bw.write(buf2[i]);
                }
            }
            bw.write(";");
            bw.write((counter+1)+";");
            bw.newLine();
            bw.close();
            ms.close();
            
            
        }catch (Exception e){
            System.err.println("Error Rx Socket");
        }
    }
    
}
