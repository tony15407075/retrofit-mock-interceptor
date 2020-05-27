# Retrofit-Mock-Interceptor  (Under-Construction)

Light weight retrofit response mocker that easily integrates with existing Retrofit+OkHttp setup.

- Supports `.json` mock response files.
- Supports regex url path mapping, highly configurable.
- Plug and play, integrates seamlessly with existing retrofit+okhttp networking infrastructure.
- Light weight.

## Installation
1. Add jitpack to project build.gradle.
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
```
2. Add the library dependency to app/build.gradle

```gradle
implementation ("com.github.tony15407075:retrofit-mock-interceptor:1.0.1") {
    exclude module: "okhttp"
    exclude group: "androidx"
}
```

## Usage
<details><summary><b>Example 1 : <code>GET</code> - Path Wildcard 🃏</b></summary>
<p>

1.  Suppose you have defined this retrofit `GET` request in your app.
```kotlin
// Suppose full url = https://www.base_url.com/user/{id}
@GET("user/{id}")
fun getUser(@Path("id") id: String) : Call<User>
```

2.  To mock the above `GET` request, you need to map it to a `GetRequestMock`, with the proper regex pattern.
```kotlin
class GetUserMockSuccess : GetRequestMock {

    override fun urlPattern(): Pattern {
        // https://www.base_url.com/user/2 --> Match
        // https://www.base_url.com/user/10 --> Match
        // https://www.base_url.com/user/223 --> Match
        // https://www.base_url.com/user/tommy --> Non_Match

        // Mock class maps to below url pattern
        return Pattern.compile("https://www.base_url.com/user/[0-9]+")
    }

    override fun response(): MockResponse {
        // Returns this [MockResponse] upon successfully intercepting request with url pattern defined above
        return GetUserMockResponse()
    }
}
```

3.  Next define a corresponding `MockResponse` object.
```kotlin
class GetUserMockResponse : MockResponse {
    override fun fileResId(): Int {
        // .json file of the mock response
        return R.raw.get_user_mock_response
    }

    override fun statusCode(): Int {
        // status code of the response
        return 200
    }
}
```

4.  Create a `get_user_mock_response.json` your resources `/res/raw/` directory.  [examples](https://github.com/tony15407075/retrofit-mock-interceptor/blob/master/app/src/debug/res/raw/test_mock_get_success.json).

5.  Populate the `get_user_mock_response.json`.

```json
{
    "name" :  "name-mock",
    "message" : "message-mock",
    "id" :  23,
    "age" : 30
}
```

6.  Add `MockInterceptor` to your retrofit's `OkHttpClient` configuration.
```kotlin
val mockRequests = listOf<MockRequest>(
    GetUserMockSuccess()
    // Add additional MockRequests to this list
)

// resources = context.getResources()
val mockInterceptor = MockInterceptor(resources, mockRequests)

OkHttpClient okHttpClient = new OkHttpClient.Builder()
    .addInterceptor(mockInterceptor)
    .build();

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("your_api_base_url")
    .client(okHttpClient)
    .build();
```

7.  Done!  Now every retrofit `Get` request with url pattern matching `https://www.base_url.com/user/[0-9]+`, you will receive the mock response object define in ***step 5***.
</p>
</details>



<details><summary><b>Example 2 : <code>GET</code> - Query Wildcard 🃏</b></summary>
<p>

1.  Suppose you have defined this retrofit `GET` request in your app.
```kotlin
// Suppose full url = https://www.base_url.com/user?name=steve&age=21
@GET("user")
fun getUser(@Query("name") name: String, 
            @Query("age") age: Int) : Call<User>
```

2.  To mock the above `GET` request, you need to map it to a `GetRequestMock`, with the proper regex pattern.
```kotlin
class QueryUserMockSuccess : GetRequestMock {

    override fun urlPattern(): Pattern {
        // https://www.base_url.com/user?name=steve&age=21  => match
        // https://www.base_url.com/user?name=john&age=1    => match
        // https://www.base_url.com/user?name=Chris&age=32  => match
        
        // *Important* query symbol "?" must be wrapped around square brackets [?]
        return Pattern.compile("https://www.base_url.com/user[?]name=[a-zA-Z]+&age=[0-9]+")
    }

    override fun response(): MockResponse {
        // Returns this [MockResponse] upon successfully intercepting request with url pattern defined above
        return QueryUserMockResponse()
    }
}
```

3.  Next define a corresponding `MockResponse` object.
```kotlin
class QueryUserMockResponse : MockResponse {
    override fun fileResId(): Int {
        // .json file of the mock response
        return R.raw.query_user_mock_response
    }

    override fun statusCode(): Int {
        // status code of the response
        return 200
    }
}
```

4.  Create a `query_user_mock_response.json` your resources `/res/raw/` directory.  [examples](https://github.com/tony15407075/retrofit-mock-interceptor/blob/master/app/src/debug/res/raw/test_mock_get_success.json).

5.  Populate the `get_user_mock_response.json`.

```json
{
    "name" :  "name-mock",
    "message" : "message-mock",
    "id" :  23,
    "age" : 30
}
```

6.  Add `MockInterceptor` to your retrofit's `OkHttpClient` configuration.
```kotlin
val mockRequests = listOf<MockRequest>(
    QueryUserMockSuccess()
    // Add additional MockRequests to this list
)

// resources = context.getResources()
val mockInterceptor = MockInterceptor(resources, mockRequests)

OkHttpClient okHttpClient = new OkHttpClient.Builder()
    .addInterceptor(mockInterceptor)
    .build();

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("your_api_base_url")
    .client(okHttpClient)
    .build();
```

7.  Done!  Now every retrofit `Get` request with url pattern matching `https://www.base_url.com/user[?]name=[a-zA-Z]+&age=[0-9]+`, you will receive the mock response object define in ***step 5***.

</p>
</details>



<details><summary><b>Example 3 : <code>GET</code> - Exact Match</b></summary>
<p>

1.  Suppose you have defined this retrofit `GET` request in your app.
```kotlin
// Suppose full url = https://www.base_url.com/user?name=steve&age=21
@GET("user")
fun getUser(@Query("name") name: String, 
            @Query("age") age: Int) : Call<User>
```

2.  Suppose you want to mock the above `GET` request for a specific user `{name="%John%", age=-12}`.  First define the corresponding `GetRequestMock`.  

#### *Important !!* - For exact query/path matching make sure you include special characters (*&^%%# ... etc) for the unique query/path values.  This is require to differentiate between potential colliding wildcard (`*`) and unique url patterns.  Failure to do so might result in the wrong mock response object being returned.

#### ex
:black_joker: `Wildcard (*) pattern        : "https://www.base_url.com/user[?]name=[a-zA-Z]+&age=[0-9]+"`\
:x: `Exact Pattern   : "https://www.base_url.com/user[?]name=John&age=23" // Will get swallowed up by above regex pattern`\
:heavy_check_mark: `Exact Pattern:     : "https://www.base_url.com/user[?]name=#John#&age=-23"`


```kotlin
class QueryUserMockSuccess : GetRequestMock {
    override fun urlPattern(): Pattern {
        val uniqueName = "%John%"
        val uniqueAge = -12
        
        // *Important* query symbol "?" must be wrapped around square brackets [?]
        return Pattern.compile("https://www.base_url.com/user[?]name=${uniqueName}&age=${uniqueAge}")
    }

    override fun response(): MockResponse {
        // Returns this [MockResponse] upon successfully intercepting request with url pattern defined above
        return QueryUserMockResponse()
    }
}
```

</p>
</details>

