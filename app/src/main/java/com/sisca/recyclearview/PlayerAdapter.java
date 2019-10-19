package com.sisca.recyclearview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    Context context;
    ArrayList<Player> players;
    private OnClickListener listener;
    public PlayerAdapter(Context context) {
        this.context = context;
        this.players = new ArrayList<>();

    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_player_item, parent, false);

        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(getPlayers().get(position).getName());
        Picasso.get()
                .load(getPlayers().get(position).getImage())
                .fit()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return getPlayers().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.imgPlayer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.klikView(getAdapterPosition());
                    }
                }
            });
        }


    }
}
