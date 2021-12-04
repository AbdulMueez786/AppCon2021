package com.example.appcon_2021.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcon_2021.Model.user;
import com.example.appcon_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopDonors_adapter extends RecyclerView.Adapter<TopDonors_adapter.ViewHolder> {

    private Context c;
    private List<user> ls;

    public TopDonors_adapter(Context c, List<user> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.top_donors_row, parent, false);
        return new TopDonors_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.donor_name.setText(ls.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(c, Chat.class);
                //intent.putExtra("userid", u.getId());
                //intent.putExtra("profile", u.getUser_profile());
                //c.startActivity(intent);
            }
        });

/*
        holder.username.setText(ls.get(position).getName());
        holder.lastmessage.setText(ls.get(position).getPost());
        final user u = ls.get(position);

        FirebaseDatabase.getInstance().getReference("UserStatus")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.child(u.getId()).getValue(String.class);

                        System.out.println(status);
                        if (status != null) {
                            if (status.matches("online")) {
                                holder.offline_status.setVisibility(View.GONE);
                                holder.online_status.setVisibility(View.VISIBLE);
                            } else {
                                holder.offline_status.setVisibility(View.VISIBLE);
                                holder.online_status.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        if (u.getUser_profile().equals("default") != true) {
            Picasso.get().load(u.getUser_profile())
                    .into(holder.profile_image);
        } else {
            Picasso.get().load(R.drawable.users)
                    .into(holder.profile_image);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Chat.class);
                intent.putExtra("userid", u.getId());
                intent.putExtra("profile", u.getUser_profile());
                c.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView donor_name, lastmessage, chat_date;
        public CircleImageView profile_image, online_status, offline_status;
        //public LinearLayout l;
        public RelativeLayout l;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            donor_name=itemView.findViewById(R.id.donor_name);
            l = itemView.findViewById(R.id.l);
            /*username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            online_status = itemView.findViewById(R.id.status_online);
            offline_status = itemView.findViewById(R.id.status_offline);
            lastmessage = itemView.findViewById(R.id.lastmessage);
            chat_date = itemView.findViewById(R.id.chat_date);
          */
        }
    }
}
