package com.example.layouts.view;

import static com.yandex.runtime.Runtime.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.layouts.R;

import com.example.layouts.databinding.BottomMainBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_main extends BottomSheetDialog {
    BottomMainBinding binding;
    @SuppressLint("StaticFieldLeak")
    public static Repo repo = new Repo();
    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    protected Bottom_main(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public Bottom_main(@NonNull Context context) {
        super(context);
        //binding = BottomMainBinding.inflate();
        //View bottomSheetView = LayoutInflater.from(getApplicationContext()).
        //        inflate(R.layout.booking, findViewById(R.id.booking_layout));

        //findViewById(R.id.buttonAdress).setOnClickListener(AdressListener);
        //getLayoutInflater().inflate(R.layout.booking, , false);
    }
    /*@Nullable*/
    /*@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        super.onCreateView(inflater, container, savedInstanceState);
         mapView = (MapView) getView().findViewById(R.id.mapview);

        return inflater.inflate(R.layout.booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                inflate(R.layout.booking, getActivity().findViewById(R.id.booking_layout));
        //bottomSheetDialog.setContentView(bottomSheetView);
        //bookingBinding.buttonAdress.setOnClickListener(AdressListener);
        bottomSheetDialog.show();
        //bottomSheetDialog.findViewById(R.id.buttonAdress).setOnClickListener(AdressListener);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.bottom_main);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.booking, true).build();
    }

    private View.OnClickListener AdressListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast toast = Toast.makeText(getContext(), "Adress", Toast.LENGTH_SHORT);
            toast.show();
            /*BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.AppBottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                    inflate(R.layout.booking_adress, findViewById(R.id.booking_adress_layout));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.setDismissWithAnimation(false);
            bottomSheetDialog.show();*/
            NavController navController = Navigation.findNavController(findViewById(R.id.BOTTOM));
            navController.navigate(R.id.action_booking_to_booking_adress);
            //Navigation.findNavController(view).navigate(R.id.action_booking_to_booking_adress);
        }
    };

    /*public BottomSheetDialog getBottomSheetDialog() {
        return bottomSheetDialog;
    }*/

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
        //if(repo.mapObjects != null && repo.addressRoute != null)
            //repo.mapObjects.remove(repo.addressRoute);
        if(repo.addressRoute != null) {
            repo.mapObjects.clear();
            repo.addressRoute  = null;
        }
    }

    @Nullable
    @Override
    public View getCurrentFocus() {
        return super.getCurrentFocus();
    }
}
/*public class Bottom_main extends Fragment {
    BottomSheetDialog bottomSheetDialog;

    *//*@NonNull
    @Override

    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }*//*

    *//*protected Bottom(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }*//*

    *//*public Bottom(@NonNull Context context) {
        super(context);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                inflate(R.layout.booking, findViewById(R.id.booking_layout));
        setContentView(bottomSheetView);
        findViewById(R.id.buttonAdress).setOnClickListener(AdressListener);
        getLayoutInflater().inflate(R.layout.booking, )
    }*//*

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        super.onCreateView(inflater, container, savedInstanceState);
        //mapView = (MapView) getView().findViewById(R.id.mapview);
        getBottomSheetDialog().setContentView(getActivity().findViewById(R.layout.booking));
        return inflater.inflate(R.layout.booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

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

    public BottomSheetDialog getBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(getApplicationContext());
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).
                inflate(R.layout.booking, getActivity().findViewById(R.id.booking_layout));
        bottomSheetDialog.setContentView(bottomSheetView);
        //bookingBinding.buttonAdress.setOnClickListener(AdressListener);
        //bottomSheetDialog.show();
        bottomSheetDialog.findViewById(R.id.buttonAdress).setOnClickListener(AdressListener);
        return bottomSheetDialog;
    }


}*/
