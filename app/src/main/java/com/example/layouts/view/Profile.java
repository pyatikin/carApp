package com.example.layouts.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.layouts.MainActivity;
import com.example.layouts.R;
import com.example.layouts.ViewModel.UserViewModel;
import com.example.layouts.databinding.ProfileBinding;
import com.google.android.material.navigation.NavigationView;

public class Profile extends Fragment {
    ProfileBinding binding;
    private UserViewModel user = MainActivity.getUserViewModel();

    public Profile(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        /*View menu = getActivity().findViewById(R.id.imageMenu);

        menu.setBackgroundResource(R.drawable.back_arrow);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Back to map", Toast.LENGTH_SHORT).show();
                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                NavController navController = Navigation.findNavController(getActivity(), R.id.map_fragment);
                NavigationUI.setupWithNavController(navigationView, navController);
            }
        });*/
        return inflater.inflate(R.layout.profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = ProfileBinding.bind(view);
        user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            binding.editTextPhone.setText(user1.getPhone_number());
            binding.editTextTextEmailAddress2.setText(user1.getEmail_address());
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextTextEmailAddress2.getText().toString()).matches()) {
                    Toast.makeText(getContext(),"Введите корректный email", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.SaveEmail(binding.editTextTextEmailAddress2.getText().toString());
                    Toast.makeText(getContext(),"Почта сохранена",Toast.LENGTH_SHORT).show();
                }
                if (!Patterns.PHONE.matcher(binding.editTextPhone.getText().toString()).matches()) {
                    Toast.makeText(getContext(),"Введите корректный номер телефона!",Toast.LENGTH_SHORT).show();
                } else {
                    user.SavePhone(binding.editTextPhone.getText().toString());
                    Toast.makeText(getContext(),"Успешное сохранение!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
