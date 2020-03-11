package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.mock_response.GetUserSuccessMock
import java.util.regex.Pattern

class GetUsersMock : GetRequestMock {
    override fun response(): MockResponse {
        return GetUserSuccessMock()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://staging.later.com/api/v2/users/me"
        return Pattern.compile(urlRegex)
    }
}