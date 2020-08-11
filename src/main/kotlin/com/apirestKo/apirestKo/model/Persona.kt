package com.apirestKo.apirestKo.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Personas")
data class Persona(var identificacion:String? = null, var nombre:String? = null,
                   val apellidos:String?= null, var fechaNacimiento:LocalDate? = null)        {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}