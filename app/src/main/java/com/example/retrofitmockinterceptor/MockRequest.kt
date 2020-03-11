package com.example.retrofitmockinterceptor

import java.util.regex.Pattern

interface MockRequestMatcher {
    companion object {
        const val MATCHER_UNDEFINED = 1
        const val MATCH_FOUND = 2
        const val NO_MATCH = 3
    }

    /**
     * Define custom matching rules based on [requestBody]
     *
     * @return - true when custom condition is satisfied - otherwise false.
     */
    fun customMatcher(requestBody: String) : Boolean ?= null

    /**
     * Checks requestBody against [customMatcher]
     *
     * @return
     * [MATCHER_UNDEFINED] - Custom matcher not defined.
     * [MATCH_FOUND] - requestBody satisfies condition
     * [NO_MATCH] - requestBody doesn't satisfies condition
     */
    fun checkCustomMatcher(requestBody: String) : Int {
        val isMatch = customMatcher(requestBody)
        return when {
            isMatch == null -> MATCHER_UNDEFINED
            isMatch -> MATCH_FOUND
            else -> NO_MATCH
        }
    }
}

interface MockRequest {
    /**
     * @return [MockRequest]'s url pattern [Pattern]
     */
    fun urlPattern() : Pattern

    /**
     * @return [MockRequest]'s corresponding [MockResponse]
     */
    fun response() : MockResponse
}

interface GetRequestMock : MockRequest
interface PostRequestMock : MockRequest, MockRequestMatcher
interface PutRequestMock : MockRequest, MockRequestMatcher
interface PatchRequestMock : MockRequest, MockRequestMatcher
interface DeleteRequestMock : MockRequest