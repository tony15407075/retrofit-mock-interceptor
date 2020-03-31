package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.DeleteRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.DeleteTestMock2Success
import java.util.regex.Pattern

class DeleteTestMock2: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.com/mockapi/[a-zA-Z0-9_]+/delete[?]id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock2Success()
}