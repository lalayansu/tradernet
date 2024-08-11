import Versions.activityVersion
import Versions.androidBuildToolsGradlePlugin
import Versions.appCompatVersion
import Versions.composeCoilVersion
import Versions.composeConstraintVersion
import Versions.composeMaterial3Version
import Versions.composeUiVersion
import Versions.coreKtxVersion
import Versions.coroutinesVersion
import Versions.gsonVersion
import Versions.hiltAndroidxVersion
import Versions.hiltComposeVersion
import Versions.hiltGradlePlugin
import Versions.hiltVersion
import Versions.kotlinGradlePluginVersion
import Versions.kotlinVersion
import Versions.lifecycleVersion
import Versions.materialVersion
import Versions.okHttpVersion
import Versions.retrofitCoroutinesVersion
import Versions.retrofitVersion
import Versions.runtimeVersion
import Versions.scarletVersion

object Versions {

    const val COMPILE_SDK = 34
    const val MIN_SDK = 26
    const val TARGET_SDK = 34
    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"

    const val kotlinGradlePluginVersion = "1.9.0"
    const val androidBuildToolsGradlePlugin = "7.3.1"
    const val hiltGradlePlugin = "2.46.1"

    const val composeUiVersion = "1.4.0-alpha03"
    const val hiltComposeVersion = "1.2.0"
    const val composeMaterial3Version = "1.1.1"
    const val composeConstraintVersion = "1.0.1"
    const val composeCoilVersion = "2.2.2"
    const val runtimeVersion = "1.0.3"

    const val hiltVersion = "2.46"
    const val hiltAndroidxVersion = "1.0.0"
    const val retrofitCoroutinesVersion = "0.9.2"
    const val gsonVersion = "2.10"
    const val retrofitVersion = "2.9.0"
    const val okHttpVersion = "5.0.0-alpha.11"
    const val activityVersion = "1.8.0-alpha06"

    const val kotlinVersion = "1.9.0"
    const val appCompatVersion = "1.5.1"
    const val coreKtxVersion = "1.9.0"
    const val coroutinesVersion = "1.6.4"
    const val lifecycleVersion = "2.6.1"
    const val materialVersion = "1.8.0-beta01"

    const val scarletVersion = "0.1.10"
}

object DependencyType {
    const val IMPLEMENTATION = "implementation"
    const val KAPT = "kapt"
}

object Plugins {
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradlePluginVersion"
    const val ANDROID_GRADLE_PLUGIN =
        "com.android.tools.build:gradle:$androidBuildToolsGradlePlugin"
    const val HILT_GRADLE_PLUGIN =
        "com.google.dagger:hilt-android-gradle-plugin:$hiltGradlePlugin"
}

object Libs {

    object Kotlin {
        const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:$appCompatVersion"
        const val CORE_KTX = "androidx.core:core-ktx:$coreKtxVersion"
        const val LIFECYCLE_VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val LIFECYCLE_RUNTIME =
            "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val LIFECYCLE_PROCESS = "androidx.lifecycle:lifecycle-process:$lifecycleVersion"
        const val MATERIAL = "com.google.android.material:material:$materialVersion"
    }

    object Compose {
        const val UI = "androidx.compose.ui:ui:$composeUiVersion"
        const val MATERIAL = "androidx.compose.material:material:$composeUiVersion"
        const val MATERIAL_3 = "androidx.compose.material3:material3:$composeMaterial3Version"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$composeUiVersion"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$composeUiVersion"
        const val ACTIVITY = "androidx.activity:activity-compose:$activityVersion"
        const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:$hiltComposeVersion"
        const val MATERIAL_ICONS_EXTENDED =
            "androidx.compose.material:material-icons-extended:$composeUiVersion"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout-compose:$composeConstraintVersion"
        const val COIL = "io.coil-kt:coil-compose:$composeCoilVersion"
        const val RUNTIME = "androidx.compose.runtime:runtime:$runtimeVersion"
    }

    object Hilt {
        const val ANDROID = "com.google.dagger:hilt-android:$hiltVersion"
        const val ANDROIDX = "androidx.hilt:hilt-compiler:$hiltAndroidxVersion"
        const val KAPT = "com.google.dagger:hilt-compiler:$hiltVersion"
    }

    object Retrofit {
        const val COROUTINE_ADAPTER =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$retrofitCoroutinesVersion"
        const val GSON = "com.google.code.gson:gson:$gsonVersion"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val HTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
        const val OK_HTTP = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    }

    object Scarlet {
        const val SCARLET = "com.tinder.scarlet:scarlet:$scarletVersion"
        const val WEB_SOCKET_OKHTTP = "com.tinder.scarlet:websocket-okhttp:$scarletVersion"
        const val MESSAGE_ADAPTER_GSON = "com.tinder.scarlet:message-adapter-gson:$scarletVersion"
        const val STREAM_ADAPTER_COROUTINES =
            "com.tinder.scarlet:stream-adapter-coroutines:$scarletVersion"
        const val SCARLET_LIFECYCLE = "com.tinder.scarlet:lifecycle-android:$scarletVersion"
    }
}
