package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PutRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PutTestMock1Success
import java.util.regex.Pattern

class PutTestMock1 : PutRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.com/put/mock/[0-9]+")
    }

    override fun response(): MockResponse {
        return PutTestMock1Success()
    }
}