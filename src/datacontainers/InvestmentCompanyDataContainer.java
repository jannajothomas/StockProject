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

import datamodels.InvestmentCompany;

public class InvestmentCompanyDataContainer  {

    /** Simple container that stores elements as a list, can contain duplicates 
     *  Stores in FIFO order
     */
    private List<InvestmentCompany> companyList = new ArrayList<>();
    
    /** Container that stores elements as a set of unique elements
     *  Random ordering
     */
    private Set<InvestmentCompany> companySet = new HashSet<InvestmentCompany>();

    /** Container that stores elements as a map, can contain duplicates
     *  Order not enforced
     */
    private Map companyMap = new HashMap();

    
    
    public List<InvestmentCompany> getcompanyList() {
        return companyList;
    }

    public void setcompanyList(List<InvestmentCompany> companyList) {
        this.companyList = companyList;
    }

    public Set getcompanySet() {
        return companySet;
    }

    public void setStockQuoteSet(Set companySet) {
        this.companySet = companySet;
    }

    public Map getcompanyMap() {
        return companyMap;
    }

    public void setcompanyMap(Map companyMap) {
        this.companyMap = companyMap;
    }
}