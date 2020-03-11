package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PostRequestMock
import com.example.retrofitmockinterceptor.mock_response.PostTestMock5Success
import java.util.regex.Pattern

class PostTestMock5Matcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.later.com/api/mock/-23")
    }

    override fun response(): MockResponse {
        return PostTestMock5Success()
    }
}