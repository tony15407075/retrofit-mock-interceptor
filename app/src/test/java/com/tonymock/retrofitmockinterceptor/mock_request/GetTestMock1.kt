package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.GetRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.GetTestMock1Success
import java.util.regex.Pattern

class GetTestMock1: GetRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.com/mockapi/[a-zA-Z0-9_]+/query[?]name=[a-zA-Z0-9]+&id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = GetTestMock1Success()
}