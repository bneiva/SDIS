package Project;

import java.net.*;

public class RxSocket extends Thread{
    
    private volatile byte[] buf = new byte[512];
    
    Node n;
    public RxSocket(Node n){
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
            
            buf = dp.getData();
            
            ms.close();
            
            //System.out.println("Mensagem Recebida RxRxRxRx!");
            System.out.println(new String(dp.getData()));
            
        }catch (Exception e){
            System.err.println("Error Rx Socket");
        }
    }
    
}
