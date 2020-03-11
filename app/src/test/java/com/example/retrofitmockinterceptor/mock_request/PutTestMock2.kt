package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PutRequestMock
import com.example.retrofitmockinterceptor.mock_response.PutTestMock2Success
import com.example.retrofitmockinterceptor.model.TestMockRequest
import com.google.gson.Gson
import java.util.regex.Pattern

class PutTestMock2 : PutRequestMock {

    override fun customMatcher(requestBody: String): Boolean? {
        val requestObj = Gson().fromJson(requestBody, TestMockRequest::class.java)
        return (requestObj.id == 123) && (requestObj.title == "custom-title")
    }

    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.later.com/put/mock/[0-9]+")
    }

    override fun response(): MockResponse {
        return PutTestMock2Success()
    }
}