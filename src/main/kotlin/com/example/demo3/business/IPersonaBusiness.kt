package com.example.demo3.business

import com.example.demo3.model.Persona

interface IPersonaBusiness {

    fun list(): List<Persona>
    fun load(idPersona:Long): Persona
    fun save(persona:Persona): Persona
    fun remove(idPersona: Long)

}