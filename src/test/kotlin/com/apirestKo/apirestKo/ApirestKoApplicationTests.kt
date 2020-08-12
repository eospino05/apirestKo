package com.apirestKo.apirestKo

import org.mockito.Mockito

import com.apirestKo.apirestKo.dao.IPersonaRepository
import com.apirestKo.apirestKo.model.Persona
import com.apirestKo.apirestKo.services.PersonaServicesImpl
import org.junit.*

import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(MockitoJUnitRunner::class)
@SpringBootTest
class ApirestKoApplicationTests {

	@Mock
	val personaRepository: IPersonaRepository? = null

	@InjectMocks
	val persosnaServices: PersonaServicesImpl? = null


	@Before
	fun before() {
		println("Before");
	}

	@After
	fun after() {
		println("After");
	}


	@Test
	fun contextLoads() {
		println("inicio test................")

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
		val persona1 = Persona("126", "Elder", "Ospino", LocalDate.parse("05-08-1981", formatter))

		var list:List<Persona> = listOf(persona1)




		Mockito.`when`(personaRepository!!.findAll()).thenReturn(list)
		Assert.assertNotNull(personaRepository!!.findAll())

		Assert.assertEquals(1, personaRepository!!.findAll().filter { t -> t.identificacion == "126" }.count())


	}
}
