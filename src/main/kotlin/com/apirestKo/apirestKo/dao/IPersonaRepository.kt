package com.apirestKo.apirestKo.dao

import com.apirestKo.apirestKo.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPersonaRepository:JpaRepository<Persona, Long>