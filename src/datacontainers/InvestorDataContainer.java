/*
 *  This Class contains several containers which can hold classroom objects 
 *  created in the UI
 */
package datacontainers;

import datamodels.Investor;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "investorList")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestorDataContainer  {

    /** Simple container that stores elements as a list, can contain duplicates 
     *  Stores in FIFO order
     */
	 @XmlElement(name = "investor")  
    private List<Investor> investorList = new ArrayList<>();
    
    public List<Investor> getInvestorList() {
        return investorList;
    }

    public void setInvestorList(List<Investor> investorList) {
        this.investorList = investorList;
    }
}