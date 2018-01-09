package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RxTxSocket extends Thread{
    
    private volatile byte[] buf = new byte[512];
    
    Node n;
    Node id;
    JFrame j;
    
    String msg;
    int counter;
    
    public RxTxSocket(Node n, Node id, JFrame j){
        this.j = j;
        
        this.n = n;
        this.id = id;
    }
    
    public byte[] getValue(){
        return buf;
    }
    
    @Override
    public void run(){
            
            byte[] buf2 = new byte[512];
            
        try {
            
            Packet buf1 = new Packet();
            
            InetAddress groupRx = InetAddress.getByName(n.ip);
            int portaRx = n.porta;
            
            MulticastSocket ms = new MulticastSocket(portaRx);
            
            ms.joinGroup(groupRx);
            
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ms.setSoTimeout(10000);
            
            try{
                ms.receive(dp); 
                j.getContentPane().add(new PaintedCircle(n,0,0));
                j.setVisible(true);
                j.getContentPane().add(new PaintedCircle(id,0,0));
                j.setVisible(true);

                ms.close();

                buf = dp.getData();
                
                buf1.decodeData(buf);
                
                counter = buf1.counterReceive;
                buf2 = buf1.receiveData;
                
                buf = buf1.encodeData(counter+1, new String(buf2));
                
                
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
            
            InetAddress groupTx = InetAddress.getByName(id.ip);
            int portaTx = id.porta;
            
            MulticastSocket ms1 = new MulticastSocket();
            
            DatagramPacket dp1 = new DatagramPacket(buf, buf.length, groupTx, portaTx);
            
            Thread.sleep(80);
            ms1.setTimeToLive(80);
            ms1.send(dp1);
            
            ms1.close();
            
        }catch (Exception e){
            System.err.println("Error RxTx Socket");
        }
    }
    
}
