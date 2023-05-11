package com.example.layouts.view;

import android.os.Bundle;
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
import com.example.layouts.databinding.BookingAdressBinding;
import com.example.layouts.databinding.GetAdressBinding;

public class Address extends Fragment {
    GetAdressBinding binding;
    private UserViewModel user;

    public Address() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //user = new ViewModelProvider(this).get(UserViewModel.class);
        binding = GetAdressBinding.inflate(inflater, container, false);
        //View v = binding.getRoot();
        //binding.bottomNavigation.
        //MenuItem item = binding.bottomNavigation.getMenu().findItem(3);
        //item.setChecked(true);
        return inflater.inflate(R.layout.get_adress, container, false);
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
        binding = GetAdressBinding.bind(view);
        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_address_to_booking_adress);
            }
        });
        /*binding.usual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_booking_adress_to_booking);
            }
        });*/
    }

}
