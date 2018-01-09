package Project;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class TxSocket implements Runnable{

    Node id;
    String msg;
    JFrame j;
    public TxSocket(Node id, String msg, JFrame j ){
        this.id = id;
        this.msg = msg;
        
        this.j = j;
    }
    
    @Override
    public void run(){
        
        try {
            byte[] buf;
            Packet buf1 = new Packet();
            
            buf = buf1.encodeData(0, msg);
            
            InetAddress group = InetAddress.getByName(id.ip);
            int porta = id.porta;
            
            MulticastSocket ms = new MulticastSocket();
            
            DatagramPacket dp = new DatagramPacket(buf, buf.length, group ,porta);
            
            j.getContentPane().add(new PaintedCircle(id,0,0));
            j.setVisible(true);
            
            Thread.sleep(80);
            
            ms.setTimeToLive(80);
            ms.send(dp);
           
            
            ms.close();
            
           // System.out.println("Mensagem enviada Tx!");
        } catch (IOException ex) {
            Logger.getLogger(TxSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TxSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
