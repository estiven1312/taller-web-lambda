package com.lambda.pe.lambdaapp.remote;

import com.lambda.pe.lambdaapp.remote.response.ResponseValidation;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ValidateImageRequest {
    @Multipart
    @POST("predict")
    Call<ResponseValidation> validateImage(@Part MultipartBody.Part file);

}
