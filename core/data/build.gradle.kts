plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "dev.luaoctaviano.dindin.core.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(project(":core:util"))

    // room
    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // di
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // mockk
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    // coroutines test
    testImplementation(libs.kotlinx.coroutines.test)

    // room test
    testImplementation(libs.core.ktx)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.core.testing)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(kotlin("test"))
}