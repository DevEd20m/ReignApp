plugins {
    id ("java-library")
    id ("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation (fileTree("libs") {include(listOf("*.jar"))})
    implementation (Dependencies.KotlinLibraries.coroutinesAndroid)
    implementation (Dependencies.KotlinLibraries.coroutinesCore)
    implementation (Dependencies.Libraries.koinCore)
}