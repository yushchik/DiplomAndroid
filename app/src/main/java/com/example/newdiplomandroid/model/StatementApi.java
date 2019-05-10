package com.example.newdiplomandroid.model;

import com.example.newdiplomandroid.model.request.LoginRequest;
import com.example.newdiplomandroid.model.request.RegistrationRequest;
import com.example.newdiplomandroid.model.request.ResultQuizzRequest;
import com.example.newdiplomandroid.model.response.AllLessonResponse;
import com.example.newdiplomandroid.model.response.QuizzResponse;
import com.example.newdiplomandroid.model.response.ResultQuizzResponse;

import java.util.List;

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

    @GET("api/LessonAPI/AllLesson")
    Observable<List<AllLessonResponse>> requestAllLesson (@Header("Content-Type") String content);

    @GET("api/QuizzAPI")
    Observable<List<QuizzResponse>> requestQuizz (@Header("Content-Type") String content,
                                                  @Header("lessonId") Integer lessonId);

    @GET("api/LessonAPI/Progress")
    Observable<String> requestProgress (@Header("Content-Type") String content,
                                        @Header("userName") String userName);

    @POST("api/QuizzAPI")
    Observable<List<ResultQuizzResponse>> requestQuizzResult (@Header("Content-Type") String content,
                                                              @Body List<ResultQuizzRequest> resultQuizzRequests);
}
