package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.mock_response.GetAccountSpecificMockSuccess
import java.util.regex.Pattern

class GetAccountSpecificMock : GetRequestMock {
    override fun response() = GetAccountSpecificMockSuccess()
    override fun urlPattern(): Pattern {
        val urlRegex = "https://staging.later.com/api/v2/users/123/accounts"
        return Pattern.compile(urlRegex)
    }
}