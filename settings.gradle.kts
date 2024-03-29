dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Crypto Compose"
include(":app")
include(":data:latest_coins")
include(":data:coin_details")
include(":core:network")
include(":core:ui")
include(":domain:latest_coins_list")
include(":domain:coin_details")
include(":feature:home")
include(":core:presentation")
include(":core:navigation")
include(":domain:network_capabilities")
include(":core:network_capabilities")
include(":feature:latest_coins")
include(":data:categories")
include(":domain:categories")
include(":feature:categories")
include(":core:unit_test")
