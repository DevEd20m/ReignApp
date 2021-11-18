plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BaseURL", "\"https://hn.algolia.com/api/v1/\"")
        }
        create("qa") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BaseURL", "\"https://hn.algolia.com/api/v1/\"")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BaseURL", "\"https://hn.algolia.com/api/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(fileTree("libs") { include(listOf("*.jar")) })
    implementation(Dependencies.KotlinLibraries.kotlin)
    implementation(project(ConfigGradle.Module.domain))

    implementation(Dependencies.Libraries.koinCore)
    implementation(Dependencies.Libraries.retrofit)
    implementation(Dependencies.KotlinLibraries.jsonWebToken)
    implementation(Dependencies.Libraries.logginInterceptor)
    implementation(Dependencies.Libraries.gsonConverter)
    implementation(Dependencies.KotlinLibraries.coroutinesCore)
    implementation(Dependencies.KotlinLibraries.coroutinesAndroid)
    testImplementation(Dependencies.KotlinLibraries.coroutinesTest)
    testImplementation(Dependencies.Libraries.koinTest)
    testImplementation(Dependencies.TestLibraries.androidXcore)
    testImplementation(Dependencies.TestLibraries.jsonForJVM)
    testImplementation(Dependencies.TestLibraries.jUnit)
    androidTestImplementation(Dependencies.TestLibraries.androidJUnit)
}