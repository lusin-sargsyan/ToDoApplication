package com.example.todoapplication.extension

import com.google.gson.Gson

fun <T> T.convertJsonToString(): String = Gson().toJson(this)

inline fun <reified T> String.convertStringToJson(): T = Gson().fromJson(this, T::class.java)
