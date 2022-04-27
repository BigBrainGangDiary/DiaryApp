package com.bigbraingang.diaryapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuoteManager {
    private String quote;

    public QuoteManager(String quote){
        try{
            JSONArray jsonArray = new JSONArray(quote);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String q = jsonObject.getString("q");
            setQuote(q);

            Log.i ("quote", this.quote);

        } catch (JSONException e) {
            Log.i ("infoE", quote);
        }
    }

    public String getQuote() {
        return this.quote;
    }

    public void setQuote(String q){
        this.quote = q;
    }
}
