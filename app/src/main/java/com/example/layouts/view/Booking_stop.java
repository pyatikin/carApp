package com.example.layouts.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.layouts.MainActivity;
import com.example.layouts.R;
import com.example.layouts.ViewModel.CardViewModel;
import com.example.layouts.ViewModel.CarsViewModel;
import com.example.layouts.ViewModel.UserViewModel;
import com.example.layouts.databinding.BookingBinding;
import com.example.layouts.databinding.BookingCancelBinding;
import com.example.layouts.databinding.BookingStopBinding;

import java.util.Date;

public class Booking_stop extends Fragment {
    BookingStopBinding binding;
    private UserViewModel user;
    Repo localRepo = Bottom_main.repo;
    private CarsViewModel carsViewModel = MainActivity.getCarsViewModel();
    public Booking_stop(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.booking_stop,container,false);
        // return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = BookingStopBinding.bind(view);
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localRepo.endTime = new Date();
                carsViewModel.CreateNewCar(getContext(), localRepo.carCircle.getUserData().toString(), 50, 10, localRepo.startTime, localRepo.endTime);
                localRepo.bottomSheetDialog.cancel();
                Navigation.findNavController(view).navigate(R.id.action_booking_stop_to_booking);
                try {
                    Navigation.findNavController(localRepo.mapAddress).navigate(R.id.action_mapAddress_to_map);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }
}
