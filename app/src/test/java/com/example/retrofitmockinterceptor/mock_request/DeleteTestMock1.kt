package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.DeleteRequestMock
import com.example.retrofitmockinterceptor.mock_response.DeleteTestMock1Success

import java.util.regex.Pattern

class DeleteTestMock1: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.later.com/api/v2/delete[?]id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock1Success()
}