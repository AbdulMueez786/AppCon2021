package com.example.appcon_2021.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcon_2021.Activity.GoalDetail;
import com.example.appcon_2021.Model.goal;
import com.example.appcon_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyGoals_adapter  extends RecyclerView.Adapter<MyGoals_adapter.MyViewHolder> implements Filterable {
    List<goal> report_ls;
    private List<goal> report_ls_copy;
    Context c;

    public MyGoals_adapter(List<goal> ls, Context c) {
        this.c = c;
        this.report_ls = ls;
        this.report_ls_copy = new ArrayList<>(ls);//copy of our main list
    }

    @NonNull
    @Override
    public MyGoals_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(c).inflate(R.layout.my_goal_row, parent, false);
        return new MyGoals_adapter.MyViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyGoals_adapter.MyViewHolder holder, final int position) {

        holder.title.setText(report_ls.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(c, GoalDetail.class);
                //intent.putExtra("userid", u.getId());
                //intent.putExtra("profile", u.getUser_profile());
                c.startActivity(intent);
            }
        });
        /*
        FirebaseDatabase.getInstance().
                getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot1) {
                for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()) {
                    goal u = snapshot1.getValue(goal.class);
                    //if (u.getId().matches(report_ls.get(position).getCriminal_id())) {
                        holder.title.setText(u.getTitle());
                    //}
                    //if (u.getId().matches(report_ls.get(position).getVictim_id())) {
                    //    holder.reported_by.setText(u.getName());
                   // }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
*/
        //Picasso.get().load(report_ls.get(position).getProfile())
        //        .into(holder.profile);
        //holder.report_message.setText(report_ls.get(position).getDetail());


    }

    @Override
    public int getItemCount() {
        return report_ls.size();
    }

    public void addlist() {
        report_ls_copy = new ArrayList<goal>(report_ls);
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<goal> filteredList = new ArrayList<>();

            System.out.println("Hjhjfhjsdhjsfdh" + constraint);

            if (constraint.equals(null) || constraint.length() == 0) {
                filteredList.addAll(report_ls_copy);
                System.out.println("Working_______________");
                System.out.println(report_ls_copy);
            } else {
                System.out.println("Else Block");
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (goal item : report_ls_copy) {
                    System.out.println(filterPattern);
                    System.out.println("");
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            report_ls.clear();
            System.out.println("Result");
            report_ls.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profile;
        private TextView title, report_message, reported_by;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //profile = itemView.findViewById(R.id.profile);
            title = itemView.findViewById(R.id.title);
            //report_message = itemView.findViewById(R.id.report_message);
            //reported_by = itemView.findViewById(R.id.reported_by);
        }
    }
}


