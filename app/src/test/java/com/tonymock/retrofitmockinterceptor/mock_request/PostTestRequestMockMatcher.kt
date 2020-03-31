package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PostRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PostTestRequestMockSuccess
import java.util.regex.Pattern

class PostTestRequestMockMatcher : PostRequestMock {
    override fun urlPattern(): Pattern {
        return Pattern.compile("https://random.url.com/api/mock/test")
    }

    override fun response(): MockResponse {
        return PostTestRequestMockSuccess()
    }
}