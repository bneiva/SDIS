package Project;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TxSocket implements Runnable{

    Node id;
    String msg;
    public TxSocket(Node id, String msg ){
        this.id = id;
        this.msg = msg;
    }
    
    @Override
    public void run(){
        
        try {
            byte[] buf = msg.getBytes();
            
            InetAddress group = InetAddress.getByName(id.ip);
            int porta = id.porta;
            
            MulticastSocket ms = new MulticastSocket();
            
            DatagramPacket dp = new DatagramPacket(buf, buf.length, group ,porta);
            
            Thread.sleep(50);
            
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
