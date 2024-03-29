package com.example.prasonalhealthassistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<RecyclerUtils> recyclerUtils;
    //private List<RecyclerUtils> favProducet;
    //DatabaseReference favouriteRef;
    //FirebaseDatabase database=FirebaseDatabase.getInstance();
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
        String userName = SharedPref.read("user", "");
        final boolean[] favFlag = {true};
        if(context.getClass().getName().toLowerCase().equals("com.example.prasonalhealthassistance.userview")){
            holder.favButton.setBackgroundResource(R.drawable.ic_baseline_add_task_green_24);
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference favButton = database.getReference("Users").child(userName).child("Products").child(healthProperties.getTitleName());
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(context.getClass().getName().toLowerCase().equals("com.example.prasonalhealthassistance.fitnessactivity") || context.getClass().getName().toLowerCase().equals("com.example.prasonalhealthassistance.healthactivity")){
                    if(favFlag[0]) {
                        holder.favButton.setBackgroundResource(R.drawable.ic_baseline_add_task_green_24);
                        Toast.makeText(context.getApplicationContext(), "The activity add to your collection", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context.getApplicationContext(), "The activity already exist in you collection ", Toast.LENGTH_SHORT).show();
                    }
                    favButton.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                favButton.setValue(new RecyclerUtils(healthProperties.getTitleName() , healthProperties.getDescription(), healthProperties.getImageProfile()));
                                favFlag[0]=false;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });}
                else if(context.getClass().getName().toLowerCase().equals("com.example.prasonalhealthassistance.userview")){
                    favButton.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            holder.favButton.setBackgroundResource(R.drawable.ic_baseline_add_task_gray_24);
                            Toast.makeText(context.getApplicationContext(), "The activity remove from your collection", Toast.LENGTH_SHORT).show();
                            favButton.removeValue();
                            favFlag[0]=true;

//                            Intent intent = new Intent(this, UserView.class);
//                            context.startActivity(intent);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

//                    favButton.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            String value = snapshot.getValue(String.class);
//                            Toast.makeText(context.getApplicationContext(), "value is "+ value ,Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                   //                else {
//                    holder.favButton.setBackgroundResource(R.drawable.ic_baseline_add_task_gray_24);
//                    Toast.makeText(context.getApplicationContext(), "The activity remove to fav", Toast.LENGTH_SHORT).show();
//                    favFlag[0] =true;
//                }

            }
        });

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
        public Button favButton;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.Nametxt);
            description =  itemView.findViewById(R.id.Descriptiontxt);
            imageProfile = itemView.findViewById(R.id.userImg);
            favButton = itemView.findViewById(R.id.fav);

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