/*
 *  This Class contains several containers which can hold objects 
 *  created in the UI
 */
package datacontainers;

import datamodels.Broker;
import java.util.ArrayList;
import java.util.List;

public class BrokerDataContainer  {

    /** Simple container that stores elements as a list, can contain duplicates 
     *  Stores in FIFO order
     */
    private List<Broker> brokerList = new ArrayList<>();
    
    public List<Broker> getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(List<Broker> brokerList) {
        this.brokerList = brokerList;
    }
}