package com.example.layouts.view;

import static com.yandex.runtime.Runtime.getApplicationContext;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.layouts.MainActivity;
import com.example.layouts.R;
import com.example.layouts.databinding.BookingAdressBinding;
import com.example.layouts.databinding.BookingBinding;
import com.example.layouts.databinding.MapYandexBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.LinearRing;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.GeoObjectSelectionMetadata;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PolygonMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.AnimatedImageProvider;
import com.yandex.runtime.image.ImageProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Map extends Fragment implements GeoObjectTapListener, InputListener, UserLocationObjectListener {
    MapYandexBinding binding;
    Repo localRepo = Bottom_main.repo;
    private final String MAPKIT_API_KEY = "f08f11c2-b13e-4395-baee-04502253f4c8";
    private final Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private final Point CIRCLE_CENTER = new Point(59.956, 30.323);
    private final Point Car2 = new Point(55.670, 37.478);
    private final Point Car3 = new Point(55.670, 37.476);

    private final Point Car1 = new Point(55.605, 37.660);
    private final double OBJECT_SIZE = 0.001;

    public static MapView mapView;
    private Point person;
    private MapObjectCollection mapObjects;
    final String LOG_TAG = "myLogs";
    private UserLocationLayer userLocationLayer;
    MapKit mapKit;
    BookingBinding bookingBinding;
    // Создание MapView.
//binding = MapBinding.bind(v)
    BottomSheetDialog bottom;
    boolean first = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        /*MapKitFactory.setApiKey(MAPKIT_API_KEY);*/
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
       /* mapView = (MapView) getView().findViewById(R.id.mapview);*/

        Log.d(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.map_yandex, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.initialize(requireContext());
        super.onViewCreated(view, savedInstanceState);
        if (localRepo.getBottomSheetDialog() == null) {
            bottom = new Bottom_main(getContext());
            localRepo.bottomSheetDialog = bottom;
        }
        Log.d(LOG_TAG, "onViewCreated");
        mapView = (MapView) getView().findViewById(R.id.mapview);
        mapView.getMap().addTapListener(this);
        mapView.getMap().addInputListener(this);
        mapKit = MapKitFactory.getInstance();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(false);

        userLocationLayer.setObjectListener(this);

        mapObjects = mapView.getMap().getMapObjects().addCollection();
        createMapObjects();
        // Перемещение камеры в центр Санкт-Петербурга.
        /*mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 2),
                null);*/
        //ScreenPoint point = new ScreenPoint(1, 30);
        binding = MapYandexBinding.bind(view);
        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cameraPosition = userLocationLayer.cameraPosition();
                if (userLocationLayer.cameraPosition() != null) {
                    CameraPosition cameraPosition = new CameraPosition(userLocationLayer.cameraPosition().getTarget(),14.0f,0.0f, 0.0f);
                    mapView.getMap().move(
                            cameraPosition,
                            new Animation(Animation.Type.SMOOTH, 1),
                            null
                    );
                    //Toast.makeText(getContext(), cameraPosition.getTarget().getLatitude() + " " + cameraPosition.getTarget().getLongitude(), Toast.LENGTH_LONG).show();
                }
            }
        });
        localRepo.mainMapView = view;
        /*getActivity().findViewById(R.id.choose_adress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_map_to_mapAddress);
            }
        });*/
        /*//TODO перенести в яндекс мапу
        binding.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MapMain.this, R.style.AppBottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.booking, findViewById(R.id.booking_layout));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });*/
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
    private class CircleMapObjectUserData {
        final int id;
        final String description;

        CircleMapObjectUserData(int id, String description) {
            this.id = id;
            this.description = description;
        }
    }
    private MapObjectTapListener circleMapObjectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(MapObject mapObject, Point point) {
            if (mapObject instanceof CircleMapObject) {
                CircleMapObject circle = (CircleMapObject)mapObject;

                float randomRadius = 5.0f + 1.0f * new Random().nextFloat();

                Circle curGeometry = circle.getGeometry();
                Circle newGeometry = new Circle(curGeometry.getCenter(), randomRadius);
                circle.setGeometry(newGeometry);

                Object userData = circle.getUserData();
                if (userData instanceof CircleMapObjectUserData) {
                    CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                    /*Toast toast = Toast.makeText(
                            getApplicationContext(),
                            "Circle with id " + circleUserData.id + " and description '"
                                    + circleUserData.description + "' tapped",
                            Toast.LENGTH_SHORT);
                    toast.show();*/
                }
                /*bookingBinding = BookingBinding.inflate(getLayoutInflater());*/
                /*Bottom_main bottom = new Bottom_main(getContext());*/
                /*if (bottom == null)
                    bottom = new Bottom_main(getContext());*/
                if (bottom == null){
                    bottom = localRepo.bottomSheetDialog;
                }
                BottomSheetDialog bottomSheetDialog = bottom;
                //bottom.setCanceledOnTouchOutside(false);
                bottomSheetDialog.show();
                localRepo.bottomSheetDialog = bottom;
                localRepo.currCar = point;
                localRepo.carCircle = mapObject;

                /*Objects.requireNonNull(getActivity()).findViewById(R.id.choose_adress).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_map_to_mapAddress);
                    }
                });*/



                //bottomSheetDialog.show(/*getChildFragmentManager(), "Dialog"*/);

                /*View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.booking, getActivity().findViewById(R.id.booking_layout));
                bottomSheetDialog.setContentView(bottomSheetView);
                //bookingBinding.buttonAdress.setOnClickListener(AdressListener);
                bottomSheetDialog.show();
                bottomSheetDialog.findViewById(R.id.buttonAdress).setOnClickListener(AdressListener);*/

            }
            return true;
        }
    };

    private View.OnClickListener AdressListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast toast = Toast.makeText(getContext(), "Adress", Toast.LENGTH_SHORT);
            toast.show();
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.AppBottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                    inflate(R.layout.booking_adress, getActivity().findViewById(R.id.booking_adress_layout));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }
    };
    private void createMapObjects() {
        createTappableCircle();
        createCars();
    }
    private void createTappableCircle() {
        CircleMapObject circle = mapObjects.addCircle(
                new Circle(CIRCLE_CENTER, 100), Color.GREEN, 2, Color.RED);
        circle.setUserData(new CircleMapObjectUserData(0, "Tappable circle"));
        /*CircleMapObject circleMapObject = mapObjects.addCircle(
                new Circle(CIRCLE_CENTER2, 100), Color.GREEN,2, Color.BLUE);*/
        //circle.setZIndex(1000.0f);

        //circle.setUserData(new CircleMapObjectUserData(40, "My circle"));
        // Client code must retain strong reference to the listener.
        circle.addTapListener(circleMapObjectTapListener);
        /*circleMapObject.addTapListener(circleMapObjectTapListener);*/

    }
    private void createCars(){
        CircleMapObject circle = mapObjects.addCircle(
                new Circle(Car1, 5), Color.DKGRAY, 1.1f, Color.CYAN);
        circle.setUserData("А 123 БВ");
        //String str = circle.getUserData().toString();
        //Toast.makeText(requireContext(), str, Toast.LENGTH_LONG).show();
        circle.addTapListener(circleMapObjectTapListener);

        CircleMapObject car2 = mapObjects.addCircle(
                new Circle(Car2, 5), Color.DKGRAY, 1.1f, Color.CYAN);
        car2.setUserData("А 999 АА");
        car2.addTapListener(circleMapObjectTapListener);
        CircleMapObject car3 = mapObjects.addCircle(
                new Circle(Car3, 5), Color.DKGRAY, 1.1f, Color.CYAN);
        car3.setUserData("Р 987 ВП");
        car3.addTapListener(circleMapObjectTapListener);
    }

    @Override
    public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
        final GeoObjectSelectionMetadata selectionMetadata = geoObjectTapEvent
                .getGeoObject()
                .getMetadataContainer()
                .getItem(GeoObjectSelectionMetadata.class);

        if (selectionMetadata != null) {
            mapView.getMap().selectGeoObject(selectionMetadata.getId(), selectionMetadata.getLayerId());
        }

        return selectionMetadata != null;
    }
    @Override
    public void onMapTap(@NonNull com.yandex.mapkit.map.Map map, @NonNull Point point) {
        mapView.getMap().deselectGeoObject();
    }

    @Override
    public void onMapLongTap(@NonNull com.yandex.mapkit.map.Map map, @NonNull Point point) {

    }
    @Override
    public void onObjectAdded(UserLocationView userLocationView) {


        /*userLocationLayer.setAnchor(
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.5)),
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.83)));*/

        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                getContext(), R.drawable.search_result));

        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();

        /*pinIcon.setIcon(
                "icon",
                ImageProvider.fromResource(getContext(), R.drawable.icon),
                new IconStyle().setAnchor(new PointF(0f, 0f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(0f)
                        .setScale(1f)
        );*/

        pinIcon.setIcon(
                "pin",
                ImageProvider.fromResource(getContext(), R.drawable.search_result),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.5f)
        );

        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x55ffffff);
    }
    @Override
    public void onObjectRemoved(UserLocationView view) {
    }

    @Override
    public void onObjectUpdated(UserLocationView view, ObjectEvent event) {
    }
}