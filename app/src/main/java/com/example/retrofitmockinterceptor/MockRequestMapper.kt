package com.example.retrofitmockinterceptor

import java.util.*

/**
 * Maps retrofit Request -> [MockRequest]
 */
class MockRequestMapper(listOfMocks: List<MockRequest> = emptyList()) {

    private val comparator: (MockRequest, MockRequest) -> Int = { mock1, mock2 ->
        val mock1Url = mock1.urlPattern().toString()
        val mock2Url = mock2.urlPattern().toString()
        mock1Url.compareTo(mock2Url)
    }

    private val getRequestMocks = TreeSet<MockRequest>(comparator)
    private val deleteRequestMocks = TreeSet<MockRequest>(comparator)
    private val putRequestMocks = arrayListOf<PutRequestMock>()
    private val patchRequestMocks = arrayListOf<PatchRequestMock>()
    private val postRequestMocks = arrayListOf<PostRequestMock>()

    init {
        listOfMocks.forEach { mock ->
            when(mock) {
                is GetRequestMock -> getRequestMocks.add(mock)
                is PutRequestMock -> putRequestMocks.add(mock)
                is PostRequestMock -> postRequestMocks.add(mock)
                is DeleteRequestMock -> deleteRequestMocks.add(mock)
                else -> throw IllegalStateException("Invalid MockRequest Type $mock")
            }
        }
    }

    /**
     * Maps a Retrofit request -> [MockRequest]
     */
    fun map(requestType: String, url: String, requestBodyJson: String = "") : MockRequest? {
        return when(requestType.toLowerCase()) {
            "get" -> mapGetMock(url)
            "put" -> mapPutMock(url, requestBodyJson)
            "post" -> mapPostMock(url, requestBodyJson)
            "patch" -> mapPatchMock(url, requestBodyJson)
            "delete" -> mapDeleteMock(url)
            else -> null
        }
    }


    private fun mapGetMock(url: String): MockRequest? {
        return getRequestMocks.firstOrNull { getRequestMock -> getRequestMock.urlPattern().matcher(url).matches() }
    }

    private fun mapDeleteMock(url: String): MockRequest? {
        return deleteRequestMocks.firstOrNull { deleteRequestMock -> deleteRequestMock.urlPattern().matcher(url).matches() }
    }

    private fun mapPostMock(url: String, requestBodyJson: String): MockRequest? {
        val urlMatchingPostMocks = postRequestMocks.filter { postRequestMock -> postRequestMock.urlPattern().matcher(url).matches() }

        // Mocks with custom define match pattern take precedence
        val customMatchedMock = urlMatchingPostMocks.firstOrNull { postRequestMock -> postRequestMock.checkCustomMatcher(requestBodyJson) == MockRequestMatcher.MATCH_FOUND }
        if (customMatchedMock != null) return customMatchedMock
        return urlMatchingPostMocks.firstOrNull { postRequestMock -> postRequestMock.checkCustomMatcher(requestBodyJson) == MockRequestMatcher.MATCHER_UNDEFINED }
    }

    private fun mapPutMock(url: String, requestBodyJson: String): MockRequest? {
        val urlMatchingPutMocks = putRequestMocks.filter { putRequestMock -> putRequestMock.urlPattern().matcher(url).matches() }

        // Mocks with custom define match pattern take precedence
        val customMatchedMock = urlMatchingPutMocks.firstOrNull { putRequestMock -> putRequestMock.checkCustomMatcher(requestBodyJson) == MockRequestMatcher.MATCH_FOUND }
        if (customMatchedMock != null) return customMatchedMock

        return urlMatchingPutMocks.firstOrNull { putRequestMock -> putRequestMock.checkCustomMatcher(requestBodyJson) == MockRequestMatcher.MATCHER_UNDEFINED }
    }

    private fun mapPatchMock(url: String, requestBodyJson: String) : MockRequest? {
        val urlMatchingPatchMocks = patchRequestMocks.filter { putRequestMock -> putRequestMock.urlPattern().matcher(url).matches() }

        // Mocks with custom define match pattern take precedence
        val customMatchedMock = urlMatchingPatchMocks.firstOrNull { putRequestMock -> putRequestMock.checkCustomMatcher(requestBodyJson) == MockRequestMatcher.MATCH_FOUND }
        if (customMatchedMock != null) return customMatchedMock

        return urlMatchingPatchMocks.firstOrNull { putRequestMock -> putRequestMock.checkCustomMatcher(requestBodyJson) == MockRequestMatcher.MATCHER_UNDEFINED }
    }
}