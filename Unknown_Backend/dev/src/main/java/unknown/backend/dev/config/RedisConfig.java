package unknown.backend.dev.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
    private final String HOSTNAME;
    private final Integer PORT;

    private final Integer DATABASE;

    private final String PASSWORD;

    private final Long TIMEOUT;

    public RedisConfig(
            @Value("${redis.host}") String HOSTNAME,
            @Value("${redis.port}") Integer PORT,
            @Value("${redis.database}") Integer DATABASE,
            @Value("${redis.password}") String PASSWORD,
            @Value("${redis.timeout}") Long TIMEOUT
    ){
        this.HOSTNAME = HOSTNAME;
        this.PORT = PORT;
        this.DATABASE = DATABASE;
        this.PASSWORD = PASSWORD;
        this.TIMEOUT = TIMEOUT;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(HOSTNAME);
        config.setPort(PORT);
        config.setDatabase(DATABASE);
        config.setPassword(PASSWORD);

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(java.time.Duration.ofMillis(TIMEOUT))
                .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }

    @Bean
    public StringRedisTemplate stringRedisConnection(
            @Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory
    ){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
