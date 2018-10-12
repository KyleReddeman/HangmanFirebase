package com.kylereddeman.hangmanfirebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GamesRecyclerAdapter extends RecyclerView.Adapter<GamesRecyclerAdapter.MyViewHolder> {
    private List<Game> games;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View.OnClickListener  onClickListener;
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            v.setOnClickListener(this);
            mTextView = v;
        }
        public void setOnClickListener(View.OnClickListener itemClickListener) {
            this.onClickListener =itemClickListener;
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }

    public GamesRecyclerAdapter(List<Game> games, Context context) {
        this.games = games;
        this.context = context;
    }

    @Override
    public GamesRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.games_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Game game = games.get(position);
        String text = game.getBoard() + " Created by: " + game.getCreator();
        holder.mTextView.setText(text);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                Constants.dataManager.setGame(game);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }
}
