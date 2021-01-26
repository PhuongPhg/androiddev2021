package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    List<String> list;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        viewPager2 = (ViewPager2)findViewById(R.id.pager);
        list = new ArrayList<>();
        list.add("Ha Noi");
        list.add("Nha Trang");
        list.add("Ho Chi Minh");
        viewPager2.setAdapter(new ViewPagerAdapter(this, list, viewPager2));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Ha Noi"));
        tabLayout.addTab(tabLayout.newTab().setText("Nha Trang"));
        tabLayout.addTab(tabLayout.newTab().setText("Ho Chi Minh"));

        new TabLayoutMediator(tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String[] titles = new String[]{"Ha Noi", "Nha Trang", "Ho Chi Minh"};
                tab.setText(titles[position]);
                viewPager2.setCurrentItem(position);
            }
        }).attach();

        mediaPlayer = MediaPlayer.create(this, R.raw.ashitanohikari);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
//        new task().execute();
    }


//        public void addFragment(Fragment fragment, String title){
//            Bundle argsBundle = new Bundle();
//            argsBundle.putString("data", title);
//            fragment.setArguments(argsBundle);
//            fragmentList.add(fragment);
//            fragmentTitleList.add(title);
//        }

    //for inflating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return true;
    }

//    response to actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.refresh:
//                new task().execute();
//                volleyFunc();
                weatherFunc();
//                Intent intent = getIntent();
//                finish();
//                mediaPlayer.stop();
//                startActivity(intent);
                return true;
            case R.id.setting:
                super.onBackPressed();
                mediaPlayer.stop();
                startActivity(new Intent(this, PrefActivity.class));
            default:
                super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void weatherFunc() {
        RequestQueue queue = Volley.newRequestQueue(this);
        TextView checkTextCity = (TextView)findViewById(R.id.plsShow);
        String url="http://api.weatherapi.com/v1/forecast.json?key=369cde99aed5437d88a22739211301&q="+checkTextCity.getText()+"&days=7";

        Response.Listener<String> listener =
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONArray current = obj.getJSONArray("current");
                        Log.d("API response", String.valueOf(current));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response", "Error!");
            }
        });
        queue.add(stringRequest);
    }

    private void volleyFunc() {
        RequestQueue queue = Volley.newRequestQueue(this);

        Response.Listener<Bitmap> listener =
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView iv = (ImageView) findViewById(R.id.logo);
                        iv.setImageBitmap(response);
                    }
                };
        ImageRequest imagerequest = new ImageRequest(
                "https://usth.edu.vn/uploads/chuong-trinh/2017_01/logo-moi_2.png",
                listener, 0, 0, ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888, null
        );
        queue.add(imagerequest);
    }

    private class task extends AsyncTask<URL, Void, Bitmap>{
    Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(URL... urls) {

            try {
                URL url = new URL("https://usth.edu.vn/uploads/chuong-trinh/2017_01/logo-moi_2.png");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int response = connection.getResponseCode();
                Log.i("USTH Weather", "The response is: "+response);

                InputStream is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);

                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
//            Toast.makeText(getApplicationContext(), "Refresh now!", Toast.LENGTH_SHORT).show();

            ImageView logo = (ImageView) findViewById(R.id.logo);
            logo.setImageBitmap(bitmap);
        }
    }


    @Override
    protected  void onStart(){
        super.onStart();
//        Toast.makeText(getApplicationContext(), "onStart() call", Toast.LENGTH_LONG).show();
        // initiate the Toast with context, message and duration for the Toast
    }
    @Override
    protected void onResume(){
        super.onResume();
//        Toast.makeText(getApplicationContext(), "onResume() call", Toast.LENGTH_LONG).show();
        Log.i("WeatherActivity", "What is this? ");

    }
    @Override
    protected void onPause(){
        super.onPause();
//        Toast.makeText(getApplicationContext(), "onPause() call", Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
    }
    @Override
    protected void onStop(){
        super.onStop();
//        Toast.makeText(getApplicationContext(), "onStop() call", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "onDestroy() call", Toast.LENGTH_LONG).show();
    }
}