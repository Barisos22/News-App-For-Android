package com.example.cs310news.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cs310news.News;
import com.example.cs310news.NewsApp;
import com.example.cs310news.NewsRepository;
import com.example.cs310news.R;
import com.example.cs310news.RecycleViewFragmentAdapter;

import java.util.List;

public class Fragment_P extends Fragment {

    RecyclerView recyclerView;
    ProgressBar prg;

    Handler dataHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
           // Log.d("BARS", "handleMessage: HMMMM 1");
            List<News> data = (List<News>)msg.obj;
            RecycleViewFragmentAdapter adp = new RecycleViewFragmentAdapter(getContext(),data);

            recyclerView.setAdapter(adp);
            prg.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);


           // Log.d("BARS", "handleMessage: HMMMM 2");
            //recyclerView.setVisibility(View.VISIBLE);
            //prg.setVisibility(View.INVISIBLE);

            return true;
        }
    });




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__p, container, false);

        recyclerView = v.findViewById(R.id.recNews);
        prg = v.findViewById(R.id.progressBarList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        NewsRepository repo = new NewsRepository();
       // Log.d("BARS", "handleMessage: CHECKPOINT 0");
        repo.getNewsByCategoryId(((NewsApp) getActivity().getApplication()).srv, dataHandler,3);



        //RecycleViewFragmentAdapter adp = new RecycleViewFragmentAdapter(getContext(), newsList);
        //recyclerView.setAdapter(adp);

        return v;

    }
}
