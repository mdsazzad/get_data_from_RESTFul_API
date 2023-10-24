package com.example.task_02;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterApi {
    @GET("api/characters")
    Call<List<Character>> getCharacters();
}
