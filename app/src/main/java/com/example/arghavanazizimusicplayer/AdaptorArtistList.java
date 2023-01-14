package com.example.arghavanazizimusicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arghavanazizimusicplayer.model.ArtistModel;

import java.util.List;

public class AdaptorArtistList extends RecyclerView.Adapter<AdaptorArtistList.MyHolder>
{
    Context context ;
    List<ArtistModel> artistModels;

    public AdaptorArtistList(Context context, List<ArtistModel> artistModels) {
        this.context = context;
        this.artistModels = artistModels;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.item_artist_list, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position)
    {
        ArtistModel positions = artistModels.get(position);

        Glide.with(context).load(positions.getImage().getThumbnail_small().getUrl()).into(holder.imgArtist);
        holder.txtNameArtist.setText(positions.getFullName());
        holder.txtsumSongsDownloads.setText(positions.getSumSongsDownloadsCount());

    }

    @Override
    public int getItemCount() {
        return artistModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView imgArtist;
        TextView txtNameArtist,txtsumSongsDownloads;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imgArtist = itemView.findViewById(R.id.imgArtist);
            txtNameArtist = itemView.findViewById(R.id.txtNameArtist);
            txtsumSongsDownloads = itemView.findViewById(R.id.txtsumSongsDownloads);

        }
    }
}
