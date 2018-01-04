/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinestate;

import java.lang.Thread.State;
//import static machinestate.MachineState.State.GLOSSY_RECEIVE;
//import static machinestate.MachineState.State.GLOSSY_TRANSMIT;
//import static machinestate.MachineState.State.GLOSSY_WAIT;
//import static machinestate.MachineState.State.GLOSSY_RECEIVE;




/**
 *
 * @author ilia
 */
public class MachineState {
      
    public State GLOSSY_TRANSMIT;
    public State GLOSSY_RECEIVE;
    public State GLOSSY_OFF;
    public State GLOSSY_WAIT;
    
    public char node;
    public State state; 
    
    public int N = 5;
    public int nTx = 0;
    public int radio_time = 1000;
    
       
    public int radio_timeout = 5;
    public int tx_count;
    public int rx_count;
    public int rx_begin= 0;
    public int rx_fail = 0;
    public int start_receiver =0 ;
    public int start_initiator =0;
    public int succeeds = 0; 
    public int fails = 0;
    
    
    public int relay_counter = 0;
    
    
    private static MulticastPublisher tx = new MulticastPublisher();
    private static MulticastReceiver rx = new MulticastReceiver();
    private static MulticastReceiver initiator = new MulticastReceiver();
    private static ExecutionTimer radio = new ExecutionTimer();
    
    // stop glossy é posto quando? 
    
    public void main () throws Exception {
        
        
        //initiator.start();
        //start_initiator = 1;
        
        
        if (start_receiver == 1 ) {    //  se for um recetor 
            state = GLOSSY_WAIT;
        } else if (start_initiator == 1 ){   // se for um iniciador
            relay_counter = 0; 
            state = GLOSSY_TRANSMIT;
        }
        
        if(state == GLOSSY_WAIT) {
            //   radio_on ()
            
            radio.activateTimer("start", radio_time, radio_timeout); 
            fails = 0;
            rx_begin = 1;
            start_receiver = 0;
            state  = GLOSSY_RECEIVE;    
        } else if(state == GLOSSY_RECEIVE) {
            //     start_rx()
            rx.start();
            if(rx.error == 1)
            {
                fails =1;
            } else if (rx.error == 0 )
            {
                relay_counter++;
                succeeds = 1;
            }
     
            rx_begin = 0;
            if( fails == 1) {
                state = GLOSSY_WAIT;
            } else if (succeeds == 1) {
                
                state = GLOSSY_TRANSMIT;
            }
        } else if(state == GLOSSY_TRANSMIT) {
            //  start tx
            
            tx.start();
            
            start_initiator = 0;
            succeeds = 0;
            
            if(nTx == N) {
                state = GLOSSY_OFF;
            } else if (nTx < N )  {
                nTx++;
                state = GLOSSY_WAIT;
            }
        }
        else if (state == GLOSSY_OFF) {
            //radio_off()
              radio.activateTimer("stop", radio_time, radio_timeout);
        }
    }  
    
}
