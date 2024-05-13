package com.example.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.content.Context;

import java.util.ArrayList;

public class CustomAcceptedAdapter extends ArrayAdapter<String> {
    private Context mContext;

    public CustomAcceptedAdapter(Context context, ArrayList<String> list) {
        super(context, 0, list);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_accepted, parent, false);
        }
        // Get the lawyer's ID from the data list
        String listItem = getItem(position);

        // Extract the lawyer's ID from the listItem
        String lawyerId = listItem.substring(listItem.lastIndexOf(":") + 2);

        // Set the ID to the TextView in the custom layout
        TextView lawyerIdTextView = convertView.findViewById(R.id.phone);
        lawyerIdTextView.setText("ID: " + lawyerId);

        return convertView;
    }
}
