package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.mock_response.GetTestMock3Success
import java.util.regex.Pattern

class GetTestMock3: GetRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.later.com/api/v2/[a-zA-Z0-9_]+/query[?]name=[a-zA-Z0-9]+&id=12"
        return Pattern.compile(urlRegex)
    }

    override fun response() = GetTestMock3Success()
}