package com.hamburguesa66.scopa

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class ScopaApplication

fun main(args: Array<String>) {
	SpringApplicationBuilder(ScopaApplication::class.java)
		.headless(false)
		.web(WebApplicationType.NONE)
		.run(*args)
}
