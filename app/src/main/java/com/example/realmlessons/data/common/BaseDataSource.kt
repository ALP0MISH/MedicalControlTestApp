package com.example.realmlessons.data.common

import com.example.realmlessons.data.common.model.AnotherError
import com.example.realmlessons.data.common.model.ResultModel
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> invokeRequest(request: suspend () -> Response<T>): ResultModel<T> {
        return try {
            val response = request()
            if (response.isSuccessful) ResultModel.success(response.body())
            else ResultModel.error(
                AnotherError(
                    response.message(),
                    response.code(),
                )
            )
        } catch (
            httpException: HttpException
        ) {
            ResultModel.error(httpException)
        } catch (
            exception: Exception
        ) {
            ResultModel.error(exception)
        }
    }
}