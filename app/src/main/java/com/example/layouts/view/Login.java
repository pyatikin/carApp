package com.example.layouts.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.layouts.MainActivity;
import com.example.layouts.R;
import com.example.layouts.ViewModel.UserViewModel;
import com.example.layouts.databinding.LoginBinding;

public class Login extends Fragment {
    LoginBinding binding;
    private UserViewModel user = MainActivity.getUserViewModel();
    public Login(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //user = new ViewModelProvider(this).get(UserViewModel.class);
        binding = LoginBinding.inflate(inflater,container,false);

        //View v = binding.getRoot();
        //binding.bottomNavigation.
        //MenuItem item = binding.bottomNavigation.getMenu().findItem(3);
        //item.setChecked(true);
        return inflater.inflate(R.layout.login,container,false);
        // return v;
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainerView, fragment);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.editEmailAddress.setText("");
        binding.ediPassword.setText("");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = LoginBinding.bind(view);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "LOGIN", Toast.LENGTH_SHORT).show();
                if (user.loginViewModel(getContext(),binding.editEmailAddress.getText().toString(),
                        binding.ediPassword.getText().toString()) ) {
                    user.giveUser(binding.editEmailAddress.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.action_login_to_mapMain);
                }
            }
        });
        binding.registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Registration", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_login_to_registration);

            }
        });
 /*       user.getUser_m().observe(getViewLifecycleOwner(), user1 -> {
            binding.login.setText(user1.getLogin());

        });*/

    }
}
