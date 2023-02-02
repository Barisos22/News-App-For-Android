package com.example.cs310news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class News implements Serializable {
    int id;
    String title, text, date;
    String img;
    String categoryName;

    public News(int id, String title, String text, String date, String img, String categoryName) {
        this.id = id;
        this.title = title;
        this.text = text;
        String d = date.substring(8,10) + "/" + date.substring(5,7) + "/" + date.substring(0,4);
        this.date = d;
        this.img = img;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    /*public static List<News> getAllNews(){
        List<News> newsList = new ArrayList<>();

        News new1 = new News(1,"ELON MUSK", "aaaa", "2022-15-08", R.drawable.earth, "aaaa");
        News new2 = new News(1,"222222", "aaaa", "2018-10-02", R.drawable.jupiter, "aaaa");
        News new3 = new News(1,"333333", "aaaa", "2021-23-11", R.drawable.mars, "aaaa");
        News new4 = new News(1,"44444", "aaaa", "2020-17-06", R.drawable.mercury, "aaaa");
        News new5 = new News(1,"555555", "aaaa", "2019-09-03", R.drawable.neptune, "aaaa");

        newsList.add(new1);
        newsList.add(new2);
        newsList.add(new3);
        newsList.add(new4);
        newsList.add(new5);

        return newsList;

    }*/
}
