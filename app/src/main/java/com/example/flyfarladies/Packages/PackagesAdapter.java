package com.example.flyfarladies.Packages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.flyfarladies.Booking.BookingDetailsActivity;
import com.example.flyfarladies.Booking.BookingPageActivity;
import com.example.flyfarladies.R;
import java.util.ArrayList;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<PackagesModel> model;
    ArrayList<PackagesModel> filterList;

    public PackagesAdapter(Context c, ArrayList<PackagesModel> m) {
        context = c;
        model = m;
        this.filterList = model;
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
        holder.cost.setText(model.get(pos).getPkCost() +"৳");
        holder.decription.setText(model.get(pos).getPkDetails());
        holder.duration.setText(model.get(pos).getPkDuration());

        String imgUri = model.get(pos).getPkImage();

        ImageView imageView = holder.imgurl;
        Glide.with(context)
                .load(imgUri)
                .into(imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), BookingPageActivity.class);
                i.putExtra("id", model.get(pos).getPkId());
                i.putExtra("title", model.get(pos).getPkName());
                i.putExtra("details", model.get(pos).getPkDetails());
                i.putExtra("duration", model.get(pos).getPkDuration());
                i.putExtra("price", model.get(pos).getPkCost());
                i.putExtra("image", model.get(pos).getPkImage());
                view.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return model.size();
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                ArrayList<PackagesModel> arrayListFilter = new ArrayList<>();

                if(constraint == null|| constraint.length() == 0) {
                    results.count = model.size();
                    results.values = model;
                } else {
                    for (PackagesModel itemModel : model) {
                        if(itemModel.getPkName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            arrayListFilter.add(itemModel);
                        }
                    }
                    results.count = arrayListFilter.size();
                    results.values = arrayListFilter;

                }
                return results;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<PackagesModel>) results.values;
                notifyDataSetChanged();

                if(filterList.size() == 0) {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                }

            }
        };
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

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
