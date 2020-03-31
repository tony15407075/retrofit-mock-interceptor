package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class PutTestMock3Success: MockResponse {
    override fun statusCode(): Int {
        return 200
    }

    override fun fileResId(): Int {
        return R.raw.test_mock_put_success_3
    }
}