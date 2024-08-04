package com.example.mymapview;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class GetLocationFragment extends Fragment {


    EditText fromLocation, toLocation;
    AppCompatButton getDirection;
    Context context;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_get_location, container, false);

        fromLocation = view.findViewById(R.id.etFromLocation);
        toLocation = view.findViewById(R.id.etToLocation);
        getDirection = view.findViewById(R.id.btnGetDirection);

        getDirection.setOnClickListener(v -> {
            String userLocation = fromLocation.getText().toString();
            String userDestination = toLocation.getText().toString();

            if (userLocation.equals("") || userDestination.equals("")){
                Toast.makeText(context, "Please enter your location & destination", Toast.LENGTH_SHORT).show();
            } else {
                getDirection(userLocation, userDestination);
            }

        });

        return view;
    }

    private void getDirection(String from, String to){
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + from + "/" + to);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}