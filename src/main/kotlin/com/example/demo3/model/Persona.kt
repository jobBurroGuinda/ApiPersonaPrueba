package com.example.demo3.model
import javax.persistence.*
import java.util.*
import org.hibernate.annotations.GeneratorType
import java.time.LocalDate

@Entity
@Table(name = "persona")
data class Persona(var dni:Long = 0, var nombre:String = "", var apellido:String = "",
                   var fechaNac:LocalDate? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}