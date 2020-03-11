package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.mock_response.GetTestMock1Success
import java.util.regex.Pattern

class GetTestMock1: GetRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.later.com/api/v2/[a-zA-Z0-9_]+/query[?]name=[a-zA-Z0-9]+&id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = GetTestMock1Success()
}