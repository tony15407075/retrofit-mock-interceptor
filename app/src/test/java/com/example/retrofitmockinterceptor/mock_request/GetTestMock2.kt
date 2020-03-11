package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.mock_response.GetTestMock2Success
import java.util.regex.Pattern

class GetTestMock2: GetRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.later.com/api/v2/specific!123/query[?]name=[a-zA-Z0-9_]+&id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = GetTestMock2Success()
}