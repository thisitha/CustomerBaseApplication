package com.kavinda.customerbaseapplication;

import  android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class paymentAdapter extends BaseAdapter {
    ArrayList<payment> Payment = new ArrayList<>();
    Context mContext;

    public paymentAdapter(ArrayList<payment> payment, Context mContext) {
        Payment = payment;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Payment.size();
    }

    @Override
    public Object getItem(int position) {
        return Payment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("thisi","Error");
        if (convertView ==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.payment,parent,false);
            Log.e("thisi","Error");
        } else Log.e("else caled","else called");
        payment tempCustomer =  (payment) getItem(position);
        TextView name = (TextView)convertView.findViewById(R.id.firstname);
        TextView lName = (TextView)convertView.findViewById(R.id.lastname);
      //  TextView age = (TextView)convertView.findViewById(R.id.age);
        name.setText(tempCustomer.getAmount());
        lName.setText(tempCustomer.getMonth());
       // age.setText(tempCustomer.getDOB());

        return convertView;
    }
}
