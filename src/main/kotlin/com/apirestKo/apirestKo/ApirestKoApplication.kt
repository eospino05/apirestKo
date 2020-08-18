package com.apirestKo.apirestKo

import com.apirestKo.apirestKo.model.Persona
import com.apirestKo.apirestKo.model.Quote
import com.apirestKo.apirestKo.services.IPersosnaServices
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@SpringBootApplication
class ApirestKoApplication/*: CommandLineRunner */{

	@Autowired
	val persosnaServices: IPersosnaServices? = null
/*
	override fun run(vararg args: String?) {
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
		val persona1 = Persona("12603", "Elder", "Ospino", LocalDate.parse("05-08-1981", formatter))

		persosnaServices!!.save(persona1)
		println("ok....")


	}
*/
	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
		return builder.build()
	}

	@Bean
	@Throws(Exception::class)
	fun run(restTemplate: RestTemplate): CommandLineRunner? {

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
		val persona1 = Persona("12603", "Elder", "Ospino", LocalDate.parse("05-08-1981", formatter))

		persosnaServices!!.save(persona1)

		println("***************Inicio Consumiendo servicio***********")

		return CommandLineRunner { args: Array<String?>? ->
			val quote = restTemplate.getForObject(
					"https://gturnquist-quoters.cfapps.io/api/random", Quote::class.java)!!
			println(quote.toString())

			val mapper = ObjectMapper()
			val jsonStr = mapper.writeValueAsString(quote)
			val result: Quote = mapper.readValue(jsonStr, Quote::class.java)

			println("object: quote.type:  ${quote.type} , idValue:  ${quote.value!!.id}, quote: ${quote.value!!.quote}")
			println("object: quote.type: " + result.type + ", idValue: " + result.value!!.id + ", quote: " + result.value!!.quote)
			println("JSON: $jsonStr")

			println("***************Fin Consumiendo servicio***********")
		}
	}

}

fun main(args: Array<String>) {
	runApplication<ApirestKoApplication>(*args)
}


