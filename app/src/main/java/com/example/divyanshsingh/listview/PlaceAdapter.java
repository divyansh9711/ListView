package com.example.divyanshsingh.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Divyansh Singh on 31-10-2017.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {
    Context mContext;
    int mLayoutResId;
    Place mData[] = null;

    public PlaceAdapter(Context context,int resource, Place[] data){
        super(context, resource, data);
        this.mContext = context;
        this.mData = data;
        this.mLayoutResId = resource;
    }

    @Override
    public Place getItem(int position) {
        return super.getItem(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        PlaceHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResId, parent, false);

            holder = new PlaceHolder();

            holder.nameView = (TextView) row.findViewById(R.id.nameTextView);
            holder.zipView = (TextView) row.findViewById(R.id.zipcodeTextView);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(holder);
        }
        else{
            holder = (PlaceHolder) row.getTag();
        }
        Place place = mData[position];

        holder.imageView.setOnClickListener(PopupListener);
        Integer rowPosition = position;
        holder.imageView.setTag(rowPosition);

        holder.nameView.setText(place.mNameOfPlace);
        holder.zipView.setText(String.valueOf(place.mZipCode));

        int resId = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);

        return row;
    }

    View.OnClickListener PopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Integer viewPostion = (Integer) view.getTag();
            Place p = mData[viewPostion];
            Toast.makeText(getContext(),p.mPopup,Toast.LENGTH_SHORT).show();
        }
    };
    private static class PlaceHolder{
        TextView nameView;
        TextView zipView;
        ImageView imageView;
    }
}


