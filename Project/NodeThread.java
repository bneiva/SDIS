package Project;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NodeThread implements Runnable{

    Node[] node;
    int N;
    int id;
    
    private Lock lock = new ReentrantLock();
    
    public NodeThread(int N, Node[] node, int id){
        this.N = N;
        this.node = new Node[N];
        this.id = id;
        System.arraycopy(node, 0, this.node, 0, node.length);
    }
    
    @Override
    public void run(){
        
        try {
            
            //Abrir socket para transmitir
            //Verificar nós nas vizinhanças
            //Ligar-se a nós na vizinhanças (join group)
            
            //Correr FSM(?)
            
            String TXip = node[id].ip;
            int TXport = node[id].porta;
            
            int initiator;
            
            if(id == 0){
                initiator = 1;
            } else{
                initiator = 0;
            }
            
            if(initiator == 1){
                InetAddress groupTX = InetAddress.getByName(TXip);
                MulticastSocket ms = new MulticastSocket();
                
                String toSend =" Teste ";
                byte[] buf = toSend.getBytes();
                
                DatagramPacket p = new DatagramPacket(buf, buf.length, groupTX, TXport);
                
                ms.send(p);
                
            } else{
               
                InetAddress groupRX = InetAddress.getByName(node[0].ip);
                MulticastSocket mr = new MulticastSocket(node[0].porta);
                byte[] buf = new byte[512];
                DatagramPacket r = new DatagramPacket(buf, buf.length);
                mr.receive(r);
                String str = new String(r.getData());
                
                System.out.println("Chegou: " + r);
            }
            
            /*lock.lock();
            try{
                System.out.println(id);
            } finally{
                lock.unlock();
            }
            /*
            try {
            MulticastSocket TXms = new MulticastSocket();
            } catch (IOException ex) {
            System.err.println("Error creating TX socket!");
            }*/
        } catch (IOException ex) {
            Logger.getLogger(NodeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
