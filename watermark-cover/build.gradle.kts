import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
}

val groupName = "com.cocoslime"
val applicationName = "watermarkcover"

android {
    namespace = "$groupName.$applicationName"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        version = "1.0.0"
//        versionCode = 1
//        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

val secretPropertiesFile: File = rootProject.file("secret/secrets.properties")
if (secretPropertiesFile.exists()) {
    val secretProperties = Properties()
    secretProperties.load(secretPropertiesFile.inputStream())

    secretProperties.forEach { key, value ->
        project.extensions.extraProperties.set(key as String, value)
    }
}

// secret_key 파일 읽기
val signingKeyFile: File = rootProject.file("secret/gpg_secret_key")
val signingKey = signingKeyFile.readText()

publishing {
    publications {
        create("release", MavenPublication::class.java) {
            from(components["release"])
            group = groupName
            artifactId = applicationName

            pom {
                name = "WatermarkCover"
                description = "A concise description of my library"
                url = "http://www.example.com/library"
//                properties = mapOf(
//                    "myProp" to "value",
//                    "prop.with.dots" to "anotherValue"
//                )
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id.set("dongmen94")
                        name.set("dongmen94")
                        email.set("dongmen94@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/cocoslime/watermark-cover.git")
                    developerConnection.set("scm:git:ssh://github.com:cocoslime/watermark-cover.git")
                    url.set("https://github.com/cocoslime/watermark-cover")
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("ossrhUsername") as String? ?: ""
                password = findProperty("ossrhPassword") as String? ?: ""
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


/*


extensions.findByType(PublishingExtension::class.java)?.apply {

}

extensions.findByType(SigningExtension::class.java)?.apply {
    useInMemoryPgpKeys(
        findProperty("signing.keyId") as String? ?: "",
        signingKey,
        ""
    )
    sign(publishing.publications["mavenJava"])
}

//    extensions.findByType(JavaPluginExtension::class.java)?.apply {
//        withJavadocJar()
//        withSourcesJar()
//    }

 */