package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.DeleteRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.DeleteTestMock5Success
import java.util.regex.Pattern

class DeleteTestMock5: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val uniquePath = "%specific%"
        val uniqueId = "-2"

        val urlRegex = "https://mock.com/mockapi/$uniquePath/delete[?]id=$uniqueId"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock5Success()
}