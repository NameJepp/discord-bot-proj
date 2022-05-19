package se.jepp.dcbot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoronaData {
    @JsonProperty("Province/State")
    private String province = "Province/State Hubei";

    @JsonProperty("Country/Region")
    private String country = "Country/Region China";
    private String lastUpdate = "Last Update 2020-03-22T09:43:06";
    private String confirmed = "Confirmed 67800";
    private String deaths = "Deaths 3144";
    private String recovered = "Recovered 59433";
    private String latitude = "Latitude 30.9756";
    private String longitude = "Longitude 112.2707";

    public String getProvince() {
        return province;
    }

    @JsonProperty("Province/State")
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String toString(){
        String result = "";
        result+=country;
        result+="->";
        result+=province;
        result+="->";
        result+=lastUpdate;
        result+="\n";
        return result;
    }
}

