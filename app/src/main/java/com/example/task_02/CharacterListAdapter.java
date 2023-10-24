package com.example.task_02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {
    private List<Character> characterList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CharacterListAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
        return new CharacterViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameTextView.setText(character.getName());
        holder.actorNameTextView.setText(character.getActor());
        holder.houseNameTextView.setText(character.getHouse());

        Picasso.get().load(character.getImage()).into(holder.pictureImageView);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView pictureImageView;
        TextView nameTextView;
        TextView actorNameTextView;
        TextView houseNameTextView;

        public CharacterViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            pictureImageView = itemView.findViewById(R.id.pictureImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            actorNameTextView = itemView.findViewById(R.id.actorNameTextView);
            houseNameTextView = itemView.findViewById(R.id.houseNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
