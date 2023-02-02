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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;
import java.util.concurrent.ExecutorService;

public class CommentRecycleViewAdapter extends RecyclerView.Adapter<CommentRecycleViewAdapter.CommentsViewHolder> {

    private Context ctx;
    private List<Comments> data;

    public CommentRecycleViewAdapter(Context ctx, List<Comments> data){
        this.ctx = ctx;
        this.data = data;
    }


    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(ctx).inflate(R.layout.commentrow,parent,false);

        CommentsViewHolder holder = new CommentsViewHolder(root);
        holder.setIsRecyclable(false);
        Log.d("COMS", "onBindViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {

        holder.name.setText(data.get(holder.getAdapterPosition()).getName());
        Log.d("COMS", "onBindViewHolder: " +  holder.name);
        holder.text.setText(data.get(holder.getAdapterPosition()).getText());
        NewsApp app = (NewsApp) ((AppCompatActivity)ctx).getApplication();

        /*holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,ActivityDetails.class);
                i.putExtra("id",data.get(holder.getAdapterPosition()).getId());
                ctx.startActivity(i);


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView text;
        ConstraintLayout row;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.comment_name);
            text = itemView.findViewById(R.id.comment_text);
            row = itemView.findViewById(R.id.commentrow);

        }



    }



}
