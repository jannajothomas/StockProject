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
    
    /** Container that stores elements as a set of unique elements
     *  Random ordering
     */
    private Set<Investor> investorSet = new HashSet<Investor>();

    /** Container that stores elements as a map, can contain duplicates
     *  Order not enforced
     */
    private Map investorMap = new HashMap();

    
    
    public List<Investor> getInvestorList() {
        return investorList;
    }

    public void setInvestorList(List<Investor> investorList) {
        this.investorList = investorList;
    }

    public Set getInvestorSet() {
        return investorSet;
    }

    public void setStockQuoteSet(Set InvestorSet) {
        this.investorSet = InvestorSet;
    }

    public Map getInvestorMap() {
        return investorMap;
    }

    public void setInvestorMap(Map investorMap) {
        this.investorMap = investorMap;
    }
}