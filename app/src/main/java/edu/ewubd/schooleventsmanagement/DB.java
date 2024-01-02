package edu.ewubd.schooleventsmanagement;

public class DB {
    private String eventName;
    private String eventdate;
    private String rad;

    public DB(){

    }
    public DB(String eventName, String eventdate, String rad) {
        this.eventName = eventName;
        this.eventdate = eventdate;
        this.rad = rad;

    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getRad() {
        return rad;
    }

    public void setRad(String rad) {
        this.rad = rad;
    }


}
