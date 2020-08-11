package com.apirestKo.apirestKo.controller

import com.apirestKo.apirestKo.exception.ServicesException
import com.apirestKo.apirestKo.model.Persona
import com.apirestKo.apirestKo.services.IPersosnaServices
import com.apirestKo.apirestKo.utils.Constans
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.util.*

@RestController
@RequestMapping(Constans.URL_BASE_PERSONAS)
class PersonaRestController {

    @Autowired
    val persosnaServices:IPersosnaServices? = null

    @GetMapping
    fun list(): ResponseEntity<List<Persona>>{

        return try {
          ResponseEntity( persosnaServices!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun  load(@PathVariable("id") id:Long): ResponseEntity<Any>{
        val op: Optional<Persona>

        return try {

            op = persosnaServices!!.load(id)

            if(op.isPresent){
                ResponseEntity(op.get(), HttpStatus.OK)
            }else{
                ResponseEntity(HttpStatus.NOT_FOUND)
                //ResponseEntity(Persona(), HttpStatus.OK)
            }


        //}catch (e:ServicesException){
        //    ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (n:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        }
    }

    @PostMapping
    fun  insert(@RequestBody persona:Persona): ResponseEntity<Any>{

        return try {
            persosnaServices!!.save(persona)

            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constans.URL_BASE_PERSONAS + "/" + persona.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)

        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping
    fun  update(@RequestBody persona:Persona): ResponseEntity<Any>{

        return try {
            ResponseEntity( persosnaServices!!.save(persona), HttpStatus.OK)

        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun  delete(@PathVariable("id") id:Long): ResponseEntity<Any>{

        return try {

            ResponseEntity( persosnaServices!!.remove(id), HttpStatus.OK)

        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (n:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}