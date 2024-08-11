buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(Plugins.ANDROID_GRADLE_PLUGIN)
        classpath(Plugins.KOTLIN_GRADLE_PLUGIN)
        classpath(Plugins.HILT_GRADLE_PLUGIN)
    }
}