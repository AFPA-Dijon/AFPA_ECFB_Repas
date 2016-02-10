package com.afpa.GESTIONREPAS;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;

/**
 * Created by reith on 10/02/2016.
 */
public class FragmentAliments extends DatabaseHelperFragment implements AdapterView.OnItemClickListener {

    private FragmentAlimentsListener fragmentAlimentsListener;
    private ListView listViewAliments;
    private ArrayAdapter<Aliment> alimentArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_fragment_aliments, container, false);
        this.listViewAliments = (ListView) view.findViewById(R.id.listeAliments);
        try {
            this.alimentArrayAdapter = new ArrayAdapter<Aliment>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    getHelper().getDaoAliment().queryForAll()
            );
            this.listViewAliments.setAdapter(this.alimentArrayAdapter);
            this.listViewAliments.setOnItemClickListener(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }

    public interface FragmentAlimentsListener {
        void OnAlimentSelected(Aliment alimentSelectionne, int quantite);
    }

    @Override
    public void onAttach(Activity activity) {
        if(activity instanceof FragmentAlimentsListener){
            this.fragmentAlimentsListener = (FragmentAlimentsListener) activity;
        }
        else {
            throw new ClassCastException("L'activité doit implémenter FragmentAlimentsListener");
        }
        super.onAttach(activity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Aliment choix = this.alimentArrayAdapter.getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Veuillez saisir une quantite en " + choix.getTypeMesure());
        EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!input.getText().toString().equals("")){
                    int qte =  Integer.parseInt(input.getText().toString());
                    if(qte > 0){
                        FragmentAliments.this.fragmentAlimentsListener.OnAlimentSelected(
                                choix,
                                Integer.parseInt(input.getText().toString())
                        );
                    }
                }
            }
        }).show();
    }


}
