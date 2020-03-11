package com.example.retrofitmockinterceptor

import android.content.res.Resources
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException


class MockInterceptor(private val res: Resources, listOfMocks: List<MockRequest> = emptyList()) : Interceptor {

    private val mockMapper = MockRequestMapper(listOfMocks)

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val mockRequest = routeToMock(originalRequest)

        return if (mockRequest == null) {
            chain.proceed(originalRequest)   // Normal flow - proceed as usual
        } else {
            val mockResponse = mockRequest.response()
            val readBytes = res.openRawResource(mockResponse.fileResId()).readBytes()

            return chain.proceed(chain.request())
                .newBuilder()
                .code(mockResponse.statusCode())
                .protocol(Protocol.HTTP_2)
                .body(readBytes.toResponseBody())
                .addHeader("content-type", "application/json")
                .build()

        }
    }

    private fun routeToMock(originalRequest: Request): MockRequest? {
        val requestType = originalRequest.method
        val url = originalRequest.url.toString()
        val requestBodyString = originalRequest.body.asString()

        return mockMapper.map(requestType, url, requestBodyString)
    }

    private fun RequestBody?.asString() : String {
        this ?: return ""

        return try {
            val buffer = Buffer()
            this.writeTo(buffer)

            buffer.readUtf8()
        } catch (e: IOException) {
            ""
        }
    }

}