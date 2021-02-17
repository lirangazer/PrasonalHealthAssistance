package com.example.prasonalhealthassistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<RecyclerUtils> recyclerUtils;

    public RecyclerAdapter(Context context, List recyclerUtils) {
        this.context = context;
        this.recyclerUtils = recyclerUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.signle_list_iten, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(recyclerUtils.get(position));
        RecyclerUtils healthProperties = recyclerUtils.get(position);

        holder.title.setText(healthProperties.getTitleName());
        holder.description.setText(healthProperties.getDescription());
        holder.imageProfile.setImageResource(healthProperties.getImageProfile());
    }

    @Override
    public int getItemCount() {
        return recyclerUtils.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView description;
        public ImageView imageProfile;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.Nametxt);
            description =  itemView.findViewById(R.id.Descriptiontxt);
            imageProfile = itemView.findViewById(R.id.userImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RecyclerUtils ru = (RecyclerUtils) view.getTag();
                    //Toast.makeText(view.getContext(), ru.getTitleName()+" is "+ ru.getDescription(), Toast.LENGTH_SHORT).show();


                }
            });

        }
    }

}