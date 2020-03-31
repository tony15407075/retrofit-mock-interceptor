package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.GetRequestMock
import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.mock_response.GetAccountMockSuccess
import java.util.regex.Pattern

class GetLabelsByGroupMock : GetRequestMock {
    override fun response(): MockResponse {
        return GetAccountMockSuccess()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/labels[?]group_id=[0-9]+"
        return Pattern.compile(urlRegex)
    }
}