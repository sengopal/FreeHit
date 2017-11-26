package com.debut.ellipsis.freehit.IntoSlider;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.R;

import java.util.List;


public class CountryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Country> countries;
    private LayoutInflater inflater;

    public CountryListAdapter(Context context, List<Country> countries) {
        super();
        this.mContext = context;
        this.countries = countries;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Country country = countries.get(position);

        if (view == null)
            view = inflater.inflate(R.layout.country_picker_row, null);

        Cell cell = Cell.from(view);
        cell.textView.setText(country.getName());

        String FlagURL = country.getFlag();
        RequestBuilder requestBuilder;

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                cell.textView.setTextColor(Color.WHITE);
                requestBuilder = GlideApp.with(mContext).load(FlagURL).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                break;
            default:
                requestBuilder = GlideApp.with(mContext).load(FlagURL).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                break;
        }

        requestBuilder.into(cell.imageView);

        return view;
    }

    static class Cell {
        TextView textView;
        ImageView imageView;

        static Cell from(View view) {
            if (view == null)
                return null;

            if (view.getTag() == null) {
                Cell cell = new Cell();
                cell.textView = view.findViewById(R.id.row_title);
                cell.imageView = view.findViewById(R.id.row_icon);
                view.setTag(cell);
                return cell;
            } else {
                return (Cell) view.getTag();
            }
        }
    }
}
