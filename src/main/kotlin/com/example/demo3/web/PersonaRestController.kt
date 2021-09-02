package com.example.demo3.web

import com.example.demo3.business.IPersonaBusiness
import com.example.demo3.exception.BusinessException
import com.example.demo3.exception.NotFoundException
import com.example.demo3.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import com.example.demo3.model.Persona
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/*
También se podría declarar como
@Controller
@RequestBody

Este anotador que se implementó funciona a partir de la nueva versión de Spring
 */
@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaRestController {

    @Autowired
    val personaBusiness: IPersonaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Persona>> {
        return try{
            ResponseEntity(personaBusiness!!.list(), HttpStatus.OK)
        } catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPersona:Long): ResponseEntity<Persona> {
        return try {
            ResponseEntity(personaBusiness!!.load(idPersona), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("")
    fun insert(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            val responseHander = HttpHeaders()
            responseHander.set( "location",Constants.URL_BASE_PERSONAS + "/" + persona.id)
            ResponseEntity(responseHander,HttpStatus.CREATED)
        } catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PutMapping("")
    fun update(@RequestBody persona: Persona): ResponseEntity<Any>{
        return try{
            personaBusiness!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPersona: Long): ResponseEntity<Any>{
        return try{
            personaBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}