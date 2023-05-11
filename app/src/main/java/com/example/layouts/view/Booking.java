package com.example.layouts.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.layouts.R;
import com.example.layouts.ViewModel.UserViewModel;

import com.example.layouts.databinding.BookingBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Date;

public class Booking extends Fragment {
        Repo localRepo = Bottom_main.repo;
        BookingBinding binding;
        private UserViewModel user;
        public Booking(){}
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //user = new ViewModelProvider(this).get(UserViewModel.class);
            /*binding = BookingBinding.inflate(inflater,container,false);
            binding.buttonAdress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("My Logs:", "FIX");
                    Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_booking_to_booking_adress);
                }
            });*/
            //View v = binding.getRoot();
            //binding.bottomNavigation.
            //MenuItem item = binding.bottomNavigation.getMenu().findItem(3);
            //item.setChecked(true);
            return inflater.inflate(R.layout.booking,container,false);
            // return v;
        }
        private void loadFragment(Fragment fragment) {
            FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.bottom_fragment, fragment);
            ft.commit();
        }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            binding = BookingBinding.bind(view);
            binding.buttonAdress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("My Logs:", "FIX");
                    //Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_booking_to_booking_adress);

                }
            });
            binding.bookCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    localRepo.startTime = new Date();
                    Navigation.findNavController(view).navigate(R.id.action_booking_to_booking_stop);
                }
            });
            /*binding.login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "LOGIN", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_login_to_mapMain);
                }
            });
            binding.registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Registration", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_login_to_registration);

                }
            });*/
        }


}
