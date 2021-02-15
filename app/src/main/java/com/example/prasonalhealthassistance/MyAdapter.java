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


public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder> {

    private Context context;
    private List<PersonUtils> personUtils;

    public MyAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.signle_list_iten, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));

        PersonUtils pu = personUtils.get(position);

        holder.pName.setText(pu.getPersonName());
        holder.pJobProfile.setText(pu.getJobProfile());
        holder.pImageProfile.setImageResource(pu.getImageProfile());

    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;
        public TextView pJobProfile;
        public ImageView pImageProfile;

        public ViewHolder(View itemView) {
            super(itemView);

            pName = (TextView) itemView.findViewById(R.id.pNametxt);
            pJobProfile = (TextView) itemView.findViewById(R.id.pJobProfiletxt);
            pImageProfile=(ImageView) itemView.findViewById(R.id.userImg);
            //pImageProfile.setImageDrawable(context.getResources().);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PersonUtils cpu = (PersonUtils) view.getTag();
                    Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}