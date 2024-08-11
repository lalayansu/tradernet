plugins {
    id("module.plugin")
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
}

android {
    namespace = "com.example.tradernet_test_task.data"
}

dependencies {
    implementation(project(":domain"))

    //region Retrofit, Gson and OkHttp
    implementation(Libs.Retrofit.OK_HTTP)
    implementation(Libs.Retrofit.GSON)
    implementation(Libs.Retrofit.GSON_CONVERTER)
    implementation(Libs.Retrofit.RETROFIT)
    //endregion

    //region Scarlet
    implementation(Libs.Scarlet.SCARLET)
    implementation(Libs.Scarlet.SCARLET_LIFECYCLE)
    implementation(Libs.Scarlet.WEB_SOCKET_OKHTTP)
    implementation(Libs.Scarlet.MESSAGE_ADAPTER_GSON)
    implementation(Libs.Scarlet.STREAM_ADAPTER_COROUTINES)
    //endregion

    //region AndroidX lifecycle
    implementation(Libs.Android.LIFECYCLE_PROCESS)
    //endregion

    // region debugImplementation
    debugImplementation(Libs.Retrofit.HTTP_INTERCEPTOR)
    debugImplementation(Libs.Retrofit.COROUTINE_ADAPTER)
    //endregion
}