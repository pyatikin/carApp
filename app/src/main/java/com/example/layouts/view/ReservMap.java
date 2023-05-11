package com.example.layouts.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.layouts.R;

import com.example.layouts.databinding.MapYandexBinding;
import com.yandex.mapkit.Animation;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.ScreenPoint;
import com.yandex.mapkit.geometry.Point;

import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;



public class ReservMap extends Fragment {
    MapYandexBinding binding;
    private final String MAPKIT_API_KEY = "f08f11c2-b13e-4395-baee-04502253f4c8";
    private final Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private MapView mapView;
    private Point person;

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(requireContext());
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate");
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.map_fragment, fragment);
        ft.commit();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.map_yandex, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.initialize(requireContext());
        super.onViewCreated(view, savedInstanceState);
        Log.d(LOG_TAG, "onViewCreated");
        // Создание MapView.
        //binding = MapBinding.bind(v)
        mapView = (MapView) getView().findViewById(R.id.mapview);

        // Перемещение камеры в центр Санкт-Петербурга.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
        ScreenPoint point = new ScreenPoint(1, 30);
        binding = MapYandexBinding.bind(view);

       /* binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "LOGIN", Toast.LENGTH_SHORT).show();
                *//*Navigation.findNavController(view).navigate(R.id.action_map_to_menu);*//*
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

    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onStart() {
        // Activity onStart call must be passed to both MapView and MapKit instance.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
    /*private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        //ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        ft.add(R.id.menu, fragment
                ,"MyStringIdentifierTag");
        ft.addToBackStack(null);
        ft.commit();
    }*/
}