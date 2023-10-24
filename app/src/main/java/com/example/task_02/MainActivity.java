package com.example.task_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CharacterApi characterApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CharacterListAdapter adapter = new CharacterListAdapter(characterList);
        recyclerView.setAdapter(adapter);

        characterApi = createCharacterApi();

        // Fetch character data from the API
        Call<List<Character>> call = characterApi.getCharacters();
        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    characterList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for the RecyclerView items to open the detail activity
        adapter.setOnItemClickListener(new CharacterListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Character character = characterList.get(position);
                openDetailActivity(character);
            }
        });
    }

    private CharacterApi createCharacterApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hp-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CharacterApi.class);
    }

    private void openDetailActivity(Character character) {
        Intent intent = new Intent(this, DetailActivity.class);
        // Convert the Character object to a JSON string
        String characterJson = new Gson().toJson(character);

// Pass the JSON string to the next activity
        intent.putExtra("character", characterJson);
        startActivity(intent);
    }
}