package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class GetUserSuccessMock : MockResponse {
    override fun fileResId() = R.raw.user
    override fun statusCode() = 200
}