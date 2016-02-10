package com.afpa.GESTIONREPAS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by sam on 21/10/2015.
 */
public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Aliment, Integer> daoAliment;
    private Dao<Repas, Date> daoRepas;
    private Dao<Constituer, Integer> daoConstituer;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Examen";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Aliment.class);
            TableUtils.createTable(connectionSource, Repas.class);
            TableUtils.createTable(connectionSource, Constituer.class);

            Aliment oeuf = new Aliment("Oeuf", "unite", 100);
            Aliment viande = new Aliment("Viande", "gramme", 215);
            Aliment kiwi = new Aliment("Kiwi", "unite", 60);
            Aliment pizza = new Aliment("Pizza", "part", 300);
            Aliment poire = new Aliment("Poire", "unite", 100);
            Aliment pain = new Aliment("Pain", "tranche", 75);
            Aliment orange = new Aliment("Orange", "unite", 85);
            Aliment glace = new Aliment("Glace", "cL", 400);
            Aliment yaourt = new Aliment("Yaourt", "cL", 100);
            getDaoAliment().createIfNotExists(oeuf);
            getDaoAliment().createIfNotExists(viande);
            getDaoAliment().createIfNotExists(kiwi);
            getDaoAliment().createIfNotExists(pizza);
            getDaoAliment().createIfNotExists(poire);
            getDaoAliment().createIfNotExists(pain);
            getDaoAliment().createIfNotExists(orange);
            getDaoAliment().createIfNotExists(glace);
            getDaoAliment().createIfNotExists(yaourt);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Aliment.class, true);
            TableUtils.dropTable(connectionSource, Constituer.class, true);
            TableUtils.dropTable(connectionSource, Repas.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<Aliment, Integer> getDaoAliment() throws SQLException {
        if(daoAliment == null){
            daoAliment = getDao(Aliment.class);
        }
        return daoAliment;
    }
    public Dao<Constituer, Integer> getDaoConstituer() throws SQLException {
        if(daoConstituer == null){
            daoConstituer = getDao(Constituer.class);
        }
        return daoConstituer;
    }
    public Dao<Repas, Date> getDaoRepas() throws SQLException {
        if(daoRepas == null){
            daoRepas = getDao(Repas.class);
        }
        return daoRepas;
    }

    public void release() {
        OpenHelperManager.releaseHelper();
    }
}
