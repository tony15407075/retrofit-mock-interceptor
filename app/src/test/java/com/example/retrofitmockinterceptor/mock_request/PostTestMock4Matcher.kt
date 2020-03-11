package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PostRequestMock
import com.example.retrofitmockinterceptor.mock_response.PostTestMock4Success
import java.util.regex.Pattern

class PostTestMock4Matcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.later.com/api/mock/[0-9]+")
    }

    override fun response(): MockResponse {
        return PostTestMock4Success()
    }
}