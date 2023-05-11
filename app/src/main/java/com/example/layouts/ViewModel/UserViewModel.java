package com.example.layouts.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.layouts.MainActivity;
import com.example.layouts.Models.User;
import com.example.layouts.Repository.UserRepo;

public class UserViewModel extends ViewModel {

    //UserViewModelBinding binding;
    private MutableLiveData<User> UserLiveData = new MutableLiveData<>();
    private UserRepo repository = MainActivity.newInstance();

    /*public UserViewModel(Context context) {
        repository = new UserRepo();
    }*/
    public void giveUser(String login) {
        UserLiveData.setValue(repository.getUserByLogin(login));
    }

    public MutableLiveData<User> getUser_m() {
        return UserLiveData;
    }

    /*LiveData<Result> login(String login, String password){
        LiveData<Result> resultLiveData = new MutableLiveData<Result>();
        if (repository.login(login,password) == 0) {
        //resultLiveData = repository.login(login,password);

        }
    }*/

    public boolean CheckAddedCard() { // проверка наличия добавленных пользователем карт

        if (UserLiveData.getValue().getCard_list() == null) {
            return false;
        }
        return true;

    }

    public boolean loginViewModel(Context context, String login, String password) {

        if ((repository.getUserByLogin(login)) == null) { // если такого юзера нет
            Toast.makeText(context, "Пользователя с таким именем нет!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (repository.isPasswordForUserValid(login,password)) {
            giveUser(login); // mutablelivedata назначим текущего юзера
            Toast.makeText(context,"Успешная авторизация!",Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context,"Пароль неверный!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean CheckAlreadyRegistered(Context context, String login) {

        if ((repository.getUserByLogin(login)) != null) {  // если пользователь уже зареган
            Toast.makeText(context.getApplicationContext(), repository.getUserByLogin(login).getLogin(),
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(context.getApplicationContext(), repository.getUserByLogin(login).getPassword(),
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Пользователь с таким именем уже существует!",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void addNewUser(String login, String password) {
        repository.AddNewUser(login,password);
        //giveUser(login); // mutablelivedata назначим текущего юзера
    }

    public void SaveEmail(String email) {
        UserLiveData.getValue().setEmail_address(email);
        //repository.SaveEmail(UserLiveData.getValue().getLogin(),email);
    }

    public void SavePhone(String phone) {
        UserLiveData.getValue().setPhone_number(phone);
    }

    public void Save_Pass_Data(String FIO, String SerialNumber, String DateBirth ) {
        UserLiveData.getValue().setPass_data(FIO);
        UserLiveData.getValue().setPass_data_serial_number(SerialNumber);
        UserLiveData.getValue().setDate_birth(DateBirth);
    }

    // копия на всякий случай
    /*public void loginViewModel(Context context, String login, String password) {
        System.out.println("from aurotis = " + login + "  "+ password);
        //repository = new UserRepo();
        if ((repository.getUserByLogin(login)) == null) // если такого юзера нет
            Toast.makeText(context,"Пользователя с таким именем нет!",
                    Toast.LENGTH_SHORT).show();
        else if (repository.getUserByLogin(login) != null) {
            repository.login(context,login,password);
        }
        //repository.login(context,login,password);
    }*/

    public void TopUp(String type_of_stock,String number) {
        if (type_of_stock.equals("Фондовый рынок")) {
            int g = Integer.parseInt(UserLiveData.getValue().getStock_market_account()); // текущий баланс
            int g1 = Integer.parseInt(number);
            UserLiveData.getValue().setStock_market_account(Long.toString(g + g1));
        } else {
            long g = Integer.parseInt(UserLiveData.getValue().getCurrency_market_account()); // текущий баланс
            long g1 = Integer.parseInt(number);
            UserLiveData.getValue().setCurrency_market_account(Long.toString(g + g1));
        }
    }

    public boolean Withdraw(Context c,String type_of_stock, String number) {
        if (type_of_stock.equals("Фондовый рынок")) {
            long g = Integer.parseInt(UserLiveData.getValue().getStock_market_account()); // текущий баланс
            long g1 = Integer.parseInt(number);
            if (g1 > g) {
                Toast.makeText(c,"Недостаточно денег на счету", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                UserLiveData.getValue().setStock_market_account(Long.toString(g - g1));
                return true;
            }
        } else {
            long g = Integer.parseInt(UserLiveData.getValue().getCurrency_market_account()); // текущий баланс
            long g1 = Integer.parseInt(number);
            if (g1 > g) {
                Toast.makeText(c,"Недостаточно денег на счету", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                UserLiveData.getValue().setCurrency_market_account(Long.toString(g - g1));
                return true;
            }
        }
    }
}


    /*private User model;

    private final String successMessage = "Login successful";
    private final String errorMessage_Login = "Login is not valid. Min length = 1 and max = 10";
    private final String errorMessage_Pass = "Password is not valid. Min length = 6 and max = 15";

    @Bindable
    private String toastMessage = null;

    public UserViewModel() {
        model = new User();
    }
    
    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserLogin() {
        return model.getLogin();
    }

    public void setUserLogin(String login) {
        model.setLogin(login);
        notifyPropertyChanged(BR.userLogin);
    }

    @Bindable
    public String getUserPassword() {
        return model.getPassword();
    }

    public void setUserPassword(String password) {
        model.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public void onButtonClicked() {
        switch (isValid()) {
            case 0:
                setToastMessage(errorMessage_Login);
                break;
            case 1:
                setToastMessage(errorMessage_Pass);
                break;
            case 2:
                setToastMessage(successMessage);
                break;
        }
    }

    public int isValid() {
        if (getUserLogin().length() == 0 || getUserLogin().length() > 10)
            return 0;
        else if (getUserPassword().length() <= 5 || getUserPassword().length() >=16)
            return 1;
        else
            return 2;
    }*/
