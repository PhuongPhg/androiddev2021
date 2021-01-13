package vn.edu.usth.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

//        Fragment mainFragment = new ForecastFragment();
//        this.replaceFragment(mainFragment);
//
//        Fragment weatherFrag = new WeatherFragment();
//        this.replaceFragment(weatherFrag);
//        if(savedInstanceState == null){
//        ForecastFragment forecast = new ForecastFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container, forecast).commit();}

//    public void replaceFragment(Fragment finalFragment){
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.container, finalFragment);
//        fragmentTransaction.commit();
//    }

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        addTabs(viewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mediaPlayer = MediaPlayer.create(this, R.raw.ashitanohikari);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
//        new task().execute();
    }

    private void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WeatherAndForecastFragment(), "Hanoi");
        adapter.addFragment(new WeatherAndForecastFragment(), "Ho Chi Minh");
        adapter.addFragment(new WeatherAndForecastFragment(), "Da Nang");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            Bundle argsBundle = new Bundle();
            argsBundle.putString("data", title);
            fragment.setArguments(argsBundle);
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position){
            return fragmentTitleList.get(position);
        }
    }

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
                new task().execute();
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
    };
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
//        final Handler handler = new Handler(Looper.getMainLooper()){
//            @Override
//            public void handleMessage (Message msg){
//                String content = msg.getData().getString("server_response");
//                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
//                Intent intent = getIntent();
//                finish();
//                mediaPlayer.stop();
//                startActivity(intent);
//            }
//        };
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                Bundle bundle = new Bundle();
//                bundle.putString("server_response", "Refresh now!");
//
//                Message msg = new Message();
//                msg.setData(bundle);
//                handler.sendMessage(msg);
//            }
//        });
//        t.start();


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