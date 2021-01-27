package vn.edu.usth.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ForecastData> {
    private List<ForecastData> forecastDataList;
    private Context mContext;

    public ListViewAdapter(List<ForecastData> forecastData, Context mContext) {
        super(mContext, R.layout.list_items, forecastData);
        this.mContext = mContext;
        this.forecastDataList = forecastData;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        ForecastData forecastData = forecastDataList.get(position);
        TextView txt = (TextView) listViewItem.findViewById(R.id.dayText);
        txt.setText(forecastData.getCondition());
        return listViewItem;
    }
}
