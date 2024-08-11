import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = project.applyAndroid()

    private fun Project.applyAndroid() {
        plugins.run {
            apply("com.android.library")
            apply("kotlin-android")
            apply("kotlin-parcelize")
            apply("kotlin-kapt")
            apply("dagger.hilt.android.plugin")
        }
        android {
            compileSdkVersion(Versions.COMPILE_SDK)

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            project.tasks.withType(KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_11.toString()
                }
            }

            defaultConfig {
                minSdk = Versions.MIN_SDK
                targetSdk = Versions.TARGET_SDK

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables.useSupportLibrary = true
            }

            this.flavorDimensions("type")
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
                    dimension("type")
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
                    dimension("type")
                }
            }

            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                }
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        dependencies {
            add(DependencyType.IMPLEMENTATION, Libs.Kotlin.COROUTINES_ANDROID)
            add(DependencyType.IMPLEMENTATION, Libs.Kotlin.COROUTINES_CORE)

            add(DependencyType.IMPLEMENTATION, Libs.Kotlin.STD_LIB)
            add(DependencyType.IMPLEMENTATION, Libs.Android.CORE_KTX)
            add(DependencyType.IMPLEMENTATION, Libs.Android.APPCOMPAT)

            add(DependencyType.IMPLEMENTATION, Libs.Hilt.ANDROID)
            add(DependencyType.KAPT, Libs.Hilt.KAPT)
            add(DependencyType.KAPT, Libs.Hilt.ANDROIDX)
        }
    }

    private fun Project.android(action: BaseExtension.() -> Unit) =
        (extensions["android"] as BaseExtension).run(action)
}