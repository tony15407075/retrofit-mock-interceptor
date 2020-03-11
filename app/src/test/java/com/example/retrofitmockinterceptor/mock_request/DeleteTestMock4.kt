package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.DeleteRequestMock
import com.example.retrofitmockinterceptor.mock_response.DeleteTestMock4Success
import java.util.regex.Pattern

class DeleteTestMock4: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val uniquePath = "%specific%"
        val uniqueId = "-1"

        val urlRegex = "https://mock.later.com/api/v2/$uniquePath/delete[?]id=$uniqueId"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock4Success()
}