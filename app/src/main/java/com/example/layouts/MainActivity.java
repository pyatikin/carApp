/*
package com.example.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);
    }
}*/
package com.example.layouts;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.layouts.Repository.CardRepo;
import com.example.layouts.Repository.CarsRepo;
import com.example.layouts.Repository.UserRepo;
import com.example.layouts.ViewModel.CardViewModel;
import com.example.layouts.ViewModel.CarsViewModel;
import com.example.layouts.ViewModel.UserViewModel;


public class MainActivity extends AppCompatActivity {
    static UserRepo repo = new UserRepo();
    static CardRepo cardRepo = new CardRepo();
    private static UserViewModel userViewModel;
    private static CardViewModel cardViewModel;
    private static CarsViewModel carsViewModel;
    static CarsRepo carsRepo = new CarsRepo();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);
    }
    public static UserRepo newInstance() {
        return repo;
    }
    public static CardViewModel getCardViewModel() {
        return cardViewModel;
    }
    public static CarsViewModel getCarsViewModel() {
        return carsViewModel;
    }
    public static UserViewModel getUserViewModel() {
        return userViewModel;
    }
    public static CardRepo getCardRepo() {
        return cardRepo;
    }
    public static CarsRepo getCarsRepo() {
        return carsRepo;
    }
    public void init() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        carsViewModel = new ViewModelProvider(this).get(CarsViewModel.class);
    }
}



/*public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // com.example.layouts.ViewModel updates the Model
        // after observing changes in the View

        // model will also update the view
        // via the com.example.layouts.ViewModel
        FirstBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.first);
        activityMainBinding.setViewModel(new AppViewModel(this));

        activityMainBinding.executePendingBindings();

    }

    // any change in toastMessage attribute
    // defined on the Button with bind prefix
    // invokes this method
    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}*/







/*
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.ScreenPoint;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.FilteringMode;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.location.internal.LocationManagerBinding;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;*/
/*public class MainActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);

    }

    public void addNewEditText(String text) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText editText = new EditText(this);
        editText.setLayoutParams(layoutParams);
        editText.setText(text);
        mLinearLayout.addView(editText);
    }
}*/

//MAP google
/*public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}*/

//YANDEX MAP
/*
public class MainActivity extends Activity {
    */
/**
     * Замените "your_api_key" валидным API-ключом.
     * Ключ можно получить на сайте https://developer.tech.yandex.ru/
     *//*

    private final String MAPKIT_API_KEY = "f08f11c2-b13e-4395-baee-04502253f4c8";
    private final Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private MapView mapView;
    private Point person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        */
/**
         * Задайте API-ключ перед инициализацией MapKitFactory.
         * Рекомендуется устанавливать ключ в методе Application.onCreate,
         * но в данном примере он устанавливается в activity.
         *//*

        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        */
/**

         *//*

        MapKitFactory.initialize(this);

        setContentView(R.layout.map_yandex);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);

        // Перемещение камеры в центр Санкт-Петербурга.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
        ScreenPoint point = new ScreenPoint(1, 30);
        //mapView.getMap().setNightModeEnabled(true);

    }

    @Override
    protected void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}*/
