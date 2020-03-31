package com.tonymock.retrofitmockinterceptor

interface MockResponse {
    fun statusCode() : Int
    fun fileResId() : Int
}