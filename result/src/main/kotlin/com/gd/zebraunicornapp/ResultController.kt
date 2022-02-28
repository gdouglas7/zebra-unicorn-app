package com.gd.zebraunicornapp

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import redis.clients.jedis.Jedis


@RequestMapping("/api")
@RestController
class ResultController {

    @GetMapping
    fun vote(): ResponseEntity<Map<String, Any>> {

        val jedis = Jedis("redis", 6379)
        val vote = "ranking_vote"
        val zebra = "zebra"
        val unicorn = "unicorn"
        val other = "other"

        val list = mapOf(
            zebra to jedis.zrank(vote, zebra),
            unicorn to jedis.zrank(vote, unicorn),
            other to jedis.zrank(vote, other),
        )

        return ResponseEntity.ok(list)
    }


    @GetMapping("/test")
    fun teste() =  ResponseEntity.ok("test")
}