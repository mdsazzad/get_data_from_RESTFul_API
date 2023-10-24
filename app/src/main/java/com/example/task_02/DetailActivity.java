package com.example.task_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView detailPictureImageView;
    private TextView detailNameTextView;
    private TextView detailActorNameTextView;
    private TextView detailHouseNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPictureImageView = findViewById(R.id.detailPictureImageView);
        detailNameTextView = findViewById(R.id.detailNameTextView);
        detailActorNameTextView = findViewById(R.id.detailActorNameTextView);
        detailHouseNameTextView = findViewById(R.id.detailHouseNameTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String characterJson = intent.getStringExtra("character");

            if (characterJson != null) {
                // Parse the JSON string back into a Character object
                Character character = new Gson().fromJson(characterJson, Character.class);

                // Display character details in the views
                detailNameTextView.setText("Character Name: "+character.getName());
                detailActorNameTextView.setText("Actor Name: "+character.getActor());
                detailHouseNameTextView.setText("House Name: "+character.getHouse());

                Picasso.get().load(character.getImage()).into(detailPictureImageView);
            }
        }

    }
}
