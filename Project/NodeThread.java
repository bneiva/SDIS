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
    int maxLvl;

    public NodeThread(int N, Node[] node, int id, int maxLvl){
        this.N = N;
        this.node = new Node[N];
        this.id = id;
        this.maxLvl = maxLvl;
        System.arraycopy(node, 0, this.node, 0, node.length);
    }
    
    @Override
    public void run(){
        
        try {
            int[] neigRxList = node[id].getRxList();
            int[] neigTxList = node[id].getTxList();
            
            if(neigTxList.length == 0){
                int[] RxNeig = node[id].getRxList();
                Thread[] t = new Thread[RxNeig.length];
                for(int i = 0; i<RxNeig.length; i++){
                    t[i] = new Thread(new RxSocket(node[RxNeig[i]]));
                }
                for(int i = (RxNeig.length-1); i>=0; i--){
                    t[i].start();
                }
                 
            } else if(neigRxList.length == 0){
                String msg = "Mensagem a enviar";
                
                Thread t = new Thread(new TxSocket(node[id], msg));
                
                t.start();
                t.join();
            } else {
                int[] RxNeig = node[id].getRxList();
                Thread[] t = new Thread[RxNeig.length];
                
                Thread xT = new Thread();
                
                byte[] receivedStr;
                
                for(int i = 0; i<RxNeig.length; i++){
                    t[i] = new Thread(new RxTxSocket(node[RxNeig[i]], node[id]));
                }
                
                for(int i = (RxNeig.length-1); i>=0; i--){
                    t[i].start();


                }
                
                for(int i = (RxNeig.length-1); i>=0; i--){
                    t[i].join();
                }
            }
            
            
            
           
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
