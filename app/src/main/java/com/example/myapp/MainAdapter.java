package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private final ArrayList<Posts> posts;
    private Context context;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_userId, tv_id, tv_title, tv_body;
        public CardView cardView;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_userId =itemView.findViewById(R.id.userId);
            tv_id = itemView.findViewById(R.id.id);
            tv_title = itemView.findViewById(R.id.title);
            tv_body = itemView.findViewById(R.id.body);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
public MainAdapter(Context context, ArrayList <Posts> posts){
        this.context = context;
        this.posts = posts;
}
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
    final Posts post = posts.get(position);
    holder.tv_userId.setText(posts.get(position).getUser_id());
    holder.tv_id.setText(posts.get(position).getId());
    holder.tv_title.setText(posts.get(position).getTitle());
    holder.tv_body.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
