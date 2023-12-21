package com.example.cards

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class CardsApplication

fun main(args: Array<String>) {
	SpringApplicationBuilder(CardsApplication::class.java)
		.headless(false)
		.web(WebApplicationType.NONE)
		.run(*args)
}
