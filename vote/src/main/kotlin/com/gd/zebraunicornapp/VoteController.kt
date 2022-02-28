package com.gd.zebraunicornapp

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import redis.clients.jedis.Jedis


@RequestMapping("/api")
@RestController
class VoteController {

    @PostMapping
    fun vote(option: Int){

        val jedis = Jedis("redis", 6379)
        val vote = "ranking_vote"
        val zebra = "zebra"
        val unicorn = "unicorn"
        val other = "other"

        if (option == 1){
            val score = with(jedis.zscore(vote, zebra)){ this ?: 0.0 }
            jedis.zadd(vote, score + 1, zebra)
        }else if(option == 2){
            val score = with(jedis.zscore(vote, unicorn)){ this ?: 0.0 }
            jedis.zadd(vote, score + 1, unicorn)
        }else{
            val score = with(jedis.zscore(vote, other)){ this ?: 0.0 }
            jedis.zadd(vote, score + 1, other)
        }
    }


    @GetMapping("/test")
    fun teste() =  ResponseEntity.ok("test")
}