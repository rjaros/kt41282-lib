package com.test

import kotlinx.coroutines.asDeferred
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.js.Promise

open class Test {

    suspend inline fun <reified T : Any> deserialize(json: String): T {
        return Promise<String> { resolve, reject ->
            resolve(json)
            reject(Exception())
        }.then {
            Json.decodeFromString<T>(serializer(), it)
        }.asDeferred().await()
    }
}
