package com.example.layouts.view;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.layouts.R;
import com.example.layouts.databinding.MapYandex2Binding;
import com.example.layouts.databinding.MapYandexBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.GeoObjectCollection;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.mapkit.directions.driving.VehicleOptions;
import com.yandex.mapkit.geometry.Geo;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.InputListener;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.PolylineMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.map.VisibleRegionUtils;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.search.Response;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.search.SearchOptions;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.mapkit.search.Session;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

import java.util.ArrayList;
import java.util.List;

public class MapAddress extends Fragment implements Session.SearchListener, InputListener, UserLocationObjectListener, CameraListener, DrivingSession.DrivingRouteListener {
    MapYandex2Binding binding;
    Repo localRepo = Bottom_main.repo;
    private final String MAPKIT_API_KEY = "f08f11c2-b13e-4395-baee-04502253f4c8";
    private final Point ROUTE_START_LOCATION = new Point(55.604735, 37.661120);
    private final Point ROUTE_END_LOCATION = new Point(55.595552, 37.664114);
    public static MapView mapView;
    private UserLocationLayer userLocationLayer;
    final String LOG_TAG = "myLogs";
    MapKit mapKit;
    private EditText searchEdit;
    private Session searchSession;
    private SearchManager searchManager;
    private DrivingRouter drivingRouter;
    private DrivingSession drivingSession;
    private MapObject saveMark = null;
    MapObjectCollection mapObjects;
    private void submitQuery(String query) {
        searchSession = searchManager.submit(
                query,
                VisibleRegionUtils.toPolygon(mapView.getMap().getVisibleRegion()),
                new SearchOptions(),
                this);
    }
    @Override
    public void onStart() {
        // Activity onStart call must be passed to both MapView and MapKit instance.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(requireContext());
        SearchFactory.initialize(requireContext());
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "onCreate");
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        super.onCreateView(inflater, container, savedInstanceState);
        /* mapView = (MapView) getView().findViewById(R.id.mapview);*/
        Log.d(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.map_yandex2, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        localRepo.mapAddress = view;
        /*DRIVING*/
        DirectionsFactory.initialize(requireContext());
        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter();
        binding = MapYandex2Binding.bind(view);
        mapView = (MapView) binding.mapview2; //!!
        mapView.getMap().addInputListener(this);
        mapKit = MapKitFactory.getInstance();
        /*USER*/
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(false);
        userLocationLayer.setObjectListener(this);
        binding.backToMainMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mapAddress_to_map);
                Navigation.findNavController(localRepo.afterAddressView).navigate(R.id.action_afterAddress_to_booking_adress);
            }
        });
        binding.location2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        /*SEARCH*/
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED);
        mapView.getMap().addCameraListener(this);
        searchEdit = binding.searchEdit;
        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    submitQuery(searchEdit.getText().toString());
                }
                return false;
            }
        });
        submitQuery(searchEdit.getText().toString());
    }

    @Override
    public void onMapTap(@NonNull Map map, @NonNull Point point) {

    }

    @Override
    public void onMapLongTap(@NonNull Map map, @NonNull Point point) {

    }

    @Override
    public void onObjectAdded(@NonNull UserLocationView userLocationView) {
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
    public void onObjectRemoved(@NonNull UserLocationView userLocationView) {

    }

    @Override
    public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent objectEvent) {

    }
    @Override
    public void onSearchResponse(Response response) {
        mapObjects = mapView.getMap().getMapObjects();
        localRepo.mapObjects = mapObjects;
        /*if(localRepo.addressRoute != null) {
            mapObjects.clear();
            localRepo.addressRoute = null;
        }*/
        //mapObjects.clear();

        for (GeoObjectCollection.Item searchResult : response.getCollection().getChildren()) {
            Point resultLocation = searchResult.getObj().getGeometry().get(0).getPoint();
            if (resultLocation != null) {
                PlacemarkMapObject res = mapObjects.addPlacemark(
                        resultLocation,
                        ImageProvider.fromResource(requireContext(), R.drawable.location_pin_32));
                res.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                        //Toast.makeText(requireContext(), "PIN", Toast.LENGTH_SHORT).show();
                        double route = submitRequest(point, localRepo.currCar);
                        Point center = new Point((point.getLatitude()+localRepo.currCar.getLatitude() - 0.025)/2.0d,(point.getLongitude()+localRepo.currCar.getLongitude())/2.0d);
                        float zoom;
                        if (route <5000)
                            zoom = 12;
                        else if( 5000 <= route && route<= 10000)
                            zoom = 10;
                        else zoom = 8;
                        mapView.getMap().move(new CameraPosition(center, zoom, 0, 0), new Animation(Animation.Type.SMOOTH, 1), null );
                        submitRequest(point, localRepo.currCar);
                        /*try {
                            Navigation.findNavController(localRepo.booking_adress).navigate(R.id.action_booking_adress_to_afterAddress);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }*/
                        localRepo.bottomSheetDialog.show();
                        int price = (int) (100 * localRepo.route / 1000);
                        Toast.makeText(requireContext(), "Примерная стоимость " + price + "₽", Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
            }
        }
    }
    @Override
    public void onSearchError(Error error) {
        String errorMessage = "Unknown error";
        if (error instanceof RemoteError) {
            errorMessage = "Remote server error";
        } else if (error instanceof NetworkError) {
            errorMessage = "Network error";
        }

        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCameraPositionChanged(
            Map map,
            CameraPosition cameraPosition,
            CameraUpdateReason cameraUpdateReason,
            boolean finished) {
        if (finished) {
            submitQuery(searchEdit.getText().toString());
        }
    }
    @Override
    public void onDrivingRoutesError(Error error) {
        String errorMessage = "unknown_error_message";
        if (error instanceof RemoteError) {
            errorMessage = "remote_error_message";
        } else if (error instanceof NetworkError) {
            errorMessage = "network_error_message";
        }

        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDrivingRoutes(List<DrivingRoute> routes) {
        //for (DrivingRoute route : routes) {
        PolylineMapObject route = mapObjects.addPolyline(routes.get(0).getGeometry());
        Repo.addressRoute = route;
        routes.get(0);
        //}
    }
    private double submitRequest(Point ROUTE_START_LOCATION, Point ROUTE_END_LOCATION) {
        double route = Geo.distance(ROUTE_START_LOCATION, ROUTE_END_LOCATION);
        localRepo.route = route;
        //Toast.makeText(requireContext(), route + " ", Toast.LENGTH_LONG).show();

        DrivingOptions drivingOptions = new DrivingOptions();
        VehicleOptions vehicleOptions = new VehicleOptions();
        ArrayList<RequestPoint> requestPoints = new ArrayList<>();
        requestPoints.add(new RequestPoint(
                ROUTE_START_LOCATION,
                RequestPointType.WAYPOINT,
                null));
        requestPoints.add(new RequestPoint(
                ROUTE_END_LOCATION,
                RequestPointType.WAYPOINT,
                null));
        drivingSession = drivingRouter.requestRoutes(requestPoints, drivingOptions, vehicleOptions, this);
        return route;
    }
}
