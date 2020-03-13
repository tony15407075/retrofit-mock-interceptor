package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PostRequestMock
import com.example.retrofitmockinterceptor.mock_response.PostTestRequestMockSuccess
import java.util.regex.Pattern

class PostTestRequestMockMatcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://random.url.com/api/mock/test")
    }

    override fun response(): MockResponse {
        return PostTestRequestMockSuccess()
    }
}