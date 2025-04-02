import java.io.ByteArrayOutputStream

plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("com.google.cloud.tools.jib") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.omarhammad"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"



dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-config-server")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")
	implementation("org.springframework.cloud:spring-cloud-config-monitor")

}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


// Use project.name to determine the microservice name
val repoName = project.name

// Execute the bash script before the jib task
tasks.register("incrementTag") {
	doFirst {
		println("Running incrementTag task...")
		val output = ByteArrayOutputStream()
		exec {
			commandLine("bash", "fetch-latest-tag.sh", repoName)
			standardOutput = output
		}
		val newTag = output.toString().trim()
		println("New tag from script: $newTag")
		project.ext.set("newTag", newTag)
		println("newTag property set to: ${project.ext.get("newTag")}")
	}

}

tasks.named("jib") {
	dependsOn("incrementTag")
	doFirst {
		println("Running jib task...")
		if (!project.ext.has("newTag")) {
			throw IllegalStateException("newTag property is not set. Ensure the incrementTag task runs successfully.")
		}
		val newTag = project.ext.get("newTag") as String
		println("Using tag: $newTag")

		jib {
			from {
				image = "openjdk:22-jdk"
			}
			to {
				image = "omarhammad97/${repoName}:$newTag"
				tags = setOf("latest")
				auth {
					username = System.getenv("DOCKER_USERNAME") ?: throw GradleException("DOCKER_USERNAME not set")
					password = System.getenv("DOCKER_PASSWORD") ?: throw GradleException("DOCKER_PASSWORD not set")
				}
				container {
					mainClass = "com.omarhammad.configserver.ConfigserverApplication"
				}
			}
		}
	}
}