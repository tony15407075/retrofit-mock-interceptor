package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class PostLabelMockSuccess : MockResponse {
    override fun fileResId() = R.raw.label
    override fun statusCode() = 200
}