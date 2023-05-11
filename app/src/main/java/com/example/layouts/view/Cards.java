package com.example.layouts.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.layouts.MainActivity;
import com.example.layouts.Models.Card;
import com.example.layouts.R;
import com.example.layouts.ViewModel.UserViewModel;
import com.example.layouts.adapters.CardAdapter;
import com.example.layouts.databinding.ListOfCardsBinding;

import java.util.ArrayList;

public class Cards extends Fragment {

    private ListOfCardsBinding binding;
    private UserViewModel user;
    private ArrayList<Card> cards = new ArrayList<Card>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListOfCardsBinding.inflate(inflater,container,false);
        user = MainActivity.getUserViewModel();
        //return inflater.inflate(R.layout.your_bag,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user.getUser_m().observe(getViewLifecycleOwner(),user1 -> {

            binding.ListViewCards.setLayoutManager(new LinearLayoutManager(getContext()));
            cards = user1.getCard_list();
            CardAdapter adapter = new CardAdapter(this, cards);
            binding.ListViewCards.setAdapter(adapter);
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_cards_to_nav_settings);
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_cards_to_addCard);
            }
        });

        /*binding.topApp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_cards2_to_profile2);
            }
        });

        binding.topApp.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Navigation.findNavController(requireView()).navigate(R.id.action_cards2_to_addCard);
                return true;
            }
        });*/

    }

    public void setInitialData() {
        cards.add(new Card("2234 4567 7889 4324", "08/24","123"));
        cards.add(new Card ("4234 4567 7889 6467", "08/24","456"));
        cards.add(new Card ("4234 4567 7889 2442", "08/24","765"));
        cards.add(new Card ("2234 4567 7889 0757", "08/24","535"));
    }


}
