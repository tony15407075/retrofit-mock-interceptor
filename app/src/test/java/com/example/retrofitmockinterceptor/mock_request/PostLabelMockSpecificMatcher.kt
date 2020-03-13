package com.example.retrofitmockinterceptor.mock_request

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.PostRequestMock
import com.example.retrofitmockinterceptor.mock_response.PostLabelSpecificMockSuccess
import com.example.retrofitmockinterceptor.model.AddLabelRequestParam
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
        val urlRegex = "https://random.url.com/api/v2/labels"
        return Pattern.compile(urlRegex)
    }
}