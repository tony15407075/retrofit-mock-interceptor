package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.DeleteRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.DeleteTestMock4Success
import java.util.regex.Pattern

class DeleteTestMock4: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val uniquePath = "%specific%"
        val uniqueId = "-1"

        val urlRegex = "https://mock.com/mockapi/$uniquePath/delete[?]id=$uniqueId"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock4Success()
}