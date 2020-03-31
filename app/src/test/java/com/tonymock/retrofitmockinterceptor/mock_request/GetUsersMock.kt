package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.GetRequestMock
import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.mock_response.GetUserSuccessMock
import java.util.regex.Pattern

class GetUsersMock : GetRequestMock {
    override fun response(): MockResponse {
        return GetUserSuccessMock()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/users/me"
        return Pattern.compile(urlRegex)
    }
}