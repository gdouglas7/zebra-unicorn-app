package com.gd.zebraunicornapp

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/result")
@RestController
class ResultController(private val choiceRepository: ChoiceRepository) {

    @GetMapping("/votes")
    fun ranking() =  ResponseEntity.ok(choiceRepository.findAll())
}