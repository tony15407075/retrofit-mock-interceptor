package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.DeleteRequestMock
import com.example.retrofitmockinterceptor.mock_response.DeleteTestMock3Success
import java.util.regex.Pattern

class DeleteTestMock3: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.later.com/api/v2/%specific%/delete[?]id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock3Success()
}