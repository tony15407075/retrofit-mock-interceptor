package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.GetRequestMock
import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.mock_response.GetAccountMockSuccess
import java.util.regex.Pattern

class GetAccountByGroupMock : GetRequestMock {
    override fun response(): MockResponse {
        return GetAccountMockSuccess()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/users/[0-9]+/accounts[?]group=[0-9]+"
        return Pattern.compile(urlRegex)
    }
}