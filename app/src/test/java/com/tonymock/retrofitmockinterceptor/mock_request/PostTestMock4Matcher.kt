package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PostRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PostTestMock4Success
import java.util.regex.Pattern

class PostTestMock4Matcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.com/someapi/mock/[0-9]+")
    }

    override fun response(): MockResponse {
        return PostTestMock4Success()
    }
}