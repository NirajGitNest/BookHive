package com.tcet.bookhive.modal;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class OverdueModel {
    private final SimpleStringProperty bkname,stdname, finestatus;
    private final SimpleIntegerProperty dayslate,fineamount;
    private final SimpleObjectProperty<LocalDate> issuedate,returndate;
    
    public OverdueModel(String bkname,String stdname, LocalDate issuedate, LocalDate returndate,Integer dayslate,Integer fineamount,String finestatus) {
        this.bkname = new SimpleStringProperty(bkname);
        this.stdname = new SimpleStringProperty(stdname);
        this.issuedate = new SimpleObjectProperty<LocalDate>(issuedate);
        this.returndate = new SimpleObjectProperty<LocalDate>(returndate);
        this.dayslate = new SimpleIntegerProperty(dayslate);
        this.fineamount = new SimpleIntegerProperty(fineamount);
        this.finestatus = new SimpleStringProperty(finestatus);
    }
    public String getBkname() { return bkname.get(); }
    public String getStdname() { return stdname.get(); }
    public LocalDate getIssuedate() { return issuedate.get(); }
    public LocalDate getReturndate() { return returndate.get(); }
    public Integer getDayslate() { return dayslate.get(); }
    public Integer getFineamount() { return fineamount.get(); }
    public String getFinestatus() { return finestatus.get(); }
    
}
