/*
 *  This Class contains several containers which can hold objects 
 *  created in the UI
 */
package datacontainers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datamodels.Broker;

@XmlRootElement(name = "brokerList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BrokerDataContainer  {

   public BrokerDataContainer() {
   }

   /**
    * Simple container that stores elements as a list, can contain duplicates
    * Stores in FIFO order
    */
   // XML document element name
   @XmlElement(name = "broker")
   private List<Broker> brokerList = new ArrayList<>();

   /**
    * Container that stores elements as a set of unique elements Random ordering
    */
   private Set brokerSet = new HashSet();

   /**
    * Container that stores elements as a map, can contain duplicates Order not
    * enforced
    */
   private Map brokerMap = new HashMap();

   // getters and setters
   public List<Broker> getBrokerList() {
      return brokerList;
   }

   public void setBrokerList(List<Broker> brokerList) {
      this.brokerList = brokerList;
   }

   public Set getBrokerSet() {
      return brokerSet;
   }

   public void setBrokerSet(Set brokerSet) {
      this.brokerSet = brokerSet;
   }

   public Map getBrokerMap() {
      return brokerMap;
   }

   public void setBrokerMap(Map brokerMap) {
      this.brokerMap = brokerMap;
   }

}