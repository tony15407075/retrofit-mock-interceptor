package com.example.retrofitmockinterceptor.mock_response

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.R

class PostTestMock5Success : MockResponse {
    override fun statusCode(): Int {
        return 223
    }

    override fun fileResId(): Int {
        return R.raw.test_mock_response
    }
}