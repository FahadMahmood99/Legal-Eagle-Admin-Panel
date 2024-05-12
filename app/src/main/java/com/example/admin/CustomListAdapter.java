package com.example.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import android.content.Context;


import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {
    private Context mContext;

    public CustomListAdapter(Context context, ArrayList<String> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the lawyer's name and ID from the data list
        String listItem = getItem(position);
        String[] parts = listItem.split("\\(ID: ");
        String lawyerName = parts[0];
        String lawyerId = parts[1].substring(0, parts[1].length() - 1);

        // Set the name and ID to the TextViews in the custom layout
        TextView lawyerNameTextView = convertView.findViewById(R.id.name);
        TextView lawyerIdTextView = convertView.findViewById(R.id.phone);
        lawyerNameTextView.setText(lawyerName);
        lawyerIdTextView.setText("ID: " + lawyerId);

        return convertView;
    }
}

