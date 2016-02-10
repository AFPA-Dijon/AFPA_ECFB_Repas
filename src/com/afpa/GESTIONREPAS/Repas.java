package com.afpa.GESTIONREPAS;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;


public class Repas {

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false)
    private Date date;

    public Repas(){}
    public Repas(Date date){
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
