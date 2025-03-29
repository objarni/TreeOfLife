plugins {
    kotlin("jvm") version "1.8.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
    implementation("io.kpeg:kpeg:0.1.2")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("TreeOfLife.MainKt")
}

tasks.test {
    useJUnitPlatform()
}


sourceSets {
    main {
        kotlin.srcDirs("src")
    }
    test {
        kotlin.srcDirs("tests")
    }
}