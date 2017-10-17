package com.debut.ellipsis.freehit.IntoSlider;

import android.content.Context;
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
    List<Country> countries;
    LayoutInflater inflater;

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

        /*CustomImageSizeModel Flag = new CustomImageSizeModelFutureStudio(FlagURL);*/

        RequestBuilder requestBuilder = GlideApp.with(mContext).load(FlagURL).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

        requestBuilder.into(cell.imageView);

        /*GlideApp.with(mContext).load(Flag).apply(new RequestOptions().placeholder(R.drawable.matches)).into(cell.imageView);*/

        return view;
    }

    static class Cell {
        public TextView textView;
        public ImageView imageView;

        static Cell from(View view) {
            if (view == null)
                return null;

            if (view.getTag() == null) {
                Cell cell = new Cell();
                cell.textView = (TextView) view.findViewById(R.id.row_title);
                cell.imageView = (ImageView) view.findViewById(R.id.row_icon);
                view.setTag(cell);
                return cell;
            } else {
                return (Cell) view.getTag();
            }
        }
    }
}
