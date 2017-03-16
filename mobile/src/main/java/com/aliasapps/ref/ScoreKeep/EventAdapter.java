package com.aliasapps.ref.ScoreKeep;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliasapps.ref.R;

import java.util.ArrayList;

/**
 * Created by mike on 2017-03-14.
 */

class EventAdapter extends BaseAdapter {

    ArrayList<Event> events;

    EventAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int i) {
        return events.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = events.get(position);

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    parent.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.event_cell, parent, false);
        }

        TextView leftDescription, leftTime, rightDescription, rightTime;
        ImageView leftIcon, rightIcon;

        leftDescription = (TextView) convertView.findViewById(R.id.event_text_left);
        leftIcon = (ImageView) convertView.findViewById(R.id.event_cell_action_left);
        leftTime = (TextView) convertView.findViewById(R.id.event_time_left);

        rightDescription = (TextView) convertView.findViewById(R.id.event_text_right);
        rightIcon = (ImageView) convertView.findViewById(R.id.event_cell_action_right);
        rightTime = (TextView) convertView.findViewById(R.id.event_time_right);

        if(event.getHomeTeam() == 1) {
            leftDescription.setText(event.getDescription());
            leftTime.setText(event.getTime());
            leftIcon.setImageResource(EventENUM.getEventResource(event));

            leftIcon.setVisibility(View.VISIBLE);
            leftTime.setVisibility(View.VISIBLE);
            leftDescription.setVisibility(View.VISIBLE);

            rightDescription.setVisibility(View.INVISIBLE);
            rightTime.setVisibility(View.INVISIBLE);
            rightIcon.setVisibility(View.INVISIBLE);
        }
        else{
            rightDescription.setText(event.getDescription());
            rightTime.setText(event.getTime());
            rightIcon.setImageResource(EventENUM.getEventResource(event));

            rightIcon.setVisibility(View.VISIBLE);
            rightTime.setVisibility(View.VISIBLE);
            rightDescription.setVisibility(View.VISIBLE);

            leftDescription.setVisibility(View.INVISIBLE);
            leftTime.setVisibility(View.INVISIBLE);
            leftIcon.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}

