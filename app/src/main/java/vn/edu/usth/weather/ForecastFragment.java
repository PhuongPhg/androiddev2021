package vn.edu.usth.weather;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForecastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForecastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForecastFragment newInstance(String param1, String param2) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_forecast, container, false);
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        view.setBackgroundColor(getResources().getColor(R.color.colorBack));

        LinearLayout llmain = (LinearLayout) view.findViewById(R.id.linearParent);
        String[] idList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        int[] iconSet = {R.drawable.sun, R.drawable.windy, R.drawable.cloud, R.drawable.rain};
        for(int i =0 ; i<idList.length; i++) {
            LinearLayout ll = new LinearLayout(this.getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    200
            );
            param.setMargins(20, 20, 20, 20);
            ll.setLayoutParams(param);

            TextView txt = new TextView(this.getContext());
            txt.setText(idList[i]);
            LinearLayout.LayoutParams paramDay = new LinearLayout.LayoutParams(
                    0, LayoutParams.MATCH_PARENT, 1.0f
            );
            txt.setTextSize(20);
            txt.setGravity(Gravity.CENTER);
            txt.setLayoutParams(paramDay);
            ll.addView(txt);

            ImageView imgWeather = new ImageView(this.getContext());
            LinearLayout.LayoutParams paramImg = new LinearLayout.LayoutParams(
                    0, LayoutParams.WRAP_CONTENT, 1.0f
            );
            Random random = new Random(System.currentTimeMillis());
            imgWeather.setImageResource(iconSet[random.nextInt(iconSet.length-1)]);
            imgWeather.setLayoutParams(paramImg);
            imgWeather.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ll.addView(imgWeather);

            TextView place = new TextView(this.getContext());
            place.setText("Hanoi City\n30°C");
            place.setTextSize(20);
            place.setGravity(Gravity.LEFT);
            LinearLayout.LayoutParams paramPlace = new LinearLayout.LayoutParams(
                    0, LayoutParams.WRAP_CONTENT, 1.0f
            );
            place.setLayoutParams(paramPlace);
            ll.addView(place);

            llmain.addView(ll);
        }
        return view;
    }
}