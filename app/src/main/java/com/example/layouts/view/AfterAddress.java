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
import com.example.layouts.databinding.BookingAfterAdressBinding;
import com.example.layouts.databinding.BookingBinding;

import java.util.Date;

public class AfterAddress extends Fragment{
    BookingAfterAdressBinding binding;
    Repo localRepo = Bottom_main.repo;
    private UserViewModel user;
    public AfterAddress(){}
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
        binding = BookingAfterAdressBinding.inflate(inflater);
        return inflater.inflate(R.layout.booking_after_adress,container,false);
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
        binding = BookingAfterAdressBinding.bind(view);
        localRepo.afterAddressView = view;

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localRepo.bottomSheetDialog.cancel();
            }
        });
        binding.OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_afterAddress_to_booking_stop) ;
                localRepo.startTime = new Date();
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
