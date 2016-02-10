package com.afpa.GESTIONREPAS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by reith on 10/02/2016.
 */
public class FragmentRepas extends DatabaseHelperFragment implements View.OnClickListener {

    private ArrayAdapter<Constituer> alimentArrayAdapter;
    private ListView listViewAliments;
    private TextView tvNbCalories;
    private Repas repasCourant;
    private int nbCalories;
    private Button boutonSauvegarder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_fragment_repas, container, false);
        this.tvNbCalories = (TextView) view.findViewById(R.id.tvNbCalories);
        this.boutonSauvegarder = (Button) view.findViewById(R.id.btnSave);
        this.boutonSauvegarder.setOnClickListener(this);
        this.listViewAliments = (ListView) view.findViewById(R.id.listViewRepas);
        this.alimentArrayAdapter = new ArrayAdapter<Constituer>(
                getActivity(),
                android.R.layout.simple_list_item_1
        );
        this.listViewAliments.setAdapter(this.alimentArrayAdapter);
        this.repasCourant = new Repas(new Date());
        this.nbCalories = 0;
        return view;
    }

    public void updateRepas(Aliment nouvelAliment, int quantite){
        this.alimentArrayAdapter.add(new Constituer(nouvelAliment, quantite));
        this.majNbCalories(nouvelAliment, quantite);
    }

    private void majNbCalories(Aliment nouvelAliment, int quantite) {
        this.nbCalories += nouvelAliment.getNbCalories() * quantite;
        this.tvNbCalories.setText(""+this.nbCalories);
    }

    @Override
    public void onClick(View v) {
        try {
            getHelper().getDaoRepas().create(this.repasCourant);
            for(int i = 0; i<this.alimentArrayAdapter.getCount(); i++){
                Constituer constituer =  this.alimentArrayAdapter.getItem(i);
                constituer.setRepas(this.repasCourant);
                getHelper().getDaoConstituer().create(constituer);
            }
            reset();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        this.repasCourant = new Repas();
        this.alimentArrayAdapter.clear();
        this.tvNbCalories.setText("");
        this.nbCalories = 0;
    }
}
