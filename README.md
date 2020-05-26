# Retrofit-Mock-Interceptor  (Under-Construction)

Light weight retrofit response mocker that seamlessly plugs into your existing Android retrofit+okhttp networking infrastructure.
Easy, light-weight, and simple :slightly_smiling_face:.

## Installation
1. Add jitpack to project build.gradle
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
  "com.github.tony15407075:retrofit-mock-interceptor:1.0.1"
```

## Usage

```kotlin
// Full url being https://mock.server.com/users

@GET("user/{id}")
fun getUser(@Path("id") id: String) : Call<User>
```
