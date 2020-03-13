package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.mock_response.GetAccountMockSuccess
import java.util.regex.Pattern

class GetAccountMock : GetRequestMock {
    override fun response() = GetAccountMockSuccess()
    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/api/v2/users/[0-9]+/accounts"
        return Pattern.compile(urlRegex)
    }
}