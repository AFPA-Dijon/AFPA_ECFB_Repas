package com.afpa.GESTIONREPAS;

import android.app.Fragment;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by reith on 10/02/2016.
 */
public class DatabaseHelperFragment extends Fragment {

    private static DatabaseOpenHelper databaseOpenHelper;

    public DatabaseOpenHelper getHelper(){
        if(databaseOpenHelper == null){
            databaseOpenHelper = new DatabaseOpenHelper(getActivity());
        }
        return databaseOpenHelper;
    }

    @Override
    public void onDestroyView() {
        if(databaseOpenHelper != null){
            databaseOpenHelper.release();
            databaseOpenHelper = null;
        }
        super.onDestroyView();
    }
}
