plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "Naviagtion"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies{
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                api(compose.runtime)
            }
        }

        val commonTest by getting{
            dependencies{
                implementation(kotlin("test"))
            }
        }
//        commonMain.dependencies {
//            //put your multiplatform dependencies here
//        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
    }
}

android {
    namespace = "za.co.jacobs.mj.naviagtion"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}
