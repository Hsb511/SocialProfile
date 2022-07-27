object AppVersions {
    private const val major = 1
    private const val minor = 0
    private const val fix = 0

    const val versionCode = major * 1_000_000 + minor * 1_000 + fix
    const val versionName = "$major.$minor.$fix"
}