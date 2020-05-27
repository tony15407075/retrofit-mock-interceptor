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
<details><summary><b>Example 1 : <code>GET</code> - wildcard(*) path</b></summary>
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

7.  Done!  Now every retrofit api call with url pattern matching `https://www.base_url.com/user/[0-9]+`, you will receive the mock response object define in ***step 5***.
</p>
</details>



<details><summary><b>Example 2 : <code>GET</code> - wildcard(*) query</b></summary>
<p>

1.  Suppose you have defined this retrofit `GET` request in your app.
```kotlin
// Suppose full url = https://www.base_url.com/user?name=steve&age=21
@GET("user/{id}")
fun getUser(@Path("id") id: String) : Call<User>
```

</p>
</details>
