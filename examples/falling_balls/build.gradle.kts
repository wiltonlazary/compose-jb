
group = "me.user"
version = "1.0"

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.4.31" apply true
    id("application")
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    "kotlinCompilerPluginClasspath"("androidx.compose.compiler:compiler:1.0.0-beta02")
}


tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile> {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-P", "plugin:androidx.compose.compiler.plugins.kotlin:generateDecoys=true"
        )
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
}


kotlin {
    jvm {
        withJava()
        application {
            mainClass.value( "org.jetbrains.compose.demo.falling.MainKt" )
        }

    }

    js(IR) {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("androidx.compose.runtime:runtime:1.0.0-beta02")
                implementation("androidx.compose.js:js:1.0.0-beta02")
            }
        }

        val jsMain by getting {
            dependencies {
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("androidx.compose.desktop:desktop-jvm-macos-x64:1.0.0-beta02")
            }
        }
    }
}
