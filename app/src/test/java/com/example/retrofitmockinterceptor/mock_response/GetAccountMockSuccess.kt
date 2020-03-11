package com.example.retrofitmockinterceptor.mock_response

import com.example.retrofitmockinterceptor.MockResponse
import com.example.retrofitmockinterceptor.R

class GetAccountMockSuccess : MockResponse {
    override fun fileResId() = R.raw.user_accounts
    override fun statusCode() = 200
}