package com.example.billspliter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class BillSpliterApplication

fun main(args: Array<String>) {
    runApplication<BillSpliterApplication>(*args)
}