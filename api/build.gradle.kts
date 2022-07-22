plugins {
    id("kotlin")
}

dependencies {
    // Retrofit
    api("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}")

    testImplementation("junit:junit:4.13.2")
}
