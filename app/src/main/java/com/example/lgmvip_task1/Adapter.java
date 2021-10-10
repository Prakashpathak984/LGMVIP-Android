package com.example.lgmvip_task1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Model>
{
    Context context;
    List<Model> modelList;

    public Adapter(Context context, List<Model> list)
    {
        super(context, R.layout.testing, list);

        this.context = context;
        this.modelList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testing, null, true);

        TextView name = view.findViewById(R.id.name);
        TextView active = view.findViewById(R.id.active);
        TextView confirmed = view.findViewById(R.id.confirmed);
        TextView migrate = view.findViewById(R.id.migrate);
        TextView notes = view.findViewById(R.id.notes);
        TextView recovered = view.findViewById(R.id.recovered);
        TextView deceased = view.findViewById(R.id.deceased);
        TextView dconfirmed = view.findViewById(R.id.dconfirmed);
        TextView ddeceased = view.findViewById(R.id.ddeceased);
        TextView drecovered = view.findViewById(R.id.drecovered);

        name.setText(modelList.get(position).getName());
        active.setText(modelList.get(position).getActive());
        confirmed.setText(modelList.get(position).getConfirmed());
        migrate.setText(modelList.get(position).getMigrate());
        notes.setText(modelList.get(position).getNotes());
        recovered.setText(modelList.get(position).getRecovered());
        deceased.setText(modelList.get(position).getDeceased());
        dconfirmed.setText(modelList.get(position).getDconfirmed());
        ddeceased.setText(modelList.get(position).getDdeceased());
        drecovered.setText(modelList.get(position).getDrecovered());

        return view;
    }
}
