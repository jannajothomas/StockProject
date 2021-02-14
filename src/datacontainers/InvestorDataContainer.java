/*
 *  This Class contains several containers which can hold classroom objects 
 *  created in the UI
 */
package datacontainers;

import datamodels.Investor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvestorDataContainer  {

    /** Simple container that stores elements as a list, can contain duplicates 
     *  Stores in FIFO order
     */
    private List<Investor> investorList = new ArrayList<>();
    
    public List<Investor> getInvestorList() {
        return investorList;
    }

    public void setInvestorList(List<Investor> investorList) {
        this.investorList = investorList;
    }
}