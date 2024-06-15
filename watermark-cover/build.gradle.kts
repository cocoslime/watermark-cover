import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

// Maven 그룹 및 버전 설정
group = "io.github.cocoslime"
version = "0.0.1"

android {
    namespace = "com.cocoslime.watermarkcover"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
//        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose
    // BOM 사용 시 maven publish 에러
    val composeRuntime = "1.6.5"
    val composeFoundation = "1.6.5"
    implementation("androidx.compose.runtime:runtime:$composeRuntime")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.foundation:foundation:$composeFoundation")
    implementation("androidx.compose.foundation:foundation-layout:$composeFoundation")
}
