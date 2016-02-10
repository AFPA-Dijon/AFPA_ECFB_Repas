package com.afpa.GESTIONREPAS;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MyActivity extends Activity implements FragmentAliments.FragmentAlimentsListener{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void OnAlimentSelected(Aliment alimentSelectionne, int quantite) {
        FragmentRepas fr =
                (FragmentRepas) getFragmentManager().findFragmentById(R.id.fragmentRepas);
        fr.updateRepas(alimentSelectionne, quantite);
    }
}
