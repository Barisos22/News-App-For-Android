package com.example.cs310news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;

public class MakeComment extends AppCompatActivity {

    EditText name;
    EditText comment;
    Button btn;
    TextView error;
    ProgressBar prg;

    Handler commentHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String retVal = "1234";


            //prgBar.setVisibility(View.INVISIBLE);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_comment);


        name = findViewById(R.id.name);
        comment = findViewById(R.id.comment);
        btn = (Button) findViewById(R.id.button);
        prg = findViewById(R.id.pb);
        error = findViewById(R.id.error);
        error.setVisibility(View.INVISIBLE);
        int id = getIntent().getIntExtra("news id", 1);
        String ids = Integer.toString(id);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!name.getText().toString().isEmpty() && !comment.getText().toString().isEmpty()) {
                    prg.setVisibility(View.VISIBLE);
                    ExecutorService srv = ((NewsApp) getApplication()).srv;
                    NewsRepository repo = new NewsRepository();
                    repo.postComment(srv, commentHandler, name.getText().toString(), comment.getText().toString(), ids);


                    Intent i = new Intent(MakeComment.this, CommentsPage.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    prg.setVisibility(View.INVISIBLE);
                    startActivity(i);
                }
                else{
                    error.setVisibility(View.VISIBLE);
                }


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("Post Comment");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.makecomment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return true;
    }


}