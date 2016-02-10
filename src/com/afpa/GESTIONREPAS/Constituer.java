package com.afpa.GESTIONREPAS;

import com.j256.ormlite.field.DatabaseField;


public class Constituer {

    public static final String ALIMENT_ID_COLUMN_NAME = "aliment_id";
    public static final String REPAS_ID_COLUMN_NAME = "repas_id";
    public static final String QUANTITE_COLUMN_NAME = "quantite";

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField (foreign = true, uniqueCombo = true, foreignAutoRefresh = true, columnName = ALIMENT_ID_COLUMN_NAME)
    private Aliment alimentId;

    @DatabaseField (foreign = true, uniqueCombo = true, foreignAutoRefresh = true, columnName = REPAS_ID_COLUMN_NAME)
    private Repas repasId;

    @DatabaseField (persisted = true, canBeNull = true, columnName = QUANTITE_COLUMN_NAME)
    private int quantite;

    public Constituer(){}

    public Constituer(Aliment alimentId, Repas repasId){
        this.alimentId = alimentId;
        this.repasId = repasId;
    }
    public Constituer(Aliment alimentId, Repas repasId, int quantite){
        this.alimentId = alimentId;
        this.repasId = repasId;
        this.quantite = quantite;
    }

    public Constituer(Aliment alimentId, int quantite) {
        this.alimentId = alimentId;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aliment getAliment() {
        return alimentId;
    }

    public void setAliment(Aliment alimentId) {
        this.alimentId = alimentId;
    }

    public Repas getRepas() {
        return repasId;
    }

    public void setRepas(Repas repasId) {
        this.repasId = repasId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return this.alimentId.getIntitule()+
                "  X"+this.getQuantite()+" "+
                this.alimentId.getTypeMesure()+"(s)";
    }
}
