package com.apirestKo.apirestKo.services

import com.apirestKo.apirestKo.model.Persona
import java.util.*

interface IPersosnaServices {

    fun list():List<Persona>
    fun load(id:Long): Optional<Persona>
    fun save(persona:Persona): Persona
    fun remove(id:Long)

}