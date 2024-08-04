package com.example.mymapview;

import android.graphics.PorterDuff;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

public class CustomizeSearchView {

    public static void customizeSearchView(SearchView searchView, int textColor, int iconColor) {
        // Change the text color
        try {
            EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            if (searchEditText != null) {
                searchEditText.setTextColor(textColor);
                searchEditText.setHintTextColor(textColor); // Optional: Change hint text color as well
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Change the icon color
        try {
            ImageView searchCloseIcon = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
            ImageView searchVoiceIcon = searchView.findViewById(androidx.appcompat.R.id.search_voice_btn);
            ImageView searchGoIcon = searchView.findViewById(androidx.appcompat.R.id.search_go_btn);
            ImageView searchMagIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);

            if (searchCloseIcon != null) {
                searchCloseIcon.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
            }
            if (searchVoiceIcon != null) {
                searchVoiceIcon.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
            }
            if (searchGoIcon != null) {
                searchGoIcon.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
            }
            if (searchMagIcon != null) {
                searchMagIcon.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
