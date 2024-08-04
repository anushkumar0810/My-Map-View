package com.example.mymapview;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class RegisterFragment extends Fragment {

    private EditText address_text, apartment_text, city_text, state_text, country_text, zip_text;
    private AppCompatButton signup;
    private static final String API_URL = "https://addressvalidation.googleapis.com/v1:validateAddress?key=";
    private static final String API_KEY = "Fill your google cloud api key here";
    Context context;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_register, container, false);

        context = requireContext();

        address_text = view.findViewById(R.id.editTextAddress);
        apartment_text = view.findViewById(R.id.editTextApartment);
        city_text = view.findViewById(R.id.editTextCity);
        state_text = view.findViewById(R.id.editTextState);
        country_text = view.findViewById(R.id.editTextCountry);
        zip_text = view.findViewById(R.id.editTextZipcode);
        signup = view.findViewById(R.id.register);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAddressFields();
            }
        });

        return view;
    }

    private void validateAddressFields() {
        String address = address_text.getText().toString().trim();
        String apartment = apartment_text.getText().toString().trim();
        String city = city_text.getText().toString().trim();
        String state = state_text.getText().toString().trim();
        String country = country_text.getText().toString().trim();
        String zipcode = zip_text.getText().toString().trim();

        if (address.isEmpty() || city.isEmpty() || state.isEmpty() || country.isEmpty() || zipcode.isEmpty()) {
            showToast("Please fill all fields");
        } else {
            validateAddress(address, apartment, city, state, zipcode, country);
        }
    }

    private void validateAddress(String address, String apartment, String city, String state, String postalCode, String country) {
        OkHttpClient client = new OkHttpClient();

        JSONObject addressJson = new JSONObject();

        try {
            addressJson.put("addressLines", new JSONArray().put(address));

            // print the values coming from edittext
            Log.d("TAG", "ccc: "+address);
            Log.d("TAG", "validateAddress: "+apartment);
            Log.d("TAG", "validateAddress: "+city);
            Log.d("TAG", "validateAddress: "+state);
            Log.d("TAG", "validateAddress: "+country);
            Log.d("TAG", "validateAddress: "+postalCode);

            JSONObject requestPayload = new JSONObject();
            requestPayload.put("address", addressJson);
            requestPayload.put("previousResponseId", "");

            RequestBody body = RequestBody.create(requestPayload.toString(), MediaType.parse("application/json; charset=utf-8"));
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(API_URL + API_KEY)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    showToast("Network error: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();

                        Log.d("TAG", "ResponseData: " + responseData);

                        try {
                            Gson gson = new GsonBuilder().create();
                            AddressResponse addressResponse = gson.fromJson(responseData, AddressResponse.class);
                            handleAddressResponse(addressResponse, country);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            showAlert("Failed to parse the response");
                        }
                    } else {
                        String responseData = response.body().string();
                        Log.d("TAG", "Response Error Data: " + responseData);
                        showAlert("Please enter a valid address");
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            showToast("JSON error: " + e.getMessage());
        }
    }


    private void handleAddressResponse(AddressResponse addressResponse, String country) {
        if (addressResponse != null && addressResponse.getResult() != null) {
            AddressResponse.Result result = addressResponse.getResult();
            AddressResponse.Verdict verdict = result.getVerdict();
            AddressResponse.Address address = result.getAddress();

            Log.d("TAG", "handleAddressResponse: " + verdict.getValidationGranularity());


                boolean isValid = true;

                for (int i = 0; i < address.getAddressComponents().size(); i++) {
                    AddressResponse.AddressComponent component = address.getAddressComponents().get(i);
                    String componentType = component.getComponentType();
                    String componentName = component.getComponentName().getText();

                    switch (componentType.toLowerCase()) {
                        case "locality":
                            if (!componentName.equalsIgnoreCase(city_text.getText().toString().trim())) {
                                isValid = false;
                            }
                            break;
                        case "administrative_area_level_1":
                            if (!componentName.equalsIgnoreCase(state_text.getText().toString().trim())) {
                                isValid = false;
                            }
                            break;
                        case "country":
                            if (!componentName.equalsIgnoreCase(country_text.getText().toString().trim())) {
                                isValid = false;
                            }
                            break;
                        case "postal_code":
                            if (!componentName.equalsIgnoreCase(zip_text.getText().toString().trim())) {
                                isValid = false;
                            }
                            break;
                        default:
                            // Handle other types if necessary
                            break;
                    }

                    if (!isValid) break; // Exit loop if any field does not match
                }

                if (isValid) {
                    showToast("Address Updated Successfully...");
                } else {
                    showAlert("The address is not complete or valid");
                }

        } else {
            showAlert("Invalid response from server");
        }
    }

    private void showAlert(String message) {
        new Handler(Looper.getMainLooper()).post(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Okay", (dialog, id) -> dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }


    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
    }



}