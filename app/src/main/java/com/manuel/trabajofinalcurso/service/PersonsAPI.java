package com.manuel.trabajofinalcurso.service;

import com.manuel.trabajofinalcurso.persons_list.model.pojo.FakePerson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by manuel on 10/18/16.
 */
public interface PersonsAPI {

        //URL base de nuestra API
        String ENDPOINT = "http://uinames.com/";

        @GET("api/?ext&")
        Call<List<FakePerson>> getPersons(@Query("amount") int number);

}
