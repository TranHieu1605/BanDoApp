package com.example.mapsearchapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapsearchapp.ui.SearchAdapter;
import com.example.mapsearchapp.viewmodel.SearchViewModel;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SearchViewModel viewModel;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // nap giao dien chinh

        EditText etSearch = findViewById(R.id.etSearch); // o tim kiem
        RecyclerView rvResults = findViewById(R.id.rvResults); // danh sach ket qua

        // khoi tao adapter va gan cho recycler view
        adapter = new SearchAdapter(this);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setAdapter(adapter);

        // khoi tao viewmodel
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        // theo doi thay doi o tim kiem
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.searchAddress(s.toString()); // truyen tu khoa vao viewmodel
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // quan sat livedata va cap nhat giao dien
        viewModel.getSearchResults().observe(this, results -> {
            if (results != null) {
                adapter.setKeyword(etSearch.getText().toString());
                adapter.setData(results); // gan du lieu moi cho adapter
            }
        });
    }
}