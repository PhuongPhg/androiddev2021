package vn.edu.usth.weather;

import java.util.Date;

public class ForecastData {
    Date date;
    Double maxtemp, mintemp;
    String condition;

    public ForecastData(Date date, Double maxtemp, Double mintemp, String condition){
        this.date = date;
        this.maxtemp = maxtemp;
        this.mintemp = mintemp;
    }

    public Date getDate() {
        return date;
    }

    public Double getMaxtemp() {
        return maxtemp;
    }

    public Double getMintemp() {
        return mintemp;
    }

    public String getCondition() {
        return condition;
    }
}
