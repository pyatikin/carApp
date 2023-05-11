package com.example.layouts.view;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.layouts.databinding.BookingAfterAdressBinding;
import com.example.layouts.databinding.BottomMainBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PolylineMapObject;

import java.util.Date;

public class Repo {
    @SuppressLint("StaticFieldLeak")
    public static BottomSheetDialog bottomSheetDialog;
    @SuppressLint("StaticFieldLeak")
    public static View mainMapView;
    public static Point currCar;
    public static PolylineMapObject addressRoute = null;
    public static MapObjectCollection mapObjects = null;
    public static View afterAddressView;
    public static View mapAddress;
    public static double route;
    public static View booking_adress;
    public static Date startTime;
    public  static Date endTime;
    public static MapObject carCircle;
    public Repo() {
    }
    public static BottomSheetDialog getBottomSheetDialog() {
        return bottomSheetDialog;
    }

    public void setBottomSheetDialog(BottomSheetDialog bottomSheetDialog) {
        Repo.bottomSheetDialog = bottomSheetDialog;
    }
}
