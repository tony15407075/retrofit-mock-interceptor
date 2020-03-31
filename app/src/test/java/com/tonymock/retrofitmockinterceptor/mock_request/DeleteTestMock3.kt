package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.DeleteRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.DeleteTestMock3Success
import java.util.regex.Pattern

class DeleteTestMock3: DeleteRequestMock {

    override fun urlPattern(): Pattern {
        val urlRegex = "https://mock.com/mockapi/%specific%/delete[?]id=[0-9]+"
        return Pattern.compile(urlRegex)
    }

    override fun response() = DeleteTestMock3Success()
}