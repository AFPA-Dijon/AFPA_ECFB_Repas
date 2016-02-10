package com.afpa.GESTIONREPAS;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by sam on 30/10/2015.
 */
public class Aliment {

    public static final String INTITULE_COLUMN_NAME = "aliment";
    public static final String NBCALORIES_COLUMN_NAME = "nb_calories";
    public static final String TYPEMESURE_COLUMN_NAME = "type_mesure";

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField (canBeNull = false, unique = true, columnName = INTITULE_COLUMN_NAME)
    private String intitule;

    @DatabaseField (canBeNull = false, columnName = NBCALORIES_COLUMN_NAME)
    private int nbCalories;

    @DatabaseField (canBeNull = false, columnName = TYPEMESURE_COLUMN_NAME)
    private String typeMesure;

    public Aliment(){}

    public Aliment(String intitule, String typeMesure, int nbCalories){
        this.intitule = intitule;
        this.typeMesure = typeMesure;
        this.nbCalories = nbCalories;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getNbCalories() {
        return nbCalories;
    }

    public void setNbCalories(int nbCalories) {
        this.nbCalories = nbCalories;
    }

    public String getTypeMesure() {
        return typeMesure;
    }

    public void setTypeMesure(String typeMesure) {
        this.typeMesure = typeMesure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.intitule.toUpperCase()+" : "+
                this.nbCalories+" calories par "+
                this.getTypeMesure();
    }
}
