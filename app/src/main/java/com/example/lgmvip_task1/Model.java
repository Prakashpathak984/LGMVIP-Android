package com.example.lgmvip_task1;

public class Model {
    String name,notes,active,confirmed, migrate,deceased,recovered,dconfirmed,ddeceased,drecovered;

    Model(String name,String notes,String active,String confirmed, String migrate,String deceased,
          String recovered,String dconfirmed,String ddeceased,String drecovered)
    {
        this.name = name;
        this.notes = notes;
        this.active = active;
        this.confirmed = confirmed;
        this.migrate = migrate;
        this.deceased = deceased;
        this.recovered = recovered;
        this.dconfirmed = dconfirmed;
        this.ddeceased = ddeceased;
        this.drecovered = drecovered;
    }
    Model(String stateName, String totalActive,String totalConfirmed,String totalMigrated,String totalDeceased,
          String totalRecovered, String totalDconfimed, String totalDdeceased,String totalDrecovered)
    {
        this.name = stateName;
        this.active = totalActive;
        this.confirmed = totalConfirmed;
        this.migrate = totalMigrated;
        this.deceased = totalDeceased;
        this.recovered = totalRecovered;
        this.dconfirmed = totalDconfimed;
        this.ddeceased = totalDdeceased;
        this.drecovered = totalDrecovered;

    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getMigrate() {
        return migrate;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDconfirmed() {
        return dconfirmed;
    }

    public String getDdeceased() {
        return ddeceased;
    }

    public String getDrecovered() {
        return drecovered;
    }
}
