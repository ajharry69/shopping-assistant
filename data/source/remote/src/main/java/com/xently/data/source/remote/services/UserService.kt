package com.xently.data.source.remote.services

import com.xently.models.Token
import com.xently.models.User
import com.xently.models.UserWithPassword
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    /**
     * Will use Basic authentication scheme for signing in
     */
    @POST("accounts/signin/")
    suspend fun signIn(@Header("Authorization") basicAuth: String): Response<User>

    @POST("accounts/signup/")
    suspend fun signUp(@Body user: UserWithPassword): Response<User>

    @Multipart
    @POST("accounts/signup/")
    suspend fun signUp(
        @Part user: MultipartBody.Part,
        @Part photo: MultipartBody.Part,
    ): Response<User>

    @POST("accounts/signout/")
    suspend fun signOut(): Response<Unit>

    @POST("accounts/profile/{username}/")
    suspend fun updateProfile(
        @Body user: User,
        @Path("username") username: String = user.username,
    ): Response<User>

    @Multipart
    @POST("accounts/profile/{username}/")
    suspend fun updateProfile(
        @Path("username") username: String,
        @Part("user") user: MultipartBody.Part,
        @Part("photo") photo: MultipartBody.Part,
    ): Response<User>

    @FormUrlEncoded
    @POST("accounts/password/change/")
    suspend fun changePassword(
        @Field("old_password") oldPassword: String,
        @Field("new_password") newPassword: String,
    ): Response<User?>

    @FormUrlEncoded
    @POST("accounts/password/reset/confirm/")
    suspend fun resetPassword(
        @Field("temporary_password") temporaryPassword: String,
        @Field("new_password") newPassword: String,
    ): Response<User>

    @FormUrlEncoded
    @POST("accounts/password/reset/request/")
    suspend fun resetPasswordRequest(@Field("email") email: String): Response<Token>

    @FormUrlEncoded
    @POST("accounts/verification/confirm/")
    suspend fun verifyAccount(@Field("code") verificationCode: String): Response<User>

    @POST("accounts/verification/request/")
    suspend fun requestAccountVerification(): Response<Token>

    @POST("accounts/profile/{username}/photo/")
    suspend fun uploadPhoto(
        @Path("username") username: String,
        @Part photo: MultipartBody.Part,
    ): Response<User?>
}