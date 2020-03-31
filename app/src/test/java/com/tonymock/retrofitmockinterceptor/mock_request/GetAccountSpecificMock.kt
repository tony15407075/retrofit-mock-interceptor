package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.GetRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.GetAccountSpecificMockSuccess
import java.util.regex.Pattern

class GetAccountSpecificMock : GetRequestMock {
    override fun response() = GetAccountSpecificMockSuccess()
    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/users/123/accounts"
        return Pattern.compile(urlRegex)
    }
}