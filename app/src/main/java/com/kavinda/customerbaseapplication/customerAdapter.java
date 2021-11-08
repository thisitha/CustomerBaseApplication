package com.kavinda.customerbaseapplication;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.media.CamcorderProfile.get;

public class customerAdapter extends BaseAdapter {
    ArrayList<customer> Customer = new ArrayList<>();
    Context mContext;
    String Date;






    public customerAdapter(Context context, ArrayList<customer> Customer) {
        this.Customer = Customer;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Customer.size();
    }

    @Override
    public Object getItem(int position) {

        return Customer.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("thisi","Error");
        if (convertView ==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.customer,parent,false);
            Log.e("thisi","Error");
        } else Log.e("else caled","else called");
        customer tempCustomer =  (customer) getItem(position);
        Date = tempCustomer.getDate();
        if(Date.equals(new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()))){

            CardView card = (CardView)convertView.findViewById(R.id.customerInflate);
            card.setCardBackgroundColor(Color.GREEN);
            TextView name = (TextView)convertView.findViewById(R.id.firstname);

            name.setText(tempCustomer.getPolicy_number());
        }else{
//            customer tempCustomer =  (customer) getItem(position);

            TextView name = (TextView)convertView.findViewById(R.id.firstname);

            name.setText(tempCustomer.getPolicy_number());
        }





        return convertView;

    }

    public String getItenUsername(int position){
        customer tempname =  (customer) getItem(position);
       return tempname.getName();





    }
    public String getCustomerOccupation(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getOccupation();
    }
    public String getCustomerContact(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getContact();
    }
    public String getCustomerDOB(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getDOB();
    }
    public String getCutomerDate(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getDate();
    }

    public String getCustomerIncome(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getIncome();
    }
    public String getCustomerRemarks(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getRemarks();
    }
    public String getCustomerIDCard(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getID_Card();
    }
    public String getCustomerGender(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getGender();
    }

    public String getCustomerCivil(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getCivil_Status();
    }

    public String getCustomerSpouseDOB(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getSpouseDOB();
    }
    public String getChild1(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getChild1();
    }
    public String getChild2(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getChild2();
    }
    public String getChild3(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getChild3();
    }
    public String getChild4(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getChild4();
    }
    public String getPolicyName(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getPolicy_name();
    }
    public String getPolicyNumber(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getPolicy_number();
    }
    public String getPremiumAmount(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getPremium();
    }
    public String getCustomerAddress(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getAddress();
    }
    public String getCustomerMode(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getMode();
    }
    public String getCustomerTerm(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getTerm();
    }

    public String getCustomerExpiary(int position){
        customer tempname =  (customer) getItem(position);
        return tempname.getExpiary();
    }





}
