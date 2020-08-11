package com.apirestKo.apirestKo

import com.apirestKo.apirestKo.model.Persona
import com.apirestKo.apirestKo.services.IPersosnaServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class ApirestKoApplication: CommandLineRunner {

	@Autowired
	val persosnaServices: IPersosnaServices? = null

	override fun run(vararg args: String?) {
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
		val persona1 = Persona("12603", "Elder", "Ospino", LocalDate.parse("05-08-1981", formatter))

		persosnaServices!!.save(persona1)
		println("ok....")
	}


}

fun main(args: Array<String>) {
	runApplication<ApirestKoApplication>(*args)
}


