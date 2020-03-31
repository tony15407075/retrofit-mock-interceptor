package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class PostLabelSpecificMockSuccess : MockResponse {
    override fun fileResId() = R.raw.label_specific
    override fun statusCode() = 200
}