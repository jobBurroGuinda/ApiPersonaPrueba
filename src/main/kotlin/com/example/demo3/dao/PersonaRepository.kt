package com.example.demo3.dao

import com.example.demo3.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository: JpaRepository<Persona,Long>