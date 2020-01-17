import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "com.brynachj"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("io.rest-assured:rest-assured:3.3.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.assertj:assertj-core:3.14.0")

}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
	}

	group = "project"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	val implementation by configurations

	dependencies {
		implementation(kotlin("stdlib-jdk8"))
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
}

//dependencies {
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("junit:junit:4.12")
//    testImplementation("commons-logging:commons-logging")
//    testImplementation("org.hamcrest:hamcrest")
////    testImplementation("com.google.guava:guava")
//    testImplementation("io.rest-assured:rest-assured:3.3.0")
////    testImplementation("org.codehaus.groovy:groovy-all")
//    testImplementation("org.assertj:assertj-core")
//}
}
