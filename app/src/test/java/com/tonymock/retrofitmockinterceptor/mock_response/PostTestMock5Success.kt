package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class PostTestMock5Success : MockResponse {
    override fun statusCode(): Int {
        return 223
    }

    override fun fileResId(): Int {
        return R.raw.test_mock_response
    }
}