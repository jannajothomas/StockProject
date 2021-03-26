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

import datamodels.InvestmentCompany;

// XML document list element name
@XmlRootElement(name = "investmentCompanyList")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestmentCompanyDataContainer  {

   /**
    * Simple container that stores elements as a list, can contain duplicates
    * Stores in FIFO order
    */
   // XML document element name
   @XmlElement(name = "investmentCompany")  
   private List<InvestmentCompany> investmentCompanyList = new ArrayList<>();

   /**
    * Container that stores elements as a set of unique elements Random ordering
    */
   private Set investmentCompanySet = new HashSet();

   /**
    * Container that stores elements as a map, can contain duplicates Order not
    * enforced
    */
   private Map investmentCompanyMap = new HashMap();

   // getters and setters
   
   public List<InvestmentCompany> getInvestmentCompanyList() {
      return investmentCompanyList;
   }

   public void setInvestmentCompanyList(List<InvestmentCompany> investmentCompanyList) {
      this.investmentCompanyList = investmentCompanyList;
   }

   public Set getInvestmentCompanySet() {
      return investmentCompanySet;
   }

   public void setInvestmentCompanySet(Set investmentCompanySet) {
      this.investmentCompanySet = investmentCompanySet;
   }

   public Map getInvestmentCompanyMap() {
      return investmentCompanyMap;
   }

   public void setInvestmentCompanyMap(Map investmentCompanyMap) {
      this.investmentCompanyMap = investmentCompanyMap;
   }


}