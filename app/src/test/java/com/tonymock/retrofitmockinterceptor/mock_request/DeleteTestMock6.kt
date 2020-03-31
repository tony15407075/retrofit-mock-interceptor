package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.DeleteRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.DeleteTestMock6Success
import java.util.regex.Pattern

class DeleteTestMock6: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val uniquePath = "%specific%"
        val uniqueId = "-2"

        val urlRegex = "https://mock.com/mockapi/$uniquePath/delete[?]id=$uniqueId&country=[a-zA-Z]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock6Success()
}