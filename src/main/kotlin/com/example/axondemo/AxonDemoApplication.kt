package com.example.axondemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan("com.example.axondemo")
@SpringBootApplication
class AxonDemoApplication

fun main(args: Array<String>) {
	runApplication<AxonDemoApplication>(*args)
}
