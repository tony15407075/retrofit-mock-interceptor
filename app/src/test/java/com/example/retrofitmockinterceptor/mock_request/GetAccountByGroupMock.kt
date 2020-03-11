package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.GetRequestMock
import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.mock_response.GetAccountMockSuccess
import java.util.regex.Pattern

class GetAccountByGroupMock : GetRequestMock {
    override fun response(): MockResponse {
        return GetAccountMockSuccess()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://staging.later.com/api/v2/users/[0-9]+/accounts[?]group=[0-9]+"
        return Pattern.compile(urlRegex)
    }
}