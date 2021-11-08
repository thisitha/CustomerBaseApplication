package com.kavinda.customerbaseapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class prospectorAdapter extends BaseAdapter {

    ArrayList<prospector> Prospector = new ArrayList<>();
    Context mContext;
    String Date;


    public prospectorAdapter(Context context, ArrayList<prospector> Prospector) {
        this.Prospector = Prospector;
        this.mContext = context;



    }

    @Override
    public int getCount() {
        return Prospector.size();
    }

    @Override
    public Object getItem(int position) {

        return Prospector.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("thisi","Error");
        if (convertView ==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.prospector,parent,false);
            Log.e("thisi","Error");
        } else Log.e("else caled","else called");
        prospector tempCustomer =  (prospector) getItem(position);
       Date= tempCustomer.getDate();
        CardView cardView = (CardView)convertView.findViewById(R.id.prospectorInflator);
        if(Date.equals(new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()))){

            TextView name = (TextView)convertView.findViewById(R.id.firstname);
            TextView lName = (TextView)convertView.findViewById(R.id.lastname);
            cardView.setCardBackgroundColor(Color.GREEN);
            name.setText(tempCustomer.getName());
            lName.setText(tempCustomer.getContact());
        }else{
            TextView name = (TextView)convertView.findViewById(R.id.firstname);
            TextView lName = (TextView)convertView.findViewById(R.id.lastname);
            //cardView.setCardBackgroundColor(Color.GREEN);
            name.setText(tempCustomer.getName());
            lName.setText(tempCustomer.getAddress());
        }



        return convertView;

    }

    public String getItenUsername(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getName();



    }



    public String getProspectorOccupation(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getOccupation();
    }
    public String getProspectorContact(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getContact();
    }
    public String getProspectorDOB(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getDOB();
    }
    public String getProspectorDate(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getDate();
    }
    public String getProspectorDependents(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getDependents();
    }
    public String getProspectorIncome(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getIncome();
    }
    public String getProspectorRemarks(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getRemarks();
    }
    public String getProspectorAddress(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getAddress();
    }
    public String getProspectorAAddress(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getAppointmentAddress();
    }
    public String getProspectorATime(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getAppointmentTime();
    }
    public String getProspectorsADate(int position){
        prospector tempname =  (prospector) getItem(position);
        return tempname.getAppointmentDate();
    }


}
