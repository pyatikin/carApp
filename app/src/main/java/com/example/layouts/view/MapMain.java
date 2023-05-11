package com.example.layouts.view;


import static com.yandex.runtime.Runtime.getApplicationContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.layouts.R;


import com.example.layouts.databinding.ActivityMapBinding;
import com.example.layouts.databinding.BottomMainBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.mapview.MapView;

import org.jetbrains.annotations.Nullable;

public class MapMain extends AppCompatActivity {
    ActivityMapBinding binding;
    //MapYandexBinding binding_try;
    private final String MAPKIT_API_KEY = "f08f11c2-b13e-4395-baee-04502253f4c8";
    private final Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private MapView mapView;
    private Point person;
    private AppBarConfiguration mAppBarConfiguration;
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST=1337;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;


    @Override
    protected void onStart() {
        super.onStart();

    }

    final String LOG_TAG = "myLogs";
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        /*MapKitFactory.initialize(this);*/
        /*super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.mainMapLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.map_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Log.d(LOG_TAG, "onCreate");*/
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*setSupportActionBar(binding.toolbar);*/
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.round_menu_24);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = binding.mainMapLayout;
        binding.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = binding.navView;
        navigationView.setItemIconTintList(null);
        NavController navController = Navigation.findNavController(this, R.id.map_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
        if (!canAccessLocation()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.map_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/


    }



    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.map_yandex, container, false);
    }*/

    /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.initialize(requireContext());
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG_TAG, "onViewCreated");


        binding = MapYandexBinding.bind(view);
        //getActivity().setActionBar(binding.appBarMain.toolbar);
        //setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawler =binding.drawerLayout;

        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawler)
                .build();
        NavController navController = Navigation.findNavController(view);

        NavigationUI.setupActionBarWithNavController((AppCompatActivity) getActivity(), navController);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.toolbar.setup
        //NavigationUI.setupWithNavController(navigationView, navController);
       *//* binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*//*

        // Создание MapView.
        //binding = MapBinding.bind(v)
        mapView = (MapView)getView().findViewById(R.id.mapview);

        // Перемещение камеры в центр Санкт-Петербурга.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
        ScreenPoint point = new ScreenPoint(1, 30);
        //binding = MapYandexBinding.bind(view);
        Map this_map = this;
        *//*binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "LOGIN", Toast.LENGTH_SHORT).show();
                *//**//*Navigation.findNavController(view).navigate(R.id.action_map_to_menu);*//**//*
                FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                //ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
                //ft.add(R.id.menu, Map.this);
                //ft.addToBackStack(null);
                //ft.commit();
                Menu menu = new Menu();
                ft.add(R.id.fragmentContainerView, menu);
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/

    //mapView.getMap().setNightModeEnabled(true);

    //}
    /*@Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
        Log.d(LOG_TAG, "onDestroy");
    }
    @Override
    public void onStart() {
        // Activity onStart call must be passed to both MapView and MapKit instance.
        //MapKitFactory.initialize(requireContext());
        super.onStart();
        Log.d(LOG_TAG, "onViewCreated");*/


        //binding = MapYandexBinding.bind(this.getCurrentFocus());

        //getActivity().setActionBar(binding.appBarMain.toolbar);
        //setSupportActionBar(binding.appBarMain.toolbar);



        // Создание MapView.
        //binding = MapBinding.bind(v)
        //binding = MapYandexBinding.bind(view);

        /*binding.menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Toast.makeText(getContext(), "LOGIN", Toast.LENGTH_SHORT).show();
        *//**//*Navigation.findNavController(view).navigate(R.id.action_map_to_menu);*//**//*
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        //ft.add(R.id.menu, Map.this);
        //ft.addToBackStack(null);
        //ft.commit();
        Menu menu = new Menu();
        ft.add(R.id.fragmentContainerView, menu);
        ft.addToBackStack(null);
        ft.commit();
    }*/


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.map_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /*private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        ft.add(R.id.menu, fragment
                ,"MyStringIdentifierTag");
        ft.addToBackStack(null);
        ft.commit();
    }*/
    public ActivityMapBinding getBinding(){
        return binding;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED== getApplicationContext().checkSelfPermission(perm));
    }


}
