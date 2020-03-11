package com.example.retrofitmockinterceptor

interface MockResponse {
    fun statusCode() : Int
    fun fileResId() : Int
}