package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

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

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ashitanohikari);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
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
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position){
            return fragmentTitleList.get(position);
        }
    }


    @Override
    protected  void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart() call", Toast.LENGTH_LONG).show();
        // initiate the Toast with context, message and duration for the Toast
    }
    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume() call", Toast.LENGTH_LONG).show();
        Log.i("WeatherActivity", "What is this? ");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause() call", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop() call", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy() call", Toast.LENGTH_LONG).show();
    }
}