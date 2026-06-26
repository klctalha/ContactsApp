    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.compose)

        id("com.google.devtools.ksp") version "2.1.0-1.0.29"

    }

    android {
        namespace = "com.talhakilic.contactsapp"
        compileSdk =37

        defaultConfig {
            applicationId = "com.talhakilic.contactsapp"
            minSdk = 24
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                optimization {
                    enable = false
                }
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        buildFeatures {
            compose = true
        }
    }

    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        testImplementation(libs.junit)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.compose.ui.test.junit4)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.junit)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        debugImplementation(libs.androidx.compose.ui.tooling)

        implementation("androidx.navigation:navigation-compose:2.9.8")
        implementation("io.coil-kt:coil-compose:2.7.0")
        implementation("com.google.code.gson:gson:2.14.0")
        implementation("androidx.compose.runtime:runtime-livedata:1.11.3")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.11.0")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.11.0")
        implementation ("androidx.room:room-runtime:2.8.4")
        implementation("androidx.room:room-ktx:2.8.4")

        ksp("androidx.room:room-compiler:2.8.4")
    }