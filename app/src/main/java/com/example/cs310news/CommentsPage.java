package com.example.cs310news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.List;


public class CommentsPage extends AppCompatActivity {

    //ProgressBar prg;
    RecyclerView recView;
    ProgressBar prg;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Comments> data = (List<Comments>)msg.obj;
            CommentRecycleViewAdapter adp = new CommentRecycleViewAdapter(CommentsPage.this,data);
            recView.setAdapter(adp);
            //recView.setVisibility(View.VISIBLE);
            //prg.setVisibility(View.INVISIBLE);
            prg.setVisibility(View.INVISIBLE);
            recView.setVisibility(View.VISIBLE);

            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_page);
        //prg = findViewById(R.id.progressBarList);
        recView = findViewById(R.id.recComments);
        recView.setLayoutManager(new LinearLayoutManager(this));
        prg = findViewById(R.id.progressBarList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //prg.setVisibility(View.VISIBLE);
       // recView.setVisibility(View.INVISIBLE);

        NewsRepository repo = new NewsRepository();
        int id = getIntent().getIntExtra("news id", 1);
        repo.getCommentsByNewsId(((NewsApp) getApplication()).srv, dataHandler, id);


    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("Comments");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        else if(item.getItemId()==R.id.makeComment){
            Intent i = new Intent(CommentsPage.this, MakeComment.class);

            i.putExtra("news id", getIntent().getIntExtra("news id", 1));
            startActivity(i);
        }


        return true;
    }


}