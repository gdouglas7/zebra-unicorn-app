package com.gd.zebraunicornapp
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

//
//@Configuration
//class RedisConfig {
//
//    @Bean
//    fun jedisConnectionFactory(): JedisConnectionFactory {
//        val config = RedisStandaloneConfiguration("localhost", 6379)
//        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
//        val factory = JedisConnectionFactory(config, jedisClientConfiguration)
//        factory.afterPropertiesSet()
//        return factory
//    }
//
//    @Bean
//    fun redisTemplate(): RedisTemplate<String, Int> {
//        val template: RedisTemplate<String, Int> = RedisTemplate()
//        template.setConnectionFactory(jedisConnectionFactory())
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()
//        return template
//    }
//}