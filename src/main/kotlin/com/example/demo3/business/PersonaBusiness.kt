package com.example.demo3.business

import com.example.demo3.dao.PersonaRepository
import com.example.demo3.exception.BusinessException
import com.example.demo3.exception.NotFoundException
import com.example.demo3.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class PersonaBusiness: IPersonaBusiness {

    @Autowired
    val personaRepository: PersonaRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Persona> {
        try {
            return personaRepository!!.findAll()
        } catch (e:Exception){
            throw  BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idPersona: Long): Persona {
        val op: Optional<Persona>
        try {
            op = personaRepository!!.findById(idPersona)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }
        // Si el valor no está presente en la base de datos...
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona con id $idPersona")
        }

        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(persona: Persona): Persona {
        try {
            return personaRepository!!.save(persona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun remove(idPersona: Long) {
        val op: Optional<Persona>
        try {
            op = personaRepository!!.findById(idPersona)
        } catch (e:Exception){
            throw BusinessException(e.message)
        }
        // Si el valor no está presente en la base de datos...
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona con id $idPersona")
        }else{
            try {
                personaRepository!!.deleteById(idPersona)
            } catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

    }
}