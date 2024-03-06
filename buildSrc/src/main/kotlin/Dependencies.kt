object Versions {
    const val appcompat = "1.6.1"
    const val constraintLayout = "2.1.4"
    const val core = "1.12.0"
    const val material = "1.11.0"
    const val navigation = "2.7.7"
    const val fragmentKtx = "1.6.2"
    const val paging = "3.2.1"

    const val coroutines = "1.7.3"
    const val kotlinxSerialization = "1.6.3"

    const val dagger = "2.50"
    const val retrofit = "2.9.0"
    const val okhttp3 = "4.12.0"
    const val glide = "4.16.0"
}

object AndroidX {
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }
}

object KotlinX {
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Dagger {
    const val daggerCore = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}
object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
}

object Glide {
    const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
}