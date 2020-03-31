package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PostRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PostTestMock5Success
import java.util.regex.Pattern

class PostTestMock5Matcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://mock.com/someapi/mock/-23")
    }

    override fun response(): MockResponse {
        return PostTestMock5Success()
    }
}