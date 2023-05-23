package com.example.ordercoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ordercoffee.Model.Feedback;
import com.example.ordercoffee.R;

import java.util.ArrayList;

public class FeedbackAdapter extends BaseAdapter {
    Context context;
    ArrayList<Feedback> list;

    public FeedbackAdapter(Context context, ArrayList<Feedback> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.feedback_item,null);

        TextView id=(TextView) row.findViewById(R.id.id);
        TextView noidung=(TextView) row.findViewById(R.id.noidung);

        final Feedback feedback=list.get(position);

        id.setText("ID: "+feedback.getId());
        noidung.setText("Nội dung: "+feedback.getNoidung());

        return  row;
    }
}
