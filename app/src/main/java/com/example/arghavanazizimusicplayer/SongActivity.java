package com.example.arghavanazizimusicplayer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arghavanazizimusicplayer.model.ArtistModel;
import com.example.arghavanazizimusicplayer.model.SongModelList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongActivity extends AppCompatActivity {

    TextView txtxTitel;
    String key;
    String flag="song";
    RecyclerView rcSong;
    String path;
    AlertDialog alertDialog;


    List<SongModelList> songModelList;
    List<ArtistModel> artistModels;

    AdaptorSongList adaptorSongList;
    AdaptorArtistList adaptorArtistList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        txtxTitel = findViewById(R.id.txtTitel);
        rcSong = findViewById(R.id.rcSong);
        songModelList = new ArrayList<>();
        artistModels = new ArrayList<>();


        // get key
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        txtxTitel.setText(key);


        try
        {
            getSongPath();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        requestSong();
        showAlert();
        setAdapter();

    }

    //method send request server
    public  void requestSong()
    {
        StringRequest stringRequest = new StringRequest( path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
              // Toast.makeText(SongActivity.this,response,Toast.LENGTH_LONG).show();
               showJsonSong(response);
                alertDialog.dismiss();
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(SongActivity.this,"اتصال شما با سرور برقرار نشد",Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // method Show data Song
    public void showJsonSong(String song)
    {
        Gson gson =new Gson();
        JsonObject jsonObject = gson.fromJson(song,JsonObject.class);

        JsonArray jsonArrayView = jsonObject.getAsJsonArray("results");
        if(flag.equals("Artist"))
        {
            ArtistModel[] modelsArtist = gson.fromJson(jsonArrayView,ArtistModel[].class);
            artistModels.addAll(Arrays.asList(modelsArtist));
            adaptorArtistList.notifyDataSetChanged();
        }
        else if(flag.equals("Song"))
        {
            SongModelList[] modelSong = gson.fromJson(jsonArrayView, SongModelList[].class);
            songModelList.addAll(Arrays.asList(modelSong));
            adaptorSongList.notifyDataSetChanged();
        }


    }

    // method set Adapter
    public  void setAdapter()
    {
        if(flag.equals("Song"))
        {
            adaptorSongList = new AdaptorSongList(this, songModelList);

            rcSong.setAdapter(adaptorSongList);
            rcSong.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rcSong.setHasFixedSize(true);
        }
        else if(flag.equals("Artist"))
        {
            adaptorArtistList = new AdaptorArtistList(this,artistModels);
            rcSong.setAdapter(adaptorArtistList);
            rcSong.setLayoutManager(new GridLayoutManager(this,2));
            rcSong.setHasFixedSize(true);
        }


    }

    // method get json data from jsonFile
    void getSongPath() throws IOException
    {
        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray jsonItem = obj.getJSONArray("item");

            for (int i = 0; i < jsonItem.length(); i++)
            {
                JSONObject jsonObjectSong = jsonItem.getJSONObject(i);

               if(jsonObjectSong.getString("name").equals(key))
               {
                   JSONObject jsonRequest = new JSONObject(jsonObjectSong.getString("request"));
                   JSONObject jsonUrl = new JSONObject(jsonRequest.getString("url"));
                   path = jsonUrl.getString("raw");
                   txtxTitel.setText(key);
                   if (key.equals("Trending Artist"))
                   {
                       flag = "Artist";
                   }
                   else
                   {
                       flag = "Song";
                   }

               }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //method load json file from asset
    public String loadJSONFromAsset()
    {
        String json = null;
        try {
            InputStream is = getAssets().open("Melobit.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // method ProgressDialog
    public void showAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.alert_home,null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }
}

