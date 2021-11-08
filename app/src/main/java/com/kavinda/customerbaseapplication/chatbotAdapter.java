package com.kavinda.customerbaseapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatbotAdapter extends RecyclerView.Adapter<chatbotAdapter.chatbotViewHolder> {
    private ArrayList<chatbotItem> MychatbotItem;

        public chatbotAdapter(ArrayList<chatbotItem> chatbotItems){
            MychatbotItem = chatbotItems;
        }

    @NonNull
    @Override
    public chatbotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
       chatbotViewHolder cvh = new chatbotViewHolder(v);
       return cvh;


    }

    @Override
    public void onBindViewHolder(@NonNull chatbotViewHolder holder, int position) {
            String reciveMode;
        chatbotItem currentmessage = MychatbotItem.get(position);
        reciveMode = currentmessage.getReciveMode().toString();
        if(reciveMode.equals("true")){
            holder.messageTxt.setVisibility(View.INVISIBLE);
            holder.messageTxt.setText(currentmessage.getMessageText());
        }
        else{
            holder.reciveText.setVisibility(View.INVISIBLE);
            holder.reciveText.setText(currentmessage.getMessageText());
        }
        holder.messageTxt.setText(currentmessage.getMessageText());
        holder.reciveText.setText(currentmessage.getMessageText());
        //holder.messageTxt.setText(currentmessage.getMessageText());

       // holder.messageTxt.setVisibility(fa);
    }

    @Override
    public int getItemCount() {
        return MychatbotItem.size();
    }

    public static class  chatbotViewHolder extends  RecyclerView.ViewHolder{
        public TextView messageTxt;
        public  TextView reciveText;

        public chatbotViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTxt = itemView.findViewById(R.id.sendMessageTxt);
            reciveText = itemView.findViewById(R.id.reciveMessageTxt);


        }
    }
}
