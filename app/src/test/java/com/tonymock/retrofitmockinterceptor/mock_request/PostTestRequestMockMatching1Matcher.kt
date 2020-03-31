package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PostRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PostTestRequestMockMatchingSuccess1
import com.tonymock.retrofitmockinterceptor.model.TestMockRequest
import com.google.gson.Gson
import java.util.regex.Pattern

class PostTestRequestMockMatching1Matcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://random.url.com/api/mock/test")
    }

    override fun response(): MockResponse {
        return PostTestRequestMockMatchingSuccess1()
    }

    override fun customMatcher(requestBody: String): Boolean {
        val requestObj = Gson().fromJson(requestBody, TestMockRequest::class.java)
        return (requestObj.country == "Sweden") && (requestObj.name == "John")
    }
}