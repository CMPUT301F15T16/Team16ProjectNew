package com.loveboyuan.smarttrader;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.loveboyuan.smarttrader.elastic_search.SearchHit;
import com.loveboyuan.smarttrader.elastic_search.SearchResponse;
import com.loveboyuan.smarttrader.elastic_search.SimpleSearchCommand;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brenn_000 on 11/29/2015.
 */
public class PendingManager {
    static User usr=LoginActivity.usr;


    private static final String TAGS = "PendingSent";
    static final String TAGR = "SearchSent";
    private Gson gson;
    private Pending pending = new Pending();


    public PendingManager(String search) {
        gson = new Gson();
    }


    public Pending searchPending(String searchString, String field) {
        Pending result = new Pending();
        User usr=LoginActivity.usr;


        String searchURL =Pending.getSearchUrl();
        HttpPost searchRequest = new HttpPost(searchURL);

        String[] fields = null;
        if (field != null) {
            throw new UnsupportedOperationException("Not implemented!");
        }

        SimpleSearchCommand command = new SimpleSearchCommand(searchString);

        String query = gson.toJson(command);
        Log.i(TAGS, "Json command: " + searchURL);

        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(query);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        searchRequest.setHeader("Accept", "application/json");
        searchRequest.setEntity(stringEntity);

        HttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = null;
        try {
            response = httpClient.execute(searchRequest);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         * Parses the response of a search
         */
        Type searchResponseType = new TypeToken<SearchResponse<Pending>>() {
        }.getType();

        SearchResponse<Pending> esResponse;
        try {
            esResponse = gson.fromJson(
                    new InputStreamReader(response.getEntity().getContent()),
                    searchResponseType);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for(SearchHit<Pending> hit : esResponse.getHits().getHits()){


            if (hit.getSource().getPendingSentId() == usr.getMy_id()){
                ArrayList<User> users = hit.getSource().getPendingSent();
                for(User user : users) {
                    result.addPendingSent(user);
                }
            }
            else if (hit.getSource().getPendingReceivedId() == usr.getMy_id()) {
                ArrayList<User> users = hit.getSource().getPendingReceived();
                for(User user : users) {
                    result.addPendingReceived(user);
                }
            }


        }


        return result;
    }

    public Pending searchOwnSent(String searchString, String field) {
        Pending result = new Pending();
        User usr=LoginActivity.usr;


        String searchURL = Pending.getSearchUrl();
        HttpPost searchRequest = new HttpPost(searchURL);

        String[] fields = null;
        if (field != null) {
            throw new UnsupportedOperationException("Not implemented!");
        }

        SimpleSearchCommand command = new SimpleSearchCommand(searchString);

        String query = gson.toJson(command);
        Log.i(TAGR, "Json command: " + searchURL);

        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(query);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        searchRequest.setHeader("Accept", "application/json");
        searchRequest.setEntity(stringEntity);

        HttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = null;
        try {
            response = httpClient.execute(searchRequest);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         * Parses the response of a search
         */
        Type searchResponseType = new TypeToken<SearchResponse<Pending>>() {
        }.getType();

        SearchResponse<Pending> esResponse;
        try {
            esResponse = gson.fromJson(
                    new InputStreamReader(response.getEntity().getContent()),
                    searchResponseType);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Extract the movies from the esResponse and put them in result


        for(SearchHit<Pending> hit : esResponse.getHits().getHits()){


            if(hit.getSource().getPendingSentId() == usr.getMy_id()){

                result = hit.getSource();
                return result;
            }
        }

        return result;
    }

}
