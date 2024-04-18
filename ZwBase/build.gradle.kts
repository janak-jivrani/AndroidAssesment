plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.zw.zwbase"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        //compose = true
        buildConfig = true
        viewBinding = true
    }

    /*composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }*/

}

dependencies {

    //val compose_version = "1.5.3"

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    api("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    //api("androidx.activity:activity-compose:1.8.0")
    //api("androidx.compose.ui:ui:$compose_version")
    //api("androidx.compose.ui:ui-tooling-preview:$compose_version")
    //api("androidx.compose.material3:material3:1.1.2")
    //api("androidx.navigation:navigation-compose:2.7.4")
    api("androidx.core:core-animation:1.0.0-rc01")

    //debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")

    /*Multidex*/
    api("androidx.multidex:multidex:2.0.1")

    /*Firebase*/
    api(platform("com.google.firebase:firebase-bom:32.8.0"))
    api("com.google.firebase:firebase-crashlytics-ktx")
    api("com.google.firebase:firebase-analytics-ktx")

    /*Retrofit*/
    val retrofitVersion = "2.9.0"
    val moshiVersion = "1.14.0"
    val okhttpVersion = "4.9.1"
    val sandwichVersion = "1.1.0"

    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    api("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    api("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    api("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    api("com.squareup.moshi:moshi-adapters:$moshiVersion")
    api("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    api("com.squareup.okhttp3:mockwebserver:$okhttpVersion")
    api("com.github.skydoves:sandwich:$sandwichVersion")

    val coilVersion = "2.4.0"

    //api("io.coil-kt:coil-compose:$coilVersion")
    api("com.github.skydoves:landscapist-coil:1.5.1")
    api("io.coil-kt:coil-gif:$coilVersion")

    val timberVersion = "5.0.1"
    api("com.jakewharton.timber:timber:$timberVersion")

    val room_version = "2.6.1"

    /*Room Database*/
    api("androidx.room:room-ktx:$room_version")
    api("androidx.room:room-runtime:$room_version")
    // To use Kotlin annotation processing tool (ksp)
    ksp("androidx.room:room-compiler:$room_version")

    /*DI*/
    api("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    /*SDP and SSp for text size and fixed size for UI elements*/
    //api("com.github.Kaaveh:sdp-compose:1.1.0")

    /*Splash Screen Api for android 12 and above*/
    api("androidx.core:core-splashscreen:1.0.1")

    //kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.6.0")

    api("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    api("androidx.activity:activity-ktx:1.8.2")
    api("androidx.fragment:fragment-ktx:1.6.2")
    api("androidx.navigation:navigation-fragment-ktx:2.7.7")
    api("androidx.navigation:navigation-ui-ktx:2.7.7")
    api("com.intuit.sdp:sdp-android:1.1.0")
    api("com.intuit.ssp:ssp-android:1.1.0")

}