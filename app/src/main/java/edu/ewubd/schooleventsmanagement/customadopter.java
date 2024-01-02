package edu.ewubd.schooleventsmanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class customadopter extends ArrayAdapter<DB> {
    private Activity context;
    private List<DB> eventlist;

    public customadopter(Activity context, List<DB> eventlist) {
        super(context, R.layout.sample_layout, eventlist);
        this.context = context;
        this.eventlist = eventlist;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);
        DB db = eventlist.get(position);
        TextView t1 =view.findViewById(R.id.eventnamemm);
        TextView t2 =view.findViewById(R.id.eventdatemm);
        TextView t3 = view.findViewById(R.id.radmm);
        t1.setText("Event name:"+db.getEventName());
        t2.setText("Event date:"+db.getEventdate());
        t3.setText("Holiday:"+db.getRad());
        return view;
    }
}
