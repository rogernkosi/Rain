package com.rogernkosi.rainassessment.networking;

import com.rogernkosi.rainassessment.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
