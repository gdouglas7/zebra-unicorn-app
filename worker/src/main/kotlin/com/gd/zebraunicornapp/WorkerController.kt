package com.gd.zebraunicornapp

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/worker")
@RestController
class WorkerController {

    @GetMapping("/test")
    fun teste() =  ResponseEntity.ok("test")
}