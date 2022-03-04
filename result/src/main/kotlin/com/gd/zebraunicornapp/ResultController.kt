package com.gd.zebraunicornapp

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/result")
@RestController
class ResultController(
    private val choiceRepository: ChoiceRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {

    @GetMapping("/ranking")
    fun cacheRanking(): ResponseEntity<Map<String, Any>> {

        val vote = "ranking_vote"

        val list = mapOf(
            ChoiceEnum.ZEBRA.key to with(redisTemplate.opsForZSet().reverseRank(vote, ChoiceEnum.ZEBRA.key)){ this ?: 0 } + 1,
            ChoiceEnum.UNICORN.key to with(redisTemplate.opsForZSet().reverseRank(vote, ChoiceEnum.UNICORN.key)){ this ?: 0 } + 1,
            ChoiceEnum.OTHER.key to with(redisTemplate.opsForZSet().reverseRank(vote, ChoiceEnum.OTHER.key)){ this ?: 0 } + 1,
        )

        return ResponseEntity.ok(list)
    }

    @GetMapping("/votes")
    fun ranking() =  ResponseEntity.ok(choiceRepository.findAll())
}

enum class ChoiceEnum(val id: Int, val key: String) {
    ZEBRA(1, "zebra"), UNICORN(2, "unicorn"), OTHER(3, "other");
}