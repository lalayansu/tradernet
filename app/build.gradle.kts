plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.tradernet_test_task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tradernet_test_task"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    flavorDimensions.add("type")
    productFlavors {
        create("tradernet_test") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://tradernet.com/\""
            )
            buildConfigField(
                "String",
                "BASE_URL_WS",
                "\"wss://wss.tradernet.com\""
            )
            dimension = "type"
            applicationIdSuffix = ".test"

            resValue("string", "app_name", "[Test]Tradernet")
        }

        create("tradernet_prod") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://tradernet.com/\""
            )
            buildConfigField(
                "String",
                "BASE_URL_WS",
                "\"wss://wss.tradernet.com\""
            )
            dimension = "type"

            resValue("string", "app_name", "Tradernet")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                "proguard-rules.pro"
            )
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {
    implementation(project(":presenter"))
    implementation(project(":domain"))
    implementation(project(":data"))

    //Kotlin
    implementation(Libs.Kotlin.STD_LIB)
    implementation(Libs.Kotlin.COROUTINES_CORE)
    implementation(Libs.Kotlin.COROUTINES_ANDROID)

    //AndroidX
    implementation(Libs.Android.APPCOMPAT)
    implementation(Libs.Android.CORE_KTX)
    implementation(Libs.Android.LIFECYCLE_VIEW_MODEL)
    implementation(Libs.Android.LIFECYCLE_RUNTIME)

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.KAPT)
    kapt(Libs.Hilt.ANDROIDX)
}
