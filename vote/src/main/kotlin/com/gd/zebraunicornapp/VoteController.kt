package com.gd.zebraunicornapp

//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.core.ValueOperations
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import redis.clients.jedis.Jedis


@RequestMapping("/api")
@RestController
class VoteController(
//    private val redisTemplate: RedisTemplate<String, Int>
) {

//    @PostMapping
//    fun vote(option: Int){
//        println("vote: $option")
//
//        val ops = redisTemplate.opsForValue()
//        ops.increment("myKey:", option.toLong())
//        println("incremented")
//    }

    @PostMapping
    fun vote(option: Int){
        println("vote: $option")

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


        println("zebra position ${jedis.zrank(vote, zebra)}")
        println("unicorn position ${jedis.zrank(vote, unicorn)}")
        println("other position ${jedis.zrank(vote, other)}")
    }


    @GetMapping("/test")
    fun teste() =  ResponseEntity.ok("test")
}