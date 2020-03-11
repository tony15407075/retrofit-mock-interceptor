package com.example.retrofitmockinterceptor.mock_response

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.R

class GetUserSuccessMock : MockResponse {
    override fun fileResId() = R.raw.user
    override fun statusCode() = 200
}