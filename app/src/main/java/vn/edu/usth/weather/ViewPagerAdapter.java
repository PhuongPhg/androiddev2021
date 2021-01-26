package vn.edu.usth.weather;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private static Bitmap mBitmap;
    private ViewPager2 mViewPager2;
    private LayoutInflater mInflater;
    private List<String> mData;

    public ViewPagerAdapter(WeatherActivity weatherActivity, List<String> list, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(weatherActivity);
        this.mData = list;
        this.mViewPager2 = viewPager2;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_weather_and_forecast, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String pos = mData.get(position);
        holder.iv.setImageBitmap(mBitmap);
        holder.myTextView.setText(pos);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTextView;
        FrameLayout frameLayout;
        ImageView iv;
        ViewHolder(View itemView){
            super(itemView);
            myTextView = itemView.findViewById(R.id.plsShow);
            frameLayout = itemView.findViewById(R.id.longFrameLayout);
            iv = itemView.findViewById(R.id.logo);
        }
    }
}
