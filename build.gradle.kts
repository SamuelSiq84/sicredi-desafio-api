plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.27.0")
    testImplementation ("io.rest-assured:rest-assured:5.5.0")
    testImplementation ("org.json:json:20240303")







}

tasks.test {
    useJUnitPlatform()
}