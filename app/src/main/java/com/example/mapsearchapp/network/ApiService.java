package com.example.mapsearchapp.network;

import com.example.mapsearchapp.model.LocationResult;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiService {
    // Goi den endpoint search cua LocationIQ
    @GET("search.php")
    Call<List<LocationResult>> searchAddress(
            @Query("key") String apiKey,      //  khoa truy cap API
            @Query("q") String keyword,       //  tu khoa tim kiem
            @Query("format") String format
    );
}
