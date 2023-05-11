package com.example.layouts.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.layouts.MainActivity;
import com.example.layouts.Models.Car;
import com.example.layouts.Models.Card;
import com.example.layouts.R;
import com.example.layouts.ViewModel.UserViewModel;
import com.example.layouts.adapters.CarAdapter;
import com.example.layouts.adapters.CardAdapter;
import com.example.layouts.databinding.HistoryBinding;

import java.util.ArrayList;

public class History extends Fragment {
    HistoryBinding binding;
    private UserViewModel user = MainActivity.getUserViewModel();
    private ArrayList<Car> cars = new ArrayList<Car>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //Car first = new Car("А 999 АА", 10, 10);
        //cars.add(first);
        return inflater.inflate(R.layout.history,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //binding = ProfileBinding.bind(view);
        binding = HistoryBinding.bind(view);
        user.getUser_m().observe(getViewLifecycleOwner(),user1 -> {
            binding.ListViewCars.setLayoutManager(new LinearLayoutManager(getContext()));
            cars = user1.getCars_list();
            CarAdapter adapter = new CarAdapter(this, cars);
            binding.ListViewCars.setAdapter(adapter);
        });
    }
}
