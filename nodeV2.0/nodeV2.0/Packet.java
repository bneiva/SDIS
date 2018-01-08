package node;

public class Packet {
    
/*
Packet 
    
Preamble        Start Flag      Counter         Data length     App Data
(8bytes) 0x0 |      0x7A    |   (1 byte)    |     1 byte    |     0-127 bytes   
*/

    
        public byte flag = 0x7A;
        public byte dataLenRec = 0;
        public int counterReceive = 0;
        public byte[] receiveData;
        
        int flagN = 0;
        int counterN = 0;
        int dataLenN = 0;
        int appDataN = 0;
             
        
        public void decodeData(byte[] buf){
            
            int i;
            int k;
            
            for( i = 0; i<512; i++){
                if(buf[i] == 0x7A){
                    flagN = i;
                    counterN = i+1;
                    dataLenN = i+2;
                    appDataN = i+3;
                    i = 513;
                }
            }
            
            counterReceive = buf[counterN];
            dataLenRec = buf[dataLenN];
            
            receiveData = new byte[dataLenRec];
            
            for (k = 0; k<dataLenRec; k++){
                receiveData[k] = buf[appDataN+k];
            }
            
        }
        
        public byte[] encodeData(int counterSend, String sendData){
            
            byte[] packetSend;
            
            byte[] dataSend = sendData.getBytes();

            
            byte dataLenSend = (byte) dataSend.length;
            byte counterToSend = (byte) counterSend;
            
            packetSend = new byte[dataSend.length+11];
            
            int i;
            for(i = 0; i<8; i++){
                packetSend[i] = 0x0;
            }
            
            packetSend[8] = 0x7A;
            packetSend[9] = counterToSend;
            packetSend[10] = dataLenSend;
            
            int k = 0;
            for(i = 11; i<(dataSend.length+11); i++){
                packetSend[i] = dataSend[k];
                k++;
            }
            
            return packetSend;
        }
                
}
    
