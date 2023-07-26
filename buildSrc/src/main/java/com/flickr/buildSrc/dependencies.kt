package com.flickr.buildSrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.2"

    object AndroidX {
        private const val version = "1.7.0"
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val splashScreen = "androidx.core:core-splashscreen:1.0.0"

        object Compose {
            const val version = "1.1.1"
            const val compilerVersion = "1.2.0"

            const val ui = "androidx.compose.ui:ui:$version"
            const val activity = "androidx.activity:activity-compose:1.3.1"
            const val material = "androidx.compose.material:material:1.2.0-alpha07"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha06"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val uiPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val foundationLayout = "androidx.compose.foundation:foundation-layout:$version"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
            const val animation = "androidx.compose.animation:animation:$version"
            const val navigation = "androidx.navigation:navigation-compose:2.5.0"
            const val paging = "androidx.paging:paging-compose:1.0.0-alpha15"

            object Hilt {
                private const val version = "1.0.0"
                const val work = "androidx.hilt:hilt-work:$version"
                const val compose = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"
                const val compiler = "androidx.hilt:hilt-compiler:$version"
            }

            object Landscapist {
                const val glide = "com.github.skydoves:landscapist-glide:1.5.2"
            }

            object Accompanist {
                const val version = "0.26.3-beta"
                const val insets = "com.google.accompanist:accompanist-insets:$version"
                const val insetsUi = "com.google.accompanist:accompanist-insets-ui:$version"
                const val pager = "com.google.accompanist:accompanist-pager:$version"
                const val navigationAnimation =
                    "com.google.accompanist:accompanist-navigation-animation:$version"
                const val navigationMaterial =
                    "com.google.accompanist:accompanist-navigation-material:$version"
                const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
                const val pagerIndicators =
                    "com.google.accompanist:accompanist-swiperefresh:$version"
            }
        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val process = "androidx.lifecycle:lifecycle-process:$version"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        }
    }

    object Coroutines {
        private const val version = "1.6.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Hilt {
        private const val version = "2.42"
        const val library = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Retrofit {
        private const val version = "2.8.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Moshi {
        private const val version = "1.13.0"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$version"
    }

    object Kotlin {
        private const val version = "1.7.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Test {
        const val mockK = "io.mockk:mockk:1.13.2"
        const val robolectric = "org.robolectric:robolectric:4.3.1"
        const val turbine = "app.cash.turbine:turbine:0.11.0"

        object Coroutines {
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
        }

        object AndroidX {
            const val core = "androidx.test:core:1.4.0"
            const val rules = "androidx.test:rules:1.4.0"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
            const val junit = "androidx.test.ext:junit-ktx:1.1.3"
        }

        object JUnit {
            const val bom = "org.junit:junit-bom:5.9.1"
            const val jupiterApi = "org.junit.jupiter:junit-jupiter-api"
            const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine"
        }
    }
}