package com.example.retrofitmockinterceptor.mock_response

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.R

class DeleteTestMock3Success: MockResponse {
    override fun statusCode(): Int {
        return 200
    }

    override fun fileResId(): Int {
        return R.raw.test_mock_delete_success_3
    }
}