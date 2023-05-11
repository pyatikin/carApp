package com.example.layouts.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.layouts.MainActivity;
import com.example.layouts.Models.Car;
import com.example.layouts.Models.Card;
import com.example.layouts.Repository.CardRepo;
import com.example.layouts.Repository.CarsRepo;
import com.example.layouts.Repository.UserRepo;

import java.util.Date;

public class CarsViewModel extends ViewModel {
    private MutableLiveData<Car> CarLiveData = new MutableLiveData<>();
    private UserViewModel user = MainActivity.getUserViewModel();
    private UserRepo repository = MainActivity.newInstance();
    private CarsRepo carsRepo = MainActivity.getCarsRepo();

    /*public void CreateNewCard(Context c, String number_card, String date_month, String CVV) {

        if (cardRepo.getCardByNumber(number_card) != null) {
            Toast.makeText(c, "Карта уже существует!", Toast.LENGTH_SHORT).show();
        } else {
            cardRepo.AddNewCard(number_card, date_month, CVV);
            user.getUser_m().getValue().setCard_list(cardRepo.getCards());
            Toast.makeText(c, "Карта успешно добавлена!", Toast.LENGTH_SHORT).show();
        }
    }*/
    public void CreateNewCar(Context c, String car_number, int fuel, int price, Date start, Date end ){
        carsRepo.AddNewCar(car_number, fuel, price, start, end);
        user.getUser_m().getValue().setCars_list(carsRepo.getCars());
    }
}
