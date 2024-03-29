plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

buildscript {
    val hilVersion = "2.48"
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
    }
    //.gitignore is devil now for me
}