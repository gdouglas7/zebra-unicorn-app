package com.gd.zebraunicornapp

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis

@Service
class ChoiceService(
    private val choiceRepository: ChoiceRepository
) {

    @Scheduled(fixedRate = 3000)
    fun fixedRateScheduledTask() {

        val jedis = Jedis("redis", 6379)

        val vote = "ranking_vote"
        val zebra = "zebra"
        val unicorn = "unicorn"
        val other = "other"

        runCatching {
            while(true){
                val zebraScore = with(jedis.zscore(vote, zebra)){ this ?: 0.0 }
                val unicornScore = with(jedis.zscore(vote, unicorn)){ this ?: 0.0 }
                val otherScore = with(jedis.zscore(vote, other)){ this ?: 0.0 }

                val zebraChoice = choiceRepository.findByName(zebra)?.copy(score = zebraScore.toInt())
                    ?: Choice(
                        name = zebra,
                        score = zebraScore.toInt()
                    )
                choiceRepository.save(zebraChoice)

                val unicornChoice = choiceRepository.findByName(unicorn)?.copy(score = unicornScore.toInt())
                    ?: Choice(
                        name = unicorn,
                        score = unicornScore.toInt()
                    )
                choiceRepository.save(unicornChoice)

                val otherChoice = choiceRepository.findByName(other)?.copy(score = otherScore.toInt())
                    ?: Choice(
                        name = other,
                        score = otherScore.toInt()
                    )
                choiceRepository.save(otherChoice)

            }
        }.onFailure {
            println("failure: $it")
        }
    }
}