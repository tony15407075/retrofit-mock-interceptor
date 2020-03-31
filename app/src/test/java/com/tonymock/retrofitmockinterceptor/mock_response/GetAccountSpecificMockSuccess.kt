package com.tonymock.retrofitmockinterceptor.mock_response

import com.tonymock.retrofitmockinterceptor.MockResponse
import com.tonymock.retrofitmockinterceptor.R

class GetAccountSpecificMockSuccess : MockResponse {
    override fun statusCode(): Int {
        return 200
    }

    override fun fileResId(): Int {
        return R.raw.user_accounts_specific
    }
}