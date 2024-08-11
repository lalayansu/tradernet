# Tradernet test task - Android

## Architecture

### Global

* __Language__: Kotlin
* __Compatible__: API 26 or higher
* __UI
  Paradigm__: MVVM + uiState
* __Global architecture__:
    * Clean Architecture
    * Single Activity Architecture
    * Modular Architecture: 5 Gradle modules, one for each clean architecture layer + 1 Application
      module + 1 module for buildSrc

### Variants

* `type`
    * `prod` - Connected to production server (Same with `test` for now)
    * `test` - Connected to test server (Same with `prod` for now)
* `buildType`
    * `debug` - Development
    * `release` - Release

## Environment

### Dependencies

- Jetpack Compose
- Dependency injection: Hilt
- Navigation: Navigation Compose
- Concurrency: Coroutines
- Serialization: Gson
- Flows
- AndroidX
- Android KTX
- HTTP Client: OkHttp
- Socket Client: Scarlet
- HTTP API management: Retrofit

