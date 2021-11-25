object Dependencies {

    object Libraries {
        // Google
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"

        // Koin
        const val koinCore = "org.koin:koin-android:${Versions.koin}"
        const val koinTest = "org.koin:koin-test:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

        // Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

        //Interceptor
        const val logginInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

        //Gson
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

        const val coil = "io.coil-kt:coil:${Versions.coil}"

        const val roomDataBaseRun = "androidx.room:room-runtime:${Versions.room}"
        const val roomDataBaseCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomDataBaseCoroutines = "androidx.room:room-ktx:${Versions.room}"

    }

    object KotlinLibraries {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val jsonWebToken = "io.jsonwebtoken:jjwt-api:0.11.2"
    }

    object AndroidLibraries {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
        const val recycler = "androidx.recyclerview:recyclerview:${Versions.recycler}"
    }

    object TestLibraries {
        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
        const val mockitoCore = "org.mockito:mockito-core:3.10.0"
        const val mockitoInline = "org.mockito:mockito-inline:2.28.2"
        const val androidXcore = "androidx.arch.core:core-testing:2.1.0"
        const val jsonForJVM = "org.json:json:20140107"
    }
}



