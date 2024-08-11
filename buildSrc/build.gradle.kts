repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation(kotlin("gradle-plugin", "1.9.0"))
    implementation("com.squareup:javapoet:1.13.0")
}

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("module.plugin") {
            id = "module.plugin"
            implementationClass = "ModulePlugin"
        }
    }
}