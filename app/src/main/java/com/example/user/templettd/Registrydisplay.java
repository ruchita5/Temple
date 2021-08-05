package com.example.user.templettd;

import java.util.ArrayList;

public class Registrydisplay {
    public String sdate,edate,title;
    public int eventid;

    public int getEventid(){return eventid;}

    public void setEventid(int id){
        eventid=id;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<Registrydisplay> arrdatareg=new ArrayList<Registrydisplay>();

}
