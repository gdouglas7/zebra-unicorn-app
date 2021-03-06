package com.gd.zebraunicornapp

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ChoiceService(
    private val choiceRepository: ChoiceRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {

    @Scheduled(fixedRate = 3000)
    fun fixedRateScheduledTask() {

        val vote = "ranking_vote"

        runCatching {
            while(true){

                val zebraPosition = with(redisTemplate.opsForZSet().reverseRank(vote, ChoiceEnum.ZEBRA.key)){ this ?: 0 } + 1
                val unicornPosition = with(redisTemplate.opsForZSet().reverseRank(vote, ChoiceEnum.UNICORN.key)){ this ?: 0 } + 1
                val otherPosition = with(redisTemplate.opsForZSet().reverseRank(vote, ChoiceEnum.OTHER.key)){ this ?: 0 } + 1

                val zebraScore = with(redisTemplate.opsForZSet().score(vote, ChoiceEnum.ZEBRA.key)){ this ?: 0.0 }
                val unicornScore = with(redisTemplate.opsForZSet().score(vote, ChoiceEnum.UNICORN.key)){ this ?: 0.0 }
                val otherScore = with(redisTemplate.opsForZSet().score(vote, ChoiceEnum.OTHER.key)){ this ?: 0.0 }

                val zebraChoiceEnum = choiceRepository.findByName(ChoiceEnum.ZEBRA.key)
                    ?.copy(score = zebraScore.toInt(), position = zebraPosition.toInt())
                    ?: Choice(
                        name = ChoiceEnum.ZEBRA.key,
                        score = zebraScore.toInt(),
                        position = zebraPosition.toInt()
                    )
                choiceRepository.save(zebraChoiceEnum)

                val unicornChoiceEnum = choiceRepository.findByName(ChoiceEnum.UNICORN.key)
                    ?.copy(score = unicornScore.toInt(), position = unicornPosition.toInt())
                    ?: Choice(
                        name = ChoiceEnum.UNICORN.key,
                        score = unicornScore.toInt(),
                        position = unicornPosition.toInt()
                    )
                choiceRepository.save(unicornChoiceEnum)

                val otherChoiceEnum = choiceRepository.findByName(ChoiceEnum.OTHER.key)
                    ?.copy(score = otherScore.toInt(), position = otherPosition.toInt())
                    ?: Choice(
                        name = ChoiceEnum.OTHER.key,
                        score = otherScore.toInt(),
                        position = otherPosition.toInt()
                    )
                choiceRepository.save(otherChoiceEnum)

            }
        }.onFailure {
            println("failure: $it")
        }
    }
}

enum class ChoiceEnum(val id: Int, val key: String) {
    ZEBRA(1, "zebra"), UNICORN(2, "unicorn"), OTHER(3, "other");
}