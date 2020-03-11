package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PutRequestMock
import com.example.retrofitmockinterceptor.mock_response.PutTestMock1Success
import java.util.regex.Pattern

class PutTestMock1 : PutRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.later.com/put/mock/[0-9]+")
    }

    override fun response(): MockResponse {
        return PutTestMock1Success()
    }
}