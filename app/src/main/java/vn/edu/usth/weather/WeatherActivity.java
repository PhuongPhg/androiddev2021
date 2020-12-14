package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.nfc.Tag;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

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
    }


    public void replaceFragment(Fragment finalFragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, finalFragment);
        fragmentTransaction.commit();
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