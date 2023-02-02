package com.example.cs310news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class NewsDetails extends AppCompatActivity {

    ImageView img;
    TextView title;
    TextView date;
    TextView text;


    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            News news = (News) msg.obj;

            title.setText(news.getTitle());
            date.setText(news.getDate());
            text.setText(news.getText());


            NewsRepository repo = new NewsRepository();
            repo.downloadImage(((NewsApp)getApplication()).srv,imgHandler,news.getImg());

            return true;
        }
    });


    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap imgb = (Bitmap) msg.obj;
            img.setImageBitmap(imgb);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        int id = getIntent().getIntExtra("news id", 1);
        //Log.d("BARS", "onCreate ID: " + id);

        img = findViewById(R.id.image_details);
        title = findViewById(R.id.title_details);
        date = findViewById(R.id.date_details);
        text = findViewById(R.id.text_details);








        NewsRepository repo = new NewsRepository();
        repo.getNewsById(((NewsApp)getApplication()).srv, dataHandler, id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        String category = getIntent().getStringExtra("category name");
        Log.d("category name", "onCreateOptionsMenu: " + category);
        switch(category){
            case "Economics":
                getSupportActionBar().setTitle("Economics");
                break;
            case "Sports":
                getSupportActionBar().setTitle("Sports");
                break;
            case "Politics":
                getSupportActionBar().setTitle("Politics");
                break;
            default:
                getSupportActionBar().setTitle("Economics");
                break;
        }

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newsdetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        else if(item.getItemId()==R.id.show_comment){
            Intent i = new Intent(NewsDetails.this, CommentsPage.class);

            i.putExtra("news id", getIntent().getIntExtra("news id", 1));
            startActivity(i);
        }
        

        return true;
    }
}