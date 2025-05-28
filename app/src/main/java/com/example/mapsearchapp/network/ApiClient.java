package com.example.mapsearchapp.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://us1.locationiq.com/v1/";  //   URL goc cua LocationIQ
    private static Retrofit retrofit; // giu instance duy nhat của Retrofit

    //  tra ve ApiService
    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Gắn base URL vao Retrofit
                    .addConverterFactory(GsonConverterFactory.create())  // Dung Gson de chuyen JSON sang Java
                    .build();
        }
        return retrofit.create(ApiService.class);  // Tra ve interface ApiService
    }
}
