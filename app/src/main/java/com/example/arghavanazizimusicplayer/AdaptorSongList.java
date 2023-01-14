package com.example.arghavanazizimusicplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arghavanazizimusicplayer.model.SongModelList;

import java.util.List;

public class AdaptorSongList extends RecyclerView.Adapter<AdaptorSongList.MyHolder>
{
    Context context ;
    List<SongModelList> songModels;


    public AdaptorSongList(Context context, List<SongModelList> songModels ) {
        this.context = context;
        this.songModels = songModels;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position)
    {
        SongModelList positions = songModels.get(position);
      holder.txtName_Song.setText(positions.getTitle());
      holder.txtName_Artist.setText(positions.getArtists().get(0).getFullName());

        Glide.with(context).load(positions.getImage().getCover().getUrl()).into(holder.imgSong);

        holder.txtName_Song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context,positions.getId(),Toast.LENGTH_LONG).show();
                Intent intent =new Intent(context, DetailSongActivity.class);
                intent.putExtra("songId",positions.getId());

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return songModels.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {

        ImageView imgSong;
        TextView txtName_Artist,txtName_Song;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            txtName_Song = itemView.findViewById(R.id.txtName_Song);
            txtName_Artist = itemView.findViewById(R.id.txtName_Artist);
            imgSong = itemView.findViewById(R.id.imgSong);
        }
    }


}
