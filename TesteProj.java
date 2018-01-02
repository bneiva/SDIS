/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeproj;

/**
 *
 * @author Nuno Moreira
 */
public class TesteProj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Packet p = new Packet();
        
        byte[] buf = p.encodeData(0, "So mesmo para para para para testar");
        
        int i;
        
        for(i = 0; i<buf.length; i++){
            //System.out.print(buf[i]+";");
        }
        
        p.decodeData(buf);
        for(i = 0; i<p.receiveData.length; i++){
            System.out.println((char)p.receiveData[i]);
        }
        
    }
    
}
