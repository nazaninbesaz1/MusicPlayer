package com.example.arghavanazizimusicplayer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.arghavanazizimusicplayer.model.DetailSongModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetailSongActivity extends AppCompatActivity {

    ImageView imgSong ,imgPlay;
    TextView txtNameSong,txtNameArtist,txtDate,txtNumberPlay,textSong;
     TextView tvCurrentTime, tvTotalTime;
    SeekBar sekBar;

    ObjectMapper mapper;
    DetailSongModel detailSongModel;

    String SongId;
    MediaPlayer mPlayer;
    String play = "play";
    boolean stateMpcreate = false;

    Uri pathAudio;
    Runnable runnable;
    Handler handler;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPlay = findViewById(R.id.imgPlay);
        imgSong = findViewById(R.id.imgSong);
        txtNameSong = findViewById(R.id.txtNameSong);
        txtNameArtist = findViewById(R.id.txtNameArtist);
        tvCurrentTime = findViewById(R.id.tv_current_time);
        tvTotalTime = findViewById(R.id.tv_total_time);
        txtDate = findViewById(R.id.txtDate);
        txtNumberPlay = findViewById(R.id.txtNumberPlay);
        textSong = findViewById(R.id.textSong);
        sekBar = findViewById(R.id.sekBar);
        handler = new Handler();
        playCycle();



        Intent intent = getIntent();
        SongId = intent.getStringExtra("songId");
        requestSong();
        showAlert();

        imgPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                if(play.equals("play"))
                {
                    mPlayer.start();
                    sekBar.setMax(mPlayer.getDuration());
                    playCycle();
                    imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                    tvTotalTime.setText(getTimeFormatted(mPlayer.getDuration()));
                    play = "pause";
                    stateMpcreate = true;
                }
                else if(play.equals("pause"))
                {
                    mPlayer.pause();
                    imgPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    play = "play";
                }
            }
        });


        sekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mPlayer.seekTo(progress);
                    tvCurrentTime.setText(getTimeFormatted(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    // method get data
    public  void requestSong()
    {
        StringRequest stringRequest = new StringRequest( "https://api-beta.melobit.com/v1/song/"+SongId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                 //Toast.makeText(DetailActivity.this,response,Toast.LENGTH_LONG).show();
                 showJson(response);
                alertDialog.dismiss();
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(DetailSongActivity.this,"اتصال شما با سرور برقرار نشد",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // method show data
    public void showJson(String detail)
    {
        String response = detail;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try
        {
            detailSongModel = mapper.readValue(response, new TypeReference<DetailSongModel>() {});

            txtNameSong.setText(detailSongModel.getTitle());
            txtNameArtist.setText((detailSongModel.getArtists().get(0).getFullName()));
            txtNumberPlay.setText(detailSongModel.getDownloadCount());

            textSong.setText(detailSongModel.getLyrics());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //txtDate.setText(" تاریخ انتشار : " + String.format(detailSongModel.getReleaseDate(), df));
            txtDate.setText(String.format(detailSongModel.getReleaseDate(),df));
            Glide.with(this).load(detailSongModel.getImage().getCover().getUrl()).into(imgSong);
             pathAudio = Uri.parse(detailSongModel.getAudio().getMedium().getUrl());

            Toast.makeText(DetailSongActivity.this,"loding music please wait .... ",Toast.LENGTH_SHORT).show();
            mPlayer=MediaPlayer.create(DetailSongActivity.this,pathAudio);


        }
        catch (JsonProcessingException e)
        {

        }

    }

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


    public void playCycle() {
        try {
            sekBar.setProgress(mPlayer.getCurrentPosition());
            tvCurrentTime.setText(getTimeFormatted(mPlayer.getCurrentPosition()));
            if (mPlayer.isPlaying()) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        playCycle();

                    }
                };
                handler.postDelayed(runnable, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method format time music
    private String getTimeFormatted(long milliSeconds) {
        String finalTimerString = "";
        String secondsString;

        //Converting total duration into time
        int hours = (int) (milliSeconds / 3600000);
        int minutes = (int) (milliSeconds % 3600000) / 60000;
        int seconds = (int) ((milliSeconds % 3600000) % 60000 / 1000);

        // Adding hours if any
        if (hours > 0)
            finalTimerString = hours + ":";

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10)
            secondsString = "0" + seconds;
        else
            secondsString = "" + seconds;

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // Return timer String;
        return finalTimerString;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(stateMpcreate) {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
        }

    }
}