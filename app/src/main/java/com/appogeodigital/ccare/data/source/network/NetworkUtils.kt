package com.appogeodigital.ccare.data.source.network

import okhttp3.*
import java.io.IOException

object NetworkUtils {
    // Adds header to the OkHttpClient making the request.
    val httpClient: OkHttpClient
        get() = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build()

                    chain.proceed(request)
                }
                .build()

    /*val httpClient: OkHttpClient
        get() = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    MockClient().intercept(chain)
                }
                .build()*/
}

class MockClient: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url()
        val urlEncodedPath = url.encodedPath()
        when (url.encodedPath()) {
            "/itemList" -> {
                val response: String = "readJsonFieleFromAssestOrAnyOtherStorage"
                return Response.Builder()
                        .code(200)
                        .message(response)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .body(ResponseBody.create(MediaType.parse("application/json"), response.toByteArray()))
                        .addHeader("content-type", "application/json")
                        .build()
            }
        }
        return Response.Builder().build()
    }

}


/*class MockClient : Client {
    @Throws(IOException::class)
    fun execute(request: Request): Response {
        val uri = Uri.parse(request.getUrl())
        Log.d("MOCK SERVER", "fetching uri: $uri")
        var responseString = ""
        responseString = if (uri.path == "/path/of/interest") {
            "JSON STRING HERE"
        } else {
            "OTHER JSON RESPONSE STRING"
        }
        return Response(request.getUrl(), 200, "nothing", Collections.EMPTY_LIST, TypedByteArray("application/json", responseString.toByteArray()))
    }
}*/
