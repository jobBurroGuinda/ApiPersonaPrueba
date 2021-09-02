package com.example.demo3

import com.example.demo3.dao.PersonaRepository
import com.example.demo3.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.format.DateTimeFormatter
import java.time.LocalDate

@SpringBootApplication
class Demo3Application:CommandLineRunner{

    @Autowired
    val personaRepository: PersonaRepository? = null

    override fun run(vararg args: String?) {

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val persona1 = Persona(254855, "Job", "Salinas", LocalDate.parse("03-06-1994",formatter))
        
        personaRepository!!.save(persona1)
    }
}

fun main(args: Array<String>) {
    runApplication<Demo3Application>(*args)
}
