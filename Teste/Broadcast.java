package broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Broadcast implements Runnable{
    
    int id;
    int grupo;
    public Broadcast(int id, int grupo){
        this.id = id;
        this.grupo = grupo;
    }
    
    public static void main(String[] args) {
        
        Thread[] t = new Thread[20];
        
        for(int i = 0; i< 20; i++){
            t[i] = new Thread(new Broadcast(i/2, i%2));
        }
        
        
        for(int i = 19; i>=0; i--){
            t[i].start();
        }
    }
    
    
    @Override
    public void run() {
        
        if(grupo == 0){
            //Apenas envia
            if(id == 0){

                try {

                    String str = "String a enviar";
                    byte[] buf;

                    buf = str.getBytes();

                    int porta = 9999;

                    MulticastSocket ms = new MulticastSocket();

                    InetAddress group = InetAddress.getByName("224.1.1.1");
                    DatagramPacket dp = new DatagramPacket(buf, buf.length, group, porta);
                    Thread.sleep(50);
                    ms.send(dp);

                    System.out.println("id= "+id+"; Iniciador enviou mensagem!");

                    ms.close();

                } catch (UnknownHostException ex) {
                    System.err.println("UnknownHostException");
                } catch (IOException ex) {
                    System.err.println("IOException");
                } catch (InterruptedException ex) {
                    System.err.println("InterruptedException");
                }

            }   //Apenas recebe 
            else{

                try {

                    byte[] buf = new byte[512];

                    int porta = 9999;


                    InetAddress group = InetAddress.getByName("224.1.1.1");

                    MulticastSocket ms = new MulticastSocket(porta);
                    ms.joinGroup(group);

                    DatagramPacket dp = new DatagramPacket(buf, buf.length);
                    System.out.println(id + " à espera");
                    ms.receive(dp);

                    String toPrint = new String(dp.getData());

                    System.out.println("group: "+grupo+"; id: "+id+"; Mensagem Recebida!");
                    System.out.println("group: "+grupo+"; id: "+id+" : " + toPrint);


                    ms.close();

                } catch (UnknownHostException ex) {
                    System.err.println("UnknownHostException");
                } catch (IOException ex) {
                    System.err.println("IOException");
                }



            }
        
    }if(grupo == 1){
    //Apenas envia
            if(id == 0){

                try {
                    byte[] buf = new byte[512];

                    int porta = 9999;
                    int porta1 = 9998;
                    
                    
                    InetAddress group = InetAddress.getByName("224.1.1.1");
                    DatagramPacket dp = new DatagramPacket(buf, buf.length);

                    MulticastSocket ms = new MulticastSocket(porta);
                    ms.joinGroup(group);
                    
                    ms.receive(dp);
                    
                    buf = dp.getData();
                    
                    ms.close();
                    
                    InetAddress group1 = InetAddress.getByName("224.1.1.2");
                    MulticastSocket ms1 = new MulticastSocket();
                    DatagramPacket dp1 = new DatagramPacket(buf, buf.length, group1, porta1);
                    Thread.sleep(50);
                    ms1.send(dp1);

                    System.out.println("id= "+id+"; Iniciador enviou mensagem!");

                    ms1.close();

                } catch (UnknownHostException ex) {
                    System.err.println("UnknownHostException");
                } catch (IOException ex) {
                    System.err.println("IOException");
                } catch (InterruptedException ex) {
                    System.err.println("InterruptedException");
                }

            }   //Apenas recebe 
            else{

                try {

                    byte[] buf = new byte[512];

                    int porta = 9998;


                    InetAddress group = InetAddress.getByName("224.1.1.2");

                    MulticastSocket ms = new MulticastSocket(porta);
                    ms.joinGroup(group);

                    DatagramPacket dp = new DatagramPacket(buf, buf.length);
                    System.out.println(id + " à espera");
                    ms.receive(dp);

                    String toPrint = new String(dp.getData());

                    System.out.println("group: "+grupo+"; id: "+id+"; Mensagem Recebida1!");
                    System.out.println("group: "+grupo+"; id: "+id+" : " + toPrint);


                    ms.close();

                } catch (UnknownHostException ex) {
                    System.err.println("UnknownHostException");
                } catch (IOException ex) {
                    System.err.println("IOException");
                }



            }
    }
    }
    
}
