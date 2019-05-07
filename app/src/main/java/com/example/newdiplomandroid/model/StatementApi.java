package com.example.newdiplomandroid.model;

import com.example.newdiplomandroid.model.request.LoginRequest;
import com.example.newdiplomandroid.model.request.RegistrationRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StatementApi {
    String mContentType = "application/json";

    @POST("api/UserAPI/Login")
    Observable<String> requestLogin(@Header("Content-Type") String content,
                                    @Body LoginRequest login);

    @POST("api/UserAPI/registration")
    Observable<String> requestRegistration(@Header("Content-Type") String content,
                                    @Body RegistrationRequest registrationRequest);
}
