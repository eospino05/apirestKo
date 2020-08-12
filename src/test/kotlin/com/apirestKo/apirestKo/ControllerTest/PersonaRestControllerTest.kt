package com.apirestKo.apirestKo.ControllerTest

import com.apirestKo.apirestKo.model.Persona
import com.apirestKo.apirestKo.utils.Constans

import org.junit.*

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CatsList : MutableList<Persona> by ArrayList()

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonaRestControllerTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate


    @Before
    fun before() {
        println("Before");
    }

    @After
    fun after() {
        println("After");
    }


    @Test
     fun list() {
        println("*************** API REST Listar Personas ****************")

        val result = testRestTemplate.getForEntity(Constans.URL_BASE_PERSONAS, CatsList::class.java)


        Assert.assertEquals(200, result.statusCode.value())


        //result.body!!.forEach { t ->  println("Identificacion: " + t.identificacion)}


        Assert.assertEquals(1, result.body!!.filter { t -> t.identificacion == "12603" }.count())



    }

    @Test
    fun insert() {
        println("*************** API REST Insert Personas ****************")

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
        val persona1 = Persona("454545", "Julano", "Detal", LocalDate.parse("05-08-1981", formatter))


        val result = testRestTemplate.postForEntity(Constans.URL_BASE_PERSONAS, persona1, Any::class.java)


        Assert.assertEquals(201, result.statusCode.value())

        val result2 = testRestTemplate.getForEntity(Constans.URL_BASE_PERSONAS, CatsList::class.java)
        Assert.assertEquals(1, result2.body!!.filter { t -> t.identificacion == "454545" }.count())


    }

}
