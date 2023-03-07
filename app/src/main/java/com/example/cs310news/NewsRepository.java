package com.example.cs310news;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;



public class NewsRepository {

    /*public void getAllNewsCategories(ExecutorService srv, Handler uiHandler) {

        srv.execute(() -> {
            try {
                URL url = new URL("");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<NewsCategories> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    NewsCategories newsCategories = new NewsCategories(current.getInt("serviceMessageCode"),
                            current.getString("serviceMessageText"),
                            current.getJSONObject("items").getInt("id"), current.getJSONObject("items").getString("name"));
                    data.add(newsCategories);

                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }*/


    public void getAllNews(ExecutorService srv, Handler uiHandler) {

        srv.execute(() -> {
            try {
                URL url = new URL("");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                JSONObject root = new JSONObject(buffer.toString());
                JSONArray arr = root.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++){
                    JSONObject current = arr.getJSONObject(i);

                    News news = new News(current.getInt("id"), current.getString("title"), current.getString("text"),
                            current.getString("date"), current.getString("image"), current.getString("categoryName"));

                    data.add(news);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }

    public void getNewsByCategoryId(ExecutorService srv, Handler uiHandler, int id) {

        //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 00 ");
        srv.execute(() -> {
            //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 11");
            try {
                URL url = new URL(""+ String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 1 ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 1.5 ");
                StringBuilder buffer = new StringBuilder();
                String line = "";
                //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 2 ");
                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                JSONObject root = new JSONObject(buffer.toString());
                JSONArray arr = root.getJSONArray("items");
                Log.d("BAK", "getNewsByCategoryId: " + arr);
                List<News> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++){
                    JSONObject current = arr.getJSONObject(i);

                    News news = new News(current.getInt("id"), current.getString("title"), current.getString("text"),
                            current.getString("date"), current.getString("image"), current.getString("categoryName"));

                    data.add(news);
                }

                //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 4 ");
                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 5 ");

        });
        //Log.d("BARS", "getNewsByCategoryId: CHECKPOINT 555");

    }


    public void getNewsById(ExecutorService srv, Handler uiHandler, int id) {
        //Log.d("BARS", "getNewsById: BAS");
        srv.execute(() -> {
            try {
                URL url = new URL("" + String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }
                //Log.d("BARS", "getNewsById: CHECKPOINT 1");


                JSONObject root = new JSONObject(buffer.toString());
                JSONArray arr = root.getJSONArray("items");
                JSONObject current = arr.getJSONObject(0);

                News news = new News(current.getInt("id"), current.getString("title"), current.getString("text"),
                        current.getString("date"), current.getString("image"), current.getString("categoryName"));

               // Log.d("BARS", "getNewsById: CHECKPOINT 2");
                Message msg = new Message();
                msg.obj = news;
                uiHandler.sendMessage(msg);
                //Log.d("BARS", "getNewsById: CHECKPOINT 3");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }


    public void getCommentsByNewsId(ExecutorService srv, Handler uiHandler, int id) {
        //Log.d("BARS", "getNewsById: BAS");
        srv.execute(() -> {
            try {
                URL url = new URL("" + String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }
                //Log.d("BARS", "getNewsById: CHECKPOINT 1");

                JSONObject root = new JSONObject(buffer.toString());
                JSONArray arr = root.getJSONArray("items");
                List<Comments> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++){
                    JSONObject current = arr.getJSONObject(i);

                    Comments comments = new Comments(current.getInt("id"), current.getInt("news_id"), current.getString("text")
                            ,current.getString("name"));

                    data.add(comments);
                }

                // Log.d("BARS", "getNewsById: CHECKPOINT 2");
                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
                //Log.d("BARS", "getNewsById: CHECKPOINT 3");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });


    }


    public void postComment (ExecutorService srv, Handler uiHandler, String name, String text, String news_id){

        srv.execute(()->{

            try {
                URL url = new URL("");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                JSONObject outputData  = new JSONObject();

                outputData.put("name",name);
                outputData.put("text",text);
                outputData.put("news_id",news_id);


                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject retVal = new JSONObject(buffer.toString());

                    /*
                    PersonData data = new PersonData(retVal.getString("date"),
                            retVal.getString("fullname"));
                    */
                conn.disconnect();


                String retValStr = "Name:" + retVal.getString("date") +
                        ", fullname:" + retVal.getString("fullname");

                Message msg = new Message();
                msg.obj = retValStr;

                uiHandler.sendMessage(msg);





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });



    }



    public void downloadImage(ExecutorService srv, Handler uiHandler,String path){
        srv.execute(()->{
            try {
                //Log.d("BARS", "downloadImage: IMAGE DOWNLOAD STARTING");
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}

