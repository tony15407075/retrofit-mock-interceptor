package com.example.retrofitmockinterceptor.mock_response

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.R

class PostLabelMockSuccess : MockResponse {
    override fun fileResId() = R.raw.label
    override fun statusCode() = 200
}