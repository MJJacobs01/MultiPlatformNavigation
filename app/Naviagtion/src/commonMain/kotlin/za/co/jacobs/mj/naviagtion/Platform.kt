package za.co.jacobs.mj.naviagtion

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform