package com.loveboyuan.smarttrader;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Created by boyuangu on 2015-11-02.
 */
public class InventoryController {
    private static Inventory inventory = null;
    private static Gson gson = new Gson();
    private static final String TAG = "InventoryController";

    static public Inventory getInventoryModel() {
        if (inventory == null) {
            inventory = new Inventory();
            return inventory;

        } else {
            return inventory;

        }

    }

    public static void addItem(Item item) {



        HttpClient httpClient = new DefaultHttpClient();

        try {

            HttpPost addRequest = new HttpPost(getInventoryModel().getResourceUrl() + item.getName());
            Log.e("url", getInventoryModel().getResourceUrl() + item.getName());



            StringEntity stringEntity = new StringEntity(gson.toJson(item));
            Log.e("gsonitem", gson.toJson(item).toString());

            addRequest.setEntity(stringEntity);
            addRequest.setHeader("Accept", "application/json");

            HttpResponse response = httpClient.execute(addRequest);
            String status = response.getStatusLine().toString();
            Log.e(TAG, status);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void removeItem(Item item) {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpDelete deleteRequest = new HttpDelete(inventory.getResourceUrl() + item.getName());
            deleteRequest.setHeader("Accept", "application/json");

            HttpResponse response = httpClient.execute(deleteRequest);
            String status = response.getStatusLine().toString();
            Log.i(TAG, status);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
