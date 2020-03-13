package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PostRequestMock
import com.example.retrofitmockinterceptor.mock_response.PostLabelMockSuccess
import java.util.regex.Pattern

class PostLabelMockMatcher : PostRequestMock {

    override fun response(): MockResponse {
        return PostLabelMockSuccess()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/api/v2/labels"
        return Pattern.compile(urlRegex)
    }
}