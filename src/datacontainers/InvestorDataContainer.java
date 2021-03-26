/*
 *  This Class contains several containers which can hold classroom objects 
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

import datamodels.Investor;

@XmlRootElement(name = "investorList")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestorDataContainer  {

   /**
    * Simple container that stores elements as a list, can contain duplicates
    * Stores in FIFO order
    */
   // XML document element name
   @XmlElement(name = "investor")
   private static List<Investor> investorList = new ArrayList<>();

   /**
    * Container that stores elements as a set of unique elements Random ordering
    */
   private Set investorSet = new HashSet();

   /**
    * Container that stores elements as a map, can contain duplicates Order not
    * enforced
    */
   private Map investorMap = new HashMap();

   // getters and setters
   public static List<Investor> getInvestorList() {
      return investorList;
   }

   public void setInvestorList(List<Investor> investorList) {
      this.investorList = investorList;
   }

   public Set getInvestorSet() {
      return investorSet;
   }

   public void setInvestorSet(Set investorSet) {
      this.investorSet = investorSet;
   }

   public Map getInvestorMap() {
      return investorMap;
   }

   public void setInvestorMap(Map investorMap) {
      this.investorMap = investorMap;
   }

}