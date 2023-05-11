package com.example.layouts.adapters;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.MainActivity;
import com.example.layouts.Models.Car;
import com.example.layouts.Models.Card;
import com.example.layouts.R;
import com.example.layouts.ViewModel.CarsViewModel;
import com.example.layouts.databinding.ListItemCarBinding;
import com.example.layouts.databinding.ListItemCardBinding;
import com.example.layouts.view.Bottom_main;
import com.example.layouts.view.Cards;
import com.example.layouts.view.History;
import com.example.layouts.view.Repo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    public ListItemCarBinding binding;
    private CarsViewModel carsViewModel;
    private final List<Car> cars;
    Repo localRepo = Bottom_main.repo;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView date;
        final TextView price;
        final TextView model;
        final TextView time;

        //final ImageButton delete_card;
        ViewHolder(ListItemCarBinding view){
            super(view.getRoot());
            date = view.textCardItem;
            price = view.textCardItem4;
            model = view.textCardItem2;
            time = view.textCardItem5;
            //delete_card = view.imageButtonCardDelete;
        }

        /*public ImageButton getDelete_card(){
            return delete_card;
        }*/

        @Override
        public void onClick(View view) {

        }
    }
    public CarAdapter(History context, List<Car> cars) {
        this.cars = cars;
        carsViewModel = MainActivity.getCarsViewModel();
    }
    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ListItemCarBinding ceb = ListItemCarBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
        return new CarAdapter.ViewHolder(ceb);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = cars.get(position);

        holder.model.setText(car.getNumber());
        holder.model.setTypeface(null, Typeface.BOLD);
        Date currDate = new Date();
        String str = String.format("%te %<TB %<tY", currDate);
        holder.date.setText(str);
        holder.date.setTypeface(null, Typeface.BOLD);
        double minutes = (car.getEnd().getTime() - car.getStart().getTime())/6000;
        holder.time.setText(""+minutes);
        int price = (int) (minutes * 15);
        holder.price.setText(price + " ₽");
        /*holder.getDelete_card().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewModel.giveCard(card.getNumber_card());
                cardViewModel.DeleteCard();
                Toast.makeText(view.getContext(), "Карта удалена", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_cards_self);
            }
        });*/
    }
    @Override
    public int getItemCount() {
        if (cars == null) {
            return 0;
        }
        return cars.size();
    }
}
