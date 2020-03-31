package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class GetAccountMockSuccess : MockResponse {
    override fun fileResId() = R.raw.user_accounts
    override fun statusCode() = 200
}