package com.example.retrofitmockinterceptor

import com.example.retrofitmockinterceptor.mock_request.*
import com.example.retrofitmockinterceptor.mock_response.*
import com.example.retrofitmockinterceptor.model.AddLabelRequestParam
import com.example.retrofitmockinterceptor.model.TestMockRequest
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MockRequestMapperTest {
    private val HTTP_GET = "GET"
    private val HTTP_POST = "POST"
    private val HTTP_PUT = "PUT"
    private val HTTP_PATCH = "PATCH"
    private val HTTP_DELETE = "DELETE"

    private val listOfMocks = listOf(
        GetAccountSpecificMock(),
        GetAccountMock(),
        GetAccountByGroupMock(),
        GetUsersMock(),
        GetTestMock1(),
        GetTestMock2(),
        GetTestMock3(),
        PostLabelMockMatcher(),
        PostLabelMockSpecificMatcher(),
        PostTestRequestMockMatcher(),
        PostTestRequestMockMatching1Matcher(),
        PostTestRequestMockMatching2Matcher(),
        PostTestRequestMockMatching3Matcher(),
        PostTestMock4Matcher(),
        PostTestMock5Matcher(),
        PutTestMock1(),
        PutTestMock2(),
        PutTestMock3(),
        PutTestMock4(),

        DeleteTestMock1(),
        DeleteTestMock2(),
        DeleteTestMock3(),
        DeleteTestMock4(),
        DeleteTestMock5(),
        DeleteTestMock6()
    )

    private lateinit var mockRequestMapper : MockRequestMapper

    @Before
    fun setup() {
        mockRequestMapper = MockRequestMapper(listOfMocks)
    }

    @Test
    fun test_mock_get_success() {
        val testData = listOf(
            Pair("https://random.url.com/api/v2/users/32/accounts?group=132", GetAccountMockSuccess()),
            Pair("https://random.url.com/api/v2/users/3/accounts?group=3213", GetAccountMockSuccess()),
            Pair("https://random.url.com/api/v2/users/32/accounts?group=21", GetAccountMockSuccess()),
            Pair("https://random.url.com/api/v2/users/me", GetUserSuccessMock()),
            Pair("https://random.url.com/api/v2/users/122323/accounts", GetAccountMockSuccess()),
            Pair("https://random.url.com/api/v2/users/12/accounts", GetAccountMockSuccess()),
            Pair("https://random.url.com/api/v2/users/123/accounts", GetAccountSpecificMockSuccess()),

            Pair("https://mock.later.com/api/v2/steven/query?name=122&id=32", GetTestMock1Success()),
            Pair("https://mock.later.com/api/v2/23/query?name=steven&id=3", GetTestMock1Success()),
            Pair("https://mock.later.com/api/v2/asasd/query?name=robin&id=4", GetTestMock1Success()),
            Pair("https://mock.later.com/api/v2/123dsa/query?name=robinhood&id=32", GetTestMock1Success()),
            Pair("https://mock.later.com/api/v2/231/query?name=danger1234&id=32", GetTestMock1Success()),

            Pair("https://mock.later.com/api/v2/specific!123/query?name=testName&id=1", GetTestMock2Success()),
            Pair("https://mock.later.com/api/v2/specific!123/query?name=testName_123&id=123", GetTestMock2Success()),
            Pair("https://mock.later.com/api/v2/specific!123/query?name=232&id=123", GetTestMock2Success()),

            Pair("https://mock.later.com/api/v2/13/query?name=name21321&id=12", GetTestMock3Success()),
            Pair("https://mock.later.com/api/v2/tommy/query?name=fakeName&id=12", GetTestMock3Success()),
            Pair("https://mock.later.com/api/v2/13tommy/query?name=123&id=12", GetTestMock3Success()),
            Pair("https://mock.later.com/api/v2/path/query?name=ddf&id=12", GetTestMock3Success())
        )

        testData.forEach { datum ->
            val url = datum.first
            val mockRequest = mockRequestMapper.map(HTTP_GET, url)
            val actualResponse = mockRequest?.response()
            val expectedResponse = datum.second

            if (actualResponse != null) Assert.assertEquals(expectedResponse::class, actualResponse::class)
            Assert.assertEquals(expectedResponse.fileResId(), actualResponse?.fileResId())
            Assert.assertEquals(expectedResponse.statusCode(), actualResponse?.statusCode())
        }
    }

    @Test
    fun test_mock_get_delete() {
        val testData = listOf(
            Pair("https://mock.later.com/api/v2/delete?id=3", DeleteTestMock1Success()),
            Pair("https://mock.later.com/api/v2/delete?id=12", DeleteTestMock1Success()),

            Pair("https://mock.later.com/api/v2/user1/delete?id=2", DeleteTestMock2Success()),
            Pair("https://mock.later.com/api/v2/user_1/delete?id=2", DeleteTestMock2Success()),
            Pair("https://mock.later.com/api/v2/2323/delete?id=2", DeleteTestMock2Success()),
            Pair("https://mock.later.com/api/v2/arnold/delete?id=2", DeleteTestMock2Success()),

            Pair("https://mock.later.com/api/v2/%specific%/delete?id=0", DeleteTestMock3Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=1", DeleteTestMock3Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=12", DeleteTestMock3Success()),

            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-1", DeleteTestMock4Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-1", DeleteTestMock4Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-1", DeleteTestMock4Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-1", DeleteTestMock4Success()),

            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2", DeleteTestMock5Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2", DeleteTestMock5Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2", DeleteTestMock5Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2", DeleteTestMock5Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2", DeleteTestMock5Success()),

            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2&country=canada", DeleteTestMock6Success()),
            Pair("https://mock.later.com/api/v2/%specific%/delete?id=-2&country=Japan", DeleteTestMock6Success())
        )

        testData.forEach { datum ->
            val url = datum.first

            val mockRequest = mockRequestMapper.map(HTTP_DELETE, url)
            val actualResponse = mockRequest?.response()
            val expectedResponse = datum.second

            if (actualResponse != null) Assert.assertEquals(expectedResponse::class, actualResponse::class)
            Assert.assertEquals(expectedResponse.fileResId(), actualResponse?.fileResId())
            Assert.assertEquals(expectedResponse.statusCode(), actualResponse?.statusCode())
        }
    }

    @Test
    fun test_mock_delete_failed() {
        val testData = listOf(
            "https://www.bad.http.request/api/v2/users/32/accounts?group=132",
            "https://mock.later.com/api/v2/delete?id=-23",
            "https://mock.later.com/api/error/v2/delete?id=32",
            "https://mock.later.com/api/v2/!!/delete?id=2",
            "https://mock.later.com/api/v2/%specific_wrong%/delete?id=23",
            "https://mock.later.com/api/v2/%specific%/delete?id=-23",
            "https://mock.later.com/api/v2/%specific%/delete?id=-1&age=22",
            "https://mock.later.com/api/v2/%specific%/delete?id=-2&age=22",
            "https://mock.later.com/api/v2/%specific%/delete?id=-2&country=Japan1"
        )

        testData.forEach { url ->
            val mockRequest = mockRequestMapper.map(HTTP_DELETE, url)
            val actualResponse = mockRequest?.response()

            Assert.assertNull(actualResponse)
        }
    }



    @Test
    fun test_mock_get_failed() {
        val testData = listOf(
            "https://www.bad.http.request/api/v2/users/32/accounts?group=132",
            "https://www.bad.http.request/api/v2/users/3/accounts?group=3213",
            "https://www.bad.http.request/api/v2/userss/3/accounts?group=3213",
            "https://random.url.com/api/v2/users/122323/accounts?id=2321",
            "https://mock.later.com/api/v2/123dsa/query?name=robinhood&id=32&speed=high"
        )

        testData.forEach { url ->
            val mockRequest = mockRequestMapper.map(HTTP_GET, url)
            val actualResponse = mockRequest?.response()

            Assert.assertNull(actualResponse)
        }
    }


    @Test
    fun test_mock_post_success() {
        val negativeId = -23
        val testData = listOf(
            Triple("https://random.url.com/api/v2/labels", AddLabelRequestParam(1, "Test_Title"), PostLabelMockSuccess()),
            Triple("https://random.url.com/api/v2/labels", AddLabelRequestParam(223, "specific_1"), PostLabelMockSuccess()),
            Triple("https://random.url.com/api/v2/labels", AddLabelRequestParam(23, "specific"), PostLabelMockSuccess()),
            Triple("https://random.url.com/api/v2/labels", AddLabelRequestParam(233, "specific"), PostLabelSpecificMockSuccess()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("title", 1, "name", "Canada"), PostTestRequestMockSuccess()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("title", 1, "John", "Sweden"), PostTestRequestMockMatchingSuccess1()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("title-not-matter", 23211, "John", "Sweden"), PostTestRequestMockMatchingSuccess1()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("title", 2, "Tony", "Canada"), PostTestRequestMockMatchingSuccess2()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("title-not-matter", 2, "Tony", "Canada"), PostTestRequestMockMatchingSuccess2()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo", 232, "Tony_Rambo", "China"), PostTestRequestMockMatchingSuccess3()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo", 232, "does-not-matter", "China"), PostTestRequestMockMatchingSuccess3()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo", 232, "does-not-matter-2", "China"), PostTestRequestMockMatchingSuccess3()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo", 232, "name-wrong", "Korea"), PostTestRequestMockSuccess()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo", 23, "Tony_Rambo", "China"), PostTestRequestMockSuccess()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo-wrong", 232, "Tony_Rambo", "China"), PostTestRequestMockSuccess()),

            Triple("https://mock.later.com/api/mock/1", TestMockRequest("rambo-wrong", 232, "Tony_Rambo", "China"), PostTestMock4Success()),
            Triple("https://mock.later.com/api/mock/2", TestMockRequest("rambo-wrong", 232, "Tony_Rambo", "China"), PostTestMock4Success()),

            Triple("https://mock.later.com/api/mock/$negativeId", TestMockRequest("rambo-wrong", 232, "Tony_Rambo", "China"), PostTestMock5Success()),
            Triple("https://random.url.com/api/mock/test", TestMockRequest("rambo-wrong", 32, "Tony_Rambo", "China-wrong"), PostTestRequestMockSuccess())
        )

        testData.forEach { datum ->
            val url = datum.first
            val requestBody = datum.second
            val expectedResponse = datum.third

            val requestBodyJson = Gson().toJson(requestBody)
            val mapPost = mockRequestMapper.map(HTTP_POST, url, requestBodyJson)
            val actualResponse = mapPost?.response()

            if (actualResponse != null) Assert.assertEquals(expectedResponse::class, actualResponse::class)
            Assert.assertEquals(expectedResponse.fileResId(), actualResponse?.fileResId())
            Assert.assertEquals(expectedResponse.statusCode(), actualResponse?.statusCode())
        }
    }

    @Test
    fun test_mock_post_failed() {
        val testData = listOf(
            Pair("https://www.bad.http.request.com/horrible/0", AddLabelRequestParam(1, "Test_Title")),
            Pair("https://www.bad.http.request.com/horrible/1", AddLabelRequestParam(223, "specific_1")),
            Pair("https://www.bad.http.request.com/horrible/2", AddLabelRequestParam(23, "specific")),
            Pair("https://www.bad.http.request.com/horrible/3", AddLabelRequestParam(233, "specific")),
            Pair("https://www.bad.http.request.com/horrible/4", TestMockRequest("title", 1, "name", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/5", TestMockRequest("title", 1, "John", "Sweden")),
            Pair("https://www.bad.http.request.com/horrible/6", TestMockRequest("title-not-matter", 23211, "John", "Sweden")),
            Pair("https://www.bad.http.request.com/horrible/7", TestMockRequest("title", 2, "Tony", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/8", TestMockRequest("title-not-matter", 2, "Tony", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/9", TestMockRequest("rambo", 232, "Tony_Rambo", "China")),
            Pair("https://www.bad.http.request.com/horrible/10", TestMockRequest("rambo", 232, "does-not-matter", "China")),
            Pair("https://www.bad.http.request.com/horrible/11", TestMockRequest("rambo", 232, "does-not-matter-2", "China")),
            Pair("https://www.bad.http.request.com/horrible/12", TestMockRequest("rambo", 232, "name-wrong", "Korea")),
            Pair("https://www.bad.http.request.com/horrible/13", TestMockRequest("rambo", 23, "Tony_Rambo", "China")),
            Pair("https://www.bad.http.request.com/horrible/14", TestMockRequest("rambo-wrong", 232, "Tony_Rambo", "China")),
            Pair("https://www.bad.http.request.com/horrible/15", TestMockRequest("rambo-wrong", 32, "Tony_Rambo", "China-wrong"))
        )

        testData.forEach { datum ->
            val url = datum.first
            val requestBody = datum.second

            val requestBodyJson = Gson().toJson(requestBody)
            val mapPost = mockRequestMapper.map(HTTP_POST, url, requestBodyJson)
            val actualResponse = mapPost?.response()

            Assert.assertNull(actualResponse)
        }
    }


    @Test
    fun test_mock_put_success() {
        val testData = listOf(
            Triple("https://mock.later.com/put/mock/32", TestMockRequest("title", 1, "name", "Canada"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/32", TestMockRequest("title-2", 12, "name-2", "Canada-2"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/32", TestMockRequest("custom-title", 121, "name-2", "Canada-2"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/1", TestMockRequest("rambo-error", 4, "error-name-1", "Japan"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/1", TestMockRequest("rambo", 3, "error-name-1", "Japan"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/1", TestMockRequest("rambo", 4, "error-name-1", "USA"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/32", TestMockRequest("rambo", 4, "error-name-1", "Japan"), PutTestMock1Success()),
            Triple("https://mock.later.com/put/mock/32", TestMockRequest("custom-title", 123, "name-2", "Canada-2"), PutTestMock2Success()),
            Triple("https://mock.later.com/put/mock/-1", TestMockRequest("rambo", 4, "error-name", "Japan"), PutTestMock3Success()),
            Triple("https://mock.later.com/put/mock/-1", TestMockRequest("rambo", 4, "error-name-1", "Japan"), PutTestMock3Success()),
            Triple("https://mock.later.com/put/mock/-2", TestMockRequest("rambo", 4, "error-name-1", "Japan"), PutTestMock4Success()),
            Triple("https://mock.later.com/put/mock/-2", TestMockRequest("rambo", 4, "error-name-1", "Japan"), PutTestMock4Success())
        )

        testData.forEach { datum ->
            val url = datum.first
            val requestBody = datum.second
            val expectedResponse = datum.third

            val requestBodyJson = Gson().toJson(requestBody)
            val mapPut = mockRequestMapper.map(HTTP_PUT, url, requestBodyJson)
            val actualResponse = mapPut?.response()

            if (actualResponse != null) Assert.assertEquals(expectedResponse::class, actualResponse::class)
            Assert.assertEquals(expectedResponse.fileResId(), actualResponse?.fileResId())
            Assert.assertEquals(expectedResponse.statusCode(), actualResponse?.statusCode())
        }
    }

    @Test
    fun test_mock_put_failed() {
        val testData = listOf(
            Pair("https://www.bad.http.request.com/horrible/4", TestMockRequest("title", 1, "name", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/-4", TestMockRequest("title", 1, "name", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/5", TestMockRequest("title", 1, "John", "Sweden")),
            Pair("https://www.bad.http.request.com/horrible/6", TestMockRequest("title-not-matter", 23211, "John", "Sweden")),
            Pair("https://www.bad.http.request.com/horrible/7", TestMockRequest("title", 2, "Tony", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/8", TestMockRequest("title-not-matter", 2, "Tony", "Canada")),
            Pair("https://www.bad.http.request.com/horrible/9", TestMockRequest("rambo", 232, "Tony_Rambo", "China")),
            Pair("https://www.bad.http.request.com/horrible/10", TestMockRequest("rambo", 4, "does-not-matter", "Japan")),
            Pair("https://www.bad.http.request.com/horrible/11", TestMockRequest("rambo", 4, "does-not-matter", "Japan")),
            Pair("https://www.bad.http.request.com/horrible/12", TestMockRequest("rambo", 4, "does-not-matter", "Japan")),
            Pair("https://www.bad.http.request.com/horrible/13", TestMockRequest("rambo", 4, "does-not-matter", "Japan")),
            Pair("https://www.bad.http.request.com/horrible/14", TestMockRequest("rambo", 4, "does-not-matter", "Japan"))
        )

        testData.forEach { datum ->
            val url = datum.first
            val requestBody = datum.second

            val requestBodyJson = Gson().toJson(requestBody)
            val mapPut = mockRequestMapper.map(HTTP_PUT, url, requestBodyJson)
            val actualResponse = mapPut?.response()

            Assert.assertNull(actualResponse)
        }
    }
}
