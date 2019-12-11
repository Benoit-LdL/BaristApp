package com.example.baristapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baristapp.Callback.IRecyclerClickListener;
import com.example.baristapp.Common.Common;
import com.example.baristapp.EventBus.DrinkItemClick;
import com.example.baristapp.Model.DrinkModel;
import com.example.baristapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyDrinkListAdapter extends RecyclerView.Adapter<MyDrinkListAdapter.MyViewHolder>{
    Context context;
    List<DrinkModel> drinkModelList;

    public MyDrinkListAdapter(Context context, List<DrinkModel> drinkModelList) {
        this.context = context;
        this.drinkModelList = drinkModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_drink_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(drinkModelList.get(position).getImage()).into(holder.img_drink_image);
        holder.txt_drink_name.setText(new StringBuilder("").append(drinkModelList.get(position).getName()));

        holder.setListener((view, pos) -> {
            Common.selectedDrink = drinkModelList.get(pos);
            EventBus.getDefault().postSticky(new DrinkItemClick(true, drinkModelList.get(pos)));
        });
    }

    @Override
    public int getItemCount() {
        return drinkModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Unbinder unbinder;
        @BindView(R.id.txt_drink_name)
        TextView txt_drink_name;
        @BindView(R.id.img_drink_image)
        ImageView img_drink_image;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClickListener(view,getAdapterPosition());
        }
    }

}
