package pe.farmaciasperuanas.digital.gcp.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisPrimaryProperty extends RedisCommonProperty {
}
