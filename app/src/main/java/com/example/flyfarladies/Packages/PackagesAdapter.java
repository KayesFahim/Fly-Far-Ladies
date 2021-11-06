package com.example.flyfarladies.Packages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.flyfarladies.Booking.BookingDetailsActivity;
import com.example.flyfarladies.R;
import java.util.ArrayList;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.MyViewHolder> {

    Context context;
    ArrayList<PackagesModel> model;

    public PackagesAdapter(Context c, ArrayList<PackagesModel> m) {
        context = c;
        model = m;
    }

    @NonNull
    @Override
    public PackagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.packages_view, parent, false));
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final PackagesAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int pos) {
        holder.title.setText(model.get(pos).getPkName());
        holder.cost.setText(model.get(pos).getPkCost());
        holder.decription.setText(model.get(pos).getPkDetails());
        holder.duration.setText(model.get(pos).getPkDuration());

        String imgUri = model.get(pos).getPkImage();

        ImageView imageView = holder.imgurl;
        Glide.with(context)
                .load(imgUri)
                .into(imageView);


        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), BookingDetailsActivity.class);
                view.getContext().startActivity(i);


            }
        });
    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView title;
        TextView cost;
        TextView decription;
        TextView duration;
        ImageView imgurl;
        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.placename);
            cost = itemView.findViewById(R.id.cost);
            decription = itemView.findViewById(R.id.tourdetails);
            duration = itemView.findViewById(R.id.duration);
            imgurl = itemView.findViewById(R.id.placeimg);


        }

    }
}
