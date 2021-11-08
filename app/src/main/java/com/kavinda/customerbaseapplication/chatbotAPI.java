package com.kavinda.customerbaseapplication;


import android.widget.TextView;
import java.net.URI;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


class Detect {

    //private TextView moodT;

    public static String emotionType = "";
    public static double emotionValue = 0;

    //System.getenv("FACE_SUBSCRIPTION_KEY");
    private static final String subscriptionKey = "909fa40fd3e1474b8c7359887afce49e";


    //System.getenv("FACE_ENDPOINT");
    private static final String endpoint = "https://facedetectionwithemotion.cognitiveservices.azure.com/";

    String returnMood;

    public String testFace(String URL) {
        String imageWithFaces = "{\"url\":\"" + URL + "\"}";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = HttpClientBuilder.create().build();
                    try {
                        URIBuilder builder = new URIBuilder(endpoint + "/face/v1.0/detect");
                        // Request parameters. All of them are optional.
                        builder.setParameter("detectionModel", "detection_01");
                        builder.setParameter("returnFaceId", "true");
                        builder.setParameter("returnFaceAttributes", "emotion");
                        // Prepare the URI for the REST API call
                        URI uri = builder.build();
                        HttpPost request = new HttpPost(uri);
                        // Request headers.
                        request.setHeader("Content-Type", "application/json");
                        request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
                        // Request body.
                        StringEntity reqEntity = new StringEntity(imageWithFaces);
                        request.setEntity(reqEntity);
                        // Execute the REST API call and get the response entity.
                        HttpResponse response = httpclient.execute(request);
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            // Format and display the JSON response.
                            System.out.println("REST Response:\n");
                            String jsonString = EntityUtils.toString(entity).trim();
                            if (jsonString.charAt(0) == '[') {
                                JSONArray jsonArray = new JSONArray(jsonString);
                                JSONObject emotionObject = jsonArray.getJSONObject(0).getJSONObject("faceAttributes").getJSONObject("emotion");
                                Iterator<String> keys = emotionObject.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    double value = emotionObject.getDouble(key);
                                    if (value >= emotionValue) {
                                        emotionValue = value;
                                        emotionType = key;
                                    }
                                }
                                // return the emotion type
                                System.out.println(emotionType);
                                returnMood=null;
                                returnMood = emotionType;
                                System.out.println(emotionValue);
                                System.out.println(jsonArray.toString(2));
                            } else if (jsonString.charAt(0) == '{') {
                                JSONObject jsonObject = new JSONObject(jsonString);
                                System.out.println(jsonObject.toString(2));
                            } else {
                                System.out.println(jsonString);
                            }
                        }
                    } catch (Exception e) {
                        // Display error message.
                        // Log.e("d", e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            thread.start();
            while (returnMood == null) {
            }
            return returnMood + " !";
        } catch (Exception e) {
            return "returnMood" + " !";
        }

    }

}
