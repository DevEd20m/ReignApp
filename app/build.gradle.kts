plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        applicationId = "com.faztbit.reignapp"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = ConfigGradle.Releases.versionCode
        versionName = ConfigGradle.Releases.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("qa") {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        dataBinding = true
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "app-${variant.buildType.name}_reignAPP_VC${defaultConfig.versionCode}_${defaultConfig.versionName}_.apk"
                println("OutputFileName: $outputFileName")
                output.outputFileName = outputFileName
            }
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar")) })
    implementation(Dependencies.KotlinLibraries.kotlin)
    implementation(Dependencies.AndroidLibraries.appCompat)
    implementation(Dependencies.AndroidLibraries.coreKtx)
    implementation(Dependencies.Libraries.koinCore)
    implementation(Dependencies.Libraries.koinViewModel)
    implementation(Dependencies.Libraries.materialDesign)
    implementation(Dependencies.Libraries.gson)
    implementation(Dependencies.Libraries.coil)
    implementation(Dependencies.AndroidLibraries.constraintlayout)
    implementation(Dependencies.AndroidLibraries.legacySupport)
    implementation(Dependencies.AndroidLibraries.navigationFragment)
    implementation(Dependencies.AndroidLibraries.navigationUi)
    implementation(Dependencies.AndroidLibraries.fragment)
    implementation(Dependencies.AndroidLibraries.recycler)
    implementation(Dependencies.AndroidLibraries.activity)
    implementation(project(ConfigGradle.Module.domain))
    implementation(project(ConfigGradle.Module.data))
    testImplementation(Dependencies.Libraries.koinTest)
    testImplementation(Dependencies.KotlinLibraries.coroutinesTest)
    testImplementation(Dependencies.TestLibraries.jUnit)
    testImplementation(Dependencies.TestLibraries.androidJUnit)
    testImplementation(Dependencies.TestLibraries.mockito)
    testImplementation(Dependencies.TestLibraries.mockitoInline)
    testImplementation(Dependencies.TestLibraries.androidXcore)
    androidTestImplementation(Dependencies.TestLibraries.espresso)
}