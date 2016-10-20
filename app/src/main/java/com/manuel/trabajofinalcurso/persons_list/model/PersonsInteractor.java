package com.manuel.trabajofinalcurso.persons_list.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manuel.trabajofinalcurso.persons_list.PersonsListMvp;
import com.manuel.trabajofinalcurso.persons_list.model.pojo.FakePerson;
import com.manuel.trabajofinalcurso.service.PersonsAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manuel on 10/18/16.
 */
public class PersonsInteractor implements PersonsListMvp.Interactor {
    @Override
    public void getPersons(int personsNumber, final PersonsListMvp.Presenter.GetPersonCallback callback) {

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PersonsAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PersonsAPI personsAPI = retrofit.create(PersonsAPI.class);
        Call<List<FakePerson>> call = personsAPI.getPersons(personsNumber);
        call.enqueue(new Callback<List<FakePerson>>() {
            @Override
            public void onResponse(Call<List<FakePerson>> call, Response<List<FakePerson>> response) {

                //Llamamos al presenter a traves del callback
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<FakePerson>> call, Throwable t) {

                //Si fallo, le avisamos al presenter a traves del callback
                callback.onError(t.getLocalizedMessage());
            }
        });


    }
}
