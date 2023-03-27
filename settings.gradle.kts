import java.net.URI
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    val agpVersion: String by settings
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.library") version agpVersion
        id("com.android.application") version agpVersion
        id("com.google.devtools.ksp") version "1.7.20-1.0.7"
        id("dev.rikka.tools.refine") version "3.1.1"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            library("libxposed-api", "io.github.libxposed", "api").version {
                branch = "master"
            }
            library("libxposed-interface", "io.github.libxposed", "interface").version {
                branch = "master"
            }
        }
    }
}

sourceControl {
    gitRepository(URI.create("https://github.com/libxposed/api.git")) {
        producesModule("io.github.libxposed:api")
    }
    gitRepository(URI.create("https://github.com/libxposed/service.git")) {
        producesModule("io.github.libxposed:interface")
    }
}


rootProject.name = "LSPatch"
include(
    ":apkzlib",
    ":axmlprinter",
    ":core",
    ":hiddenapi:bridge",
    ":hiddenapi:stubs",
    ":jar",
    ":manager",
    ":meta-loader",
    ":patch",
    ":patch-loader",
    ":services:daemon-service",
    ":services:manager-service",
    ":services:xposed-service:interface",
    ":share:android",
    ":share:java",
)

project(":core").projectDir = file("core/core")
project(":hiddenapi:bridge").projectDir = file("core/hiddenapi/bridge")
project(":hiddenapi:stubs").projectDir = file("core/hiddenapi/stubs")
project(":services:daemon-service").projectDir = file("core/services/daemon-service")
project(":services:manager-service").projectDir = file("core/services/manager-service")
project(":services:xposed-service:interface").projectDir = file("core/services/xposed-service/interface")

buildCache { local { removeUnusedEntriesAfterDays = 1 } }
