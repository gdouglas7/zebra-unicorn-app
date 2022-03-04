package com.gd.zebraunicornapp

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/vote")
@RestController
class VoteController(
    private val redisTemplate: RedisTemplate<String, String>
) {

    @PostMapping
    fun vote(choice: Choice){

        val vote = "ranking_vote"
        when (choice) {
            Choice.ZEBRA -> { redisTemplate.opsForZSet().incrementScore(vote, Choice.ZEBRA.key, 1.0) }
            Choice.UNICORN ->redisTemplate.opsForZSet().incrementScore(vote, Choice.UNICORN.key, 1.0)
            else -> redisTemplate.opsForZSet().incrementScore(vote, Choice.OTHER.key, 1.0)
        }
    }
}

enum class Choice(val id: Int, val key: String) {
    ZEBRA(1, "zebra"), UNICORN(2, "unicorn"), OTHER(3, "other");
}