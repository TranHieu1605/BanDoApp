package com.example.mapsearchapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mapsearchapp.model.LocationResult;
import com.example.mapsearchapp.repository.LocationRepository;

import java.util.List;
import android.os.Handler;
import android.os.Looper;

public class SearchViewModel extends ViewModel {
    private final LocationRepository repository = new LocationRepository(); // tao repository
    private final MutableLiveData<List<LocationResult>> searchResults = new MutableLiveData<>(); // du lieu ket qua

    private final Handler handler = new Handler(Looper.getMainLooper()); // de debounce
    private Runnable searchRunnable; // runnable chay sau 1s

    public LiveData<List<LocationResult>> getSearchResults() {
        return searchResults;
    }

    public void searchAddress(String keyword) {
        // neu tiep tuc , huy request cu
        if (searchRunnable != null) {
            handler.removeCallbacks(searchRunnable);
        }

        searchRunnable = () -> {
            // goi api sau 1 giay
            repository.searchLocation(keyword).observeForever(results -> {
                searchResults.setValue(results); // cap nhat du lieu
            });
        };

        // delay 1s moi chay request
        handler.postDelayed(searchRunnable, 1000);
    }
}
