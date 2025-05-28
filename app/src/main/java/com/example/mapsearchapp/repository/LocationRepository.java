package com.example.mapsearchapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mapsearchapp.model.LocationResult;
import com.example.mapsearchapp.network.ApiClient;
import com.example.mapsearchapp.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {
    // key lay tu LocationIQ
    private static final String API_KEY = "pk.42b355e43883770d98db098f5c9d2c01";
    private static final String FORMAT = "json";

    private ApiService apiService;

    public LocationRepository() {
        apiService = ApiClient.getApiService(); // khoi tao retrofit
    }

    public LiveData<List<LocationResult>> searchLocation(String keyword) {
        MutableLiveData<List<LocationResult>> data = new MutableLiveData<>();

        // goi api tra ve danh sach location
        apiService.searchAddress(API_KEY, keyword, FORMAT).enqueue(new Callback<List<LocationResult>>() {
            @Override
            public void onResponse(Call<List<LocationResult>> call, Response<List<LocationResult>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body()); // set du lieu vao livedata
                } else {
                    data.setValue(null); // neu loi tra ve null
                }
            }

            @Override
            public void onFailure(Call<List<LocationResult>> call, Throwable t) {
                data.setValue(null); // khi call api that bai
            }
        });

        return data;
    }
}
