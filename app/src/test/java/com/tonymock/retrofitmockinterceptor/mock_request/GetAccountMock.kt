package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.GetRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.GetAccountMockSuccess
import java.util.regex.Pattern

class GetAccountMock : GetRequestMock {
    override fun response() = GetAccountMockSuccess()
    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/users/[0-9]+/accounts"
        return Pattern.compile(urlRegex)
    }
}