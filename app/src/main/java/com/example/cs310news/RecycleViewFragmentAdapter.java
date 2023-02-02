package com.example.cs310news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class RecycleViewFragmentAdapter extends RecyclerView.Adapter<RecycleViewFragmentAdapter.NewsViewHolder> {

    Context ctx;
    List<News> data;

    public RecycleViewFragmentAdapter(Context ctx, List<News> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(ctx).inflate(R.layout.row_layout,parent,false);
        NewsViewHolder viewHolder = new NewsViewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News current = data.get(position);
        //holder.img.setImageResource(current.getImg());
        Log.d("BAK", "onBindViewHolder: CURRENT: " + current.getImg());
        holder.downloadImage(((NewsApp) ((AppCompatActivity)ctx).getApplication()).srv,data.get(holder.getAdapterPosition()).getImg());
        holder.date.setText(current.getDate());
        holder.title.setText(current.getTitle());
        Log.d("BAK", "onBindViewHolder HOLDER: " + holder.title.getText());

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, NewsDetails.class);
                i.putExtra("news id", data.get(holder.getAdapterPosition()).getId());
                i.putExtra("category name", data.get(holder.getAdapterPosition()).getCategoryName());
                //Log.d("BARS", "onClick ID SENDER: " + data.get(holder.getAdapterPosition()).getId());
                ((AppCompatActivity)ctx).startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title, date;
        ImageView img;
        ConstraintLayout row;
        //boolean imageDownloaded;

        Handler imgHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                Bitmap imgb = (Bitmap)msg.obj;
                Log.d("BAK", "BITMAP: " + imgb);
                //imageDownloaded = true;
                img.setImageBitmap(imgb);
                return true;
            }
        });

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_row);

            img = itemView.findViewById(R.id.img_row);
            date = itemView.findViewById(R.id.date_row);
            row = itemView.findViewById(R.id.row);
        }

        public void downloadImage(ExecutorService srv, String path){
            //Log.d("BAL", "downloadImage: " + path + " --- "+ imageDownloaded);
            //if (!imageDownloaded){

                NewsRepository repo = new NewsRepository();
                repo.downloadImage(srv,imgHandler,path);


            //}



        }
    }



}
