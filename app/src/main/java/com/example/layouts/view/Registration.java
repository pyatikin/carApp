package com.example.layouts.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.layouts.MainActivity;
import com.example.layouts.R;
import com.example.layouts.ViewModel.UserViewModel;
import com.example.layouts.databinding.RegisterBinding;

public class Registration extends Fragment {
    private UserViewModel user = MainActivity.getUserViewModel();
    RegisterBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = RegisterBinding.bind(view);
        binding.registrationNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editTextTextEmailAddress.getText().toString().length() == 0 ||
                        binding.editTextTextEmailAddress.getText().toString().length() > 10) {
                    Toast.makeText(getContext(),"Логин Некорректный. Допустимая длина от 1 до 10",
                            Toast.LENGTH_SHORT).show();
                } else if (!user.CheckAlreadyRegistered(getContext(),binding.editTextTextEmailAddress.getText().toString())) { // если такого пользователя нет
                    if (binding.editTextTextPassword.getText().toString().equals(
                            binding.editTextTextPassword2.getText().toString())) {
                        user.addNewUser(binding.editTextTextEmailAddress.getText().toString(),
                                binding.editTextTextPassword.getText().toString());
                        user.giveUser(binding.editTextTextEmailAddress.getText().toString());
                        Toast.makeText(getContext(), "Регистрация успешная!", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_registration_to_mapMain);
                    } else {
                        Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
