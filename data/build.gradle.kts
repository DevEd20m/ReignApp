import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

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
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
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

    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    implementation (Dependencies.Libraries.roomDataBaseRun){
        exclude(group = "org.xerial")
    }
    kapt (Dependencies.Libraries.roomDataBaseCompiler){
        exclude(group = "org.xerial")
    }
    kapt ("org.xerial:sqlite-jdbc:3.34.0")
    annotationProcessor (Dependencies.Libraries.roomDataBaseCompiler)
    implementation (Dependencies.Libraries.roomDataBaseCoroutines)
    testImplementation(Dependencies.KotlinLibraries.coroutinesTest)
    testImplementation(Dependencies.Libraries.koinTest)
    testImplementation(Dependencies.TestLibraries.androidXcore)
    testImplementation(Dependencies.TestLibraries.jsonForJVM)
    testImplementation(Dependencies.TestLibraries.jUnit)
    androidTestImplementation(Dependencies.TestLibraries.androidJUnit)
}