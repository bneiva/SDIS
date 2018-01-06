package Project;

import java.net.*;

public class RxTxSocket extends Thread{
    
    private volatile byte[] buf = new byte[512];
    
    Node n;
    Node id;
    public RxTxSocket(Node n, Node id){
        this.n = n;
        this.id = id;
    }
    
    public byte[] getValue(){
        return buf;
    }
    
    @Override
    public void run(){
        
        try {
            
            InetAddress groupRx = InetAddress.getByName(n.ip);
            int portaRx = n.porta;
            
            MulticastSocket ms = new MulticastSocket(portaRx);
            
            ms.joinGroup(groupRx);
            
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            
            ms.receive(dp);
            
            ms.close();
            
            //System.out.println("Mensagem Recebida RxTx");
            
            buf = dp.getData();
            
            InetAddress groupTx = InetAddress.getByName(id.ip);
            int portaTx = id.porta;
            
            MulticastSocket ms1 = new MulticastSocket();
            
            DatagramPacket dp1 = new DatagramPacket(buf, buf.length, groupTx, portaTx);
            
            Thread.sleep(50);
           
            ms1.send(dp1);
            
            ms1.close();
            
            /*System.out.println("Mensagem Enviada RxTx");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();*/
            //System.out.println(new String(dp1.getData()));
            /*System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();*/
            
        }catch (Exception e){
            System.err.println("Error RxTx Socket");
        }
    }
    
}
