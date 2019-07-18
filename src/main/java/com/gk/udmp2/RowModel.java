package com.gk.udmp2;

import java.util.List;

public class RowModel {
    String tradeDate;
    String PartRef;
    List treadeId;
    List salary;
    List role;
;

    public RowModel() {
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getPartRef() {
        return PartRef;
    }

    public void setPartRef(String partRef) {
        PartRef = partRef;
    }

    public List getTreadeId() {
        return treadeId;
    }

    public void setTreadeId(List treadeId) {
        this.treadeId = treadeId;
    }

    public List getSalary() {
        return salary;
    }

    public void setSalary(List salary) {
        this.salary = salary;
    }

    public List getRole() {
        return role;
    }

    public void setRole(List role) {
        this.role = role;
    }
}
