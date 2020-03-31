package com.tonymock.retrofitmockinterceptor.mock_request

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.PostRequestMock
import com.tonymock.retrofitmockinterceptor.mock_response.PostLabelSpecificMockSuccess
import com.tonymock.retrofitmockinterceptor.model.AddLabelRequestParam
import com.google.gson.Gson
import java.util.regex.Pattern

class PostLabelMockSpecificMatcher : PostRequestMock {

    override fun customMatcher(requestBody: String): Boolean {
        val requestObj = Gson().fromJson(requestBody, AddLabelRequestParam::class.java)
        return (requestObj.group_id == 233) && requestObj.label.title == "specific"
    }

    override fun response(): MockResponse {
        return PostLabelSpecificMockSuccess()
    }

    override fun urlPattern(): Pattern {
        val urlRegex = "https://random.url.com/mock_api/labels"
        return Pattern.compile(urlRegex)
    }
}