package com.example.mymapview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
        setupViewPager();

    }

    private void initializeUI() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setCustomTabViews();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // This method can be used to animate color changes while scrolling
            }

            @Override
            public void onPageSelected(int position) {
                updateTabIconColor(position);
                if (position == 0 || position == 1 || position == 2) {
                    hideKeyboard();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Not needed for this functionality
            }
        });



    }

    @SuppressLint("InflateParams")
    private void setCustomTabViews() {
        int[] tabIcons = {
                R.drawable.ic_location,
                R.drawable.ic_register,
                R.drawable.ic_map
        };

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View customTab = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                ImageView tabIcon = customTab.findViewById(R.id.tab_icon);
                tabIcon.setImageResource(tabIcons[i]);
                tab.setCustomView(customTab);
            }
        }
        updateTabIconColor(tabLayout.getSelectedTabPosition());
    }

    private void updateTabIconColor(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null && tab.getCustomView() != null) {
                ImageView tabIcon = tab.getCustomView().findViewById(R.id.tab_icon);
                tabIcon.setColorFilter(i == position ? Color.parseColor("#32A5D2") : Color.parseColor("#888888"));
            }
        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MapFragment();
                case 1:
                    return new RegisterFragment();
                case 2:
                    return new GetLocationFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; // Number of fragments
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Map";
                case 1:
                    return "Register";
                case 2:
                    return "Get Location";
                default:
                    return null;
            }
        }
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
