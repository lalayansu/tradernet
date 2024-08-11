plugins {
    id("module.plugin")
}

android {
    namespace = "com.example.tradernet_test_task.presenter"

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures.viewBinding = true
}

dependencies {
    api(project(":domain"))

    api(Libs.Android.APPCOMPAT)
    api(Libs.Android.CORE_KTX)
    api(Libs.Android.MATERIAL)

    //region Compose
    api(Libs.Compose.UI)
    api(Libs.Compose.MATERIAL)
    api(Libs.Compose.MATERIAL_3)
    api(Libs.Compose.UI_TOOLING_PREVIEW)
    api(Libs.Compose.UI_TOOLING)
    api(Libs.Compose.ACTIVITY)
    api(Libs.Compose.HILT_NAVIGATION)
    api(Libs.Compose.MATERIAL_ICONS_EXTENDED)
    api(Libs.Compose.CONSTRAINT_LAYOUT)
    api(Libs.Compose.RUNTIME)
    api(Libs.Compose.COIL)
    //endregion
}