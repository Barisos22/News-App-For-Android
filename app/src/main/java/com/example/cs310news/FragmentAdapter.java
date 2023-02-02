package com.example.cs310news;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cs310news.Fragments.Fragment_E;
import com.example.cs310news.Fragments.Fragment_P;
import com.example.cs310news.Fragments.Fragment_S;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new Fragment_E();
            case 1:
                return new Fragment_S();
            case 2:
                return new Fragment_P();
            default:
                return new Fragment_E();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
