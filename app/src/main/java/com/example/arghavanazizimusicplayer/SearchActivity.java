package com.example.arghavanazizimusicplayer;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arghavanazizimusicplayer.model.SearchModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity
{

    EditText edSearch;

    RecyclerView recyclerViewSearch;

    List<SearchModel> searchModels;

    AdaptorSearchSong adaptorSongList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edSearch = findViewById(R.id.edSearch);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);

        searchModels = new ArrayList<>();


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                 new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(charSequence.toString().trim().equals(""))
                    {
                        recyclerViewSearch.setVisibility(View.GONE);
                    }
                    else
                    {
                        search(charSequence.toString().trim());
                    }
                }
            },1000);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setAdapter();

    }

    public  void search(String key)
    {

        StringRequest stringRequest = new StringRequest( "https://api-beta.melobit.com/v1/search/query/"+key.toString()+"/0/50" ,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
               // Toast.makeText(SearchActivity.this,response+"",Toast.LENGTH_SHORT).show();
                try
                {
                    showJsonSong(response);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SearchActivity.this,"اتصال شما با سرور برقرار نشد",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJsonSong(String song) throws JSONException
    {
        Gson gson =new Gson();
        JsonObject jsonObject = gson.fromJson(song,JsonObject.class);

        JsonArray jsonArrayView = jsonObject.getAsJsonArray("results");

            SearchModel[] searchModels1 = gson.fromJson(jsonArrayView,SearchModel[].class);
            searchModels.addAll(Arrays.asList(searchModels1));
            adaptorSongList.notifyDataSetChanged();



    }

    public  void setAdapter() {
        adaptorSongList = new AdaptorSearchSong(this, searchModels);

        recyclerViewSearch.setAdapter(adaptorSongList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewSearch.setHasFixedSize(true);
    }




}