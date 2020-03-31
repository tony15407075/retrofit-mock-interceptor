package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PutRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PutTestMock2Success
import com.tonymock.retrofitmockinterceptor.model.TestMockRequest
import com.google.gson.Gson
import java.util.regex.Pattern

class PutTestMock2 : PutRequestMock {

    override fun customMatcher(requestBody: String): Boolean? {
        val requestObj = Gson().fromJson(requestBody, TestMockRequest::class.java)
        return (requestObj.id == 123) && (requestObj.title == "custom-title")
    }

    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.com/put/mock/[0-9]+")
    }

    override fun response(): MockResponse {
        return PutTestMock2Success()
    }
}