pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.novoda.bintray-release") {
                useModule("com.novoda:bintray-release:${requested.version}")
            }
        }
    }
}

include(":samples:android", ":samples:jvm")
include(":usekase-annotation", ":usekase", ":usekase-rx", ":usekase-coroutines", ":usekase-processor")
