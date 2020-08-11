package com.apirestKo.apirestKo.services

import com.apirestKo.apirestKo.dao.IPersonaRepository
import com.apirestKo.apirestKo.exception.NotFoundException
import com.apirestKo.apirestKo.exception.ServicesException
import com.apirestKo.apirestKo.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*
import javax.transaction.Transactional

@Service
class PersonaServicesImpl:IPersosnaServices {

    @Autowired
    val personaRepository: IPersonaRepository? = null

    @Throws(ServicesException::class)
    override fun list(): List<Persona> {

        try {
            return personaRepository!!.findAll()

        }catch (e:Exception){
            throw ServicesException(e.message)
        }
    }

    @Throws(ServicesException::class,ServicesException::class)
    override fun load(id: Long): Optional<Persona> {

        val op: Optional<Persona>

        try {
            op = personaRepository!!.findById(id)

        }catch (e:Exception){
            throw ServicesException(e.message)
        }

       /* if(!op.isPresent){
            throw NotFoundException("No se enconto persona con el id $id")
        }*/

        return  op
    }

    @Throws(ServicesException::class)
    @Transactional
    override fun save(persona: Persona): Persona {

        try {
            return  personaRepository!!.save(persona)

        }catch (e:Exception){
            throw ServicesException(e.message)
        }
    }

    @Throws(ServicesException::class, NotFoundException::class)
    override fun remove(id: Long) {

        val op: Optional<Persona>

        try {
            op = personaRepository!!.findById(id)

        }catch (e:Exception){
            throw ServicesException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se enconto persona con el id $id")
        }else{

            try {
                personaRepository!!.deleteById(id)

            }catch (e:Exception){
                throw ServicesException(e.message)
            }

        }
    }


}