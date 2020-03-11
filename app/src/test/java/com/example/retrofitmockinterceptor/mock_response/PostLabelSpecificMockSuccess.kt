package com.example.retrofitmockinterceptor.mock_response

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.R

class PostLabelSpecificMockSuccess : MockResponse {
    override fun fileResId() = R.raw.label_specific
    override fun statusCode() = 200
}