package com.seminolestate.mypokedex

import com.seminolestate.mypokedex.data.PokemonData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    fun getData(@Url url: String): Call<PokemonData>
}