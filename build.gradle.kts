plugins {
    kotlin("jvm") version "1.9.23"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.23")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.23")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("treeOfLife.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}


sourceSets {
    main {
        kotlin.srcDirs("src")
    }
    test {
        kotlin.srcDirs("tests")
    }
}