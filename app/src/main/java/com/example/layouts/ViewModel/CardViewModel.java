package com.example.layouts.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.layouts.MainActivity;
import com.example.layouts.Models.Card;
import com.example.layouts.Repository.CardRepo;
import com.example.layouts.Repository.UserRepo;

public class CardViewModel extends ViewModel {

    //UserViewModelBinding binding;
    private MutableLiveData<Card> CardLiveData = new MutableLiveData<>();
    private UserViewModel user = MainActivity.getUserViewModel();
    private UserRepo repository = MainActivity.newInstance();
    private CardRepo cardRepo = MainActivity.getCardRepo();

    public void CreateNewCard(Context c, String number_card, String date_month, String CVV) {

        if (cardRepo.getCardByNumber(number_card) != null) {
            Toast.makeText(c, "Карта уже существует!", Toast.LENGTH_SHORT).show();
        } else {
            cardRepo.AddNewCard(number_card, date_month, CVV);
            user.getUser_m().getValue().setCard_list(cardRepo.getCards());
            Toast.makeText(c, "Карта успешно добавлена!", Toast.LENGTH_SHORT).show();
        }
    }

    public void giveCard(String number_card) {
        CardLiveData.setValue(cardRepo.getCardByNumber(number_card));
    }

    public MutableLiveData<Card> getCard() {
        return CardLiveData;
    }

    public void DeleteCard() {
        cardRepo.DeleteCard(CardLiveData.getValue().getNumber_card());
        System.out.println("DSNSIJEFWEFEW ====== " + cardRepo.getCards());
        user.getUser_m().getValue().setCard_list(cardRepo.getCards());
    }

}
