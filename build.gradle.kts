import com.vanniktech.maven.publish.SonatypeHost
import java.net.URI

plugins {
    id("java")
    id("jacoco")
    id("maven-publish")
    id("com.vanniktech.maven.publish") version "0.32.0"
    id("org.sonarqube") version "4.0.0.2929"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

group = "dev.retrotv"
version = "1.2.0"

// Github Action 버전 출력용
tasks.register("printVersionName") {
    description = "이 프로젝트의 버전을 출력합니다."
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    println(project.version)
}

repositories {
    mavenCentral()
}

val dataUtils = "0.23.0-alpha"
val junit = "5.13.1"
val slf4j = "2.0.16"
val log4j = "2.24.3"
val uuid = "6.1.1"

dependencies {

    // Logger
    implementation("org.slf4j:slf4j-api:${slf4j}")
    implementation("org.apache.logging.log4j:log4j-api:${log4j}")
    implementation("org.apache.logging.log4j:log4j-core:${log4j}")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:${log4j}")

    implementation("dev.retrotv:data-utils:${dataUtils}")
    implementation("com.github.f4b6a3:uuid-creator:${uuid}")

    // Test
    testImplementation(platform("org.junit:junit-bom:${junit}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), project.name, version.toString())

    pom {
        name.set("random-value")
        description.set("안전하고 편리하게 랜덤 값을 생성하는 라이브러리 입니다.")
        inceptionYear.set("2025")
        url.set("https://github.com/retrotv-maven-repo/random-value")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("yjj8353")
                name.set("JaeJun Yang")
                email.set("yjj8353@gmail.com")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/retrotv-maven-repo/random-value.git")
            developerConnection.set("scm:git:ssh://github.com/retrotv-maven-repo/random-value.git")
            url.set("https://github.com/retrotv-maven-repo/random-value.git")
        }
    }

    publishing {
        repositories {

            // Github Packages에 배포하기 위한 설정
            maven {
                name = "GitHubPackages"
                url = URI("https://maven.pkg.github.com/retrotv-maven-repo/random-value")
                credentials {
                    username = System.getenv("USERNAME")
                    password = System.getenv("PASSWORD")
                }
            }
        }
    }
}

tasks.withType<Sign>().configureEach {
    onlyIf {

        // 로컬 및 깃허브 패키지 배포 시에는 서명하지 않도록 설정
        !gradle.taskGraph.hasTask(":publishMavenPublicationToMavenLocal") && !gradle.taskGraph.hasTask(":publishMavenPublicationToGitHubPackagesRepository")
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
    reports {

        // HTML 파일을 생성하도록 설정
        html.required = true

        // SonarQube에서 Jacoco XML 파일을 읽을 수 있도록 설정
        xml.required = true
        csv.required = false
    }
}

sonar {
    properties {
        property("sonar.projectKey", "retrotv-maven-repo_random-value")
        property("sonar.organization", "retrotv-maven-repo")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.exclusions", "**/enums/*")
    }
}
