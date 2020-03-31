package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PostRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PostLabelMockSuccess
import java.util.regex.Pattern

class PostLabelMockMatcher : PostRequestMock {

    override fun response(): MockResponse {
        return PostLabelMockSuccess()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/labels"
        return Pattern.compile(urlRegex)
    }
}