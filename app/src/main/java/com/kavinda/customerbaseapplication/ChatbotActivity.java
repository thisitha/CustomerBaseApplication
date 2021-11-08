package com.kavinda.customerbaseapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.Request;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.squareup.okhttp.Request.*;

public class ChatbotActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    ArrayList<chatbotItem> chatbotItems = new ArrayList<>();
    String latestMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        recyclerView = findViewById(R.id.rv_messages);
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new chatbotAdapter(chatbotItems);

        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(myAdapter);


       chatbotItems.add(new chatbotItem("welcome to Agent Bot","true"));
//        chatbotItems.add(new chatbotItem("Hi how are you","false"));
//        chatbotItems.add(new chatbotItem("Im doing fine","true"));
//        chatbotItems.add(new chatbotItem("What are thr newly avilable plans","false"));
//        chatbotItems.add(new chatbotItem("There are various number of plans, What are you intrested in? ","true"));
//        chatbotItems.add(new chatbotItem("Well what are the new health care plans","false"));
        //chatbotItems.add(new chatbotItem("Personal Healthcare plans are avilable","true"));

       // recyclerView.set
    }
    public void newMessage(View v){
        TextView newMessage = findViewById(R.id.newMessage);
        latestMessage = newMessage.getText().toString();
        newMessage.setText("");
        if(newMessage.equals("")){

        }else {
            chatbotItems.add(new chatbotItem(latestMessage,"false"));
            recyclerView.setLayoutManager(myLayoutManager);
            recyclerView.setAdapter(myAdapter);

            getResponse(latestMessage);
        }




    }
    public void getResponse(String userQuestion){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url =" https://kavindachatbot.herokuapp.com/chat?chatInput="+userQuestion;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.e("TG", "onResponse: "+ response);
                        String extractedVal="";
                        try {
                            JSONObject reader = new JSONObject(response);
                             extractedVal =reader.getString("chatBotReply");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        chatbotItems.add(new chatbotItem(extractedVal,"true"));
                        recyclerView.setLayoutManager(myLayoutManager);
                        recyclerView.setAdapter(myAdapter);
                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());

                        //textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("TG", "onResponse: "+error.toString());
                //textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}