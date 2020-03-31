package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PutRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PutTestMock4Success
import com.tonymock.retrofitmockinterceptor.model.TestMockRequest
import com.google.gson.Gson
import java.util.regex.Pattern

class PutTestMock4 : PutRequestMock {
    override fun customMatcher(requestBody: String): Boolean? {
        val requestObj = Gson().fromJson(requestBody, TestMockRequest::class.java)
        return (requestObj.title == "rambo") && (requestObj.id == 4) && (requestObj.country == "Japan")
    }

    override fun urlPattern(): Pattern {
        val uniqueId = -2
        return Pattern.compile("https://mock.com/put/mock/$uniqueId")
    }

    override fun response(): MockResponse {
        return PutTestMock4Success()
    }
}