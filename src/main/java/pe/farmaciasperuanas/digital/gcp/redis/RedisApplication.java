package pe.farmaciasperuanas.digital.gcp.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class for running Spring Boot framework.<br/>
 * <b>Class</b>: Application<br/>
 * <b>Copyright</b>: 2022 Farmacias Peruanas.<br/>
 * <b>Company</b>: Farmacias Peruanas.<br/>
 *

 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>carlos calla</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>15/07/2022 RedisApplication Class.</li>
 * </ul>
 * @version 1.0
 */
//@EnableCaching
@SpringBootApplication
public class RedisApplication {

  /**
   * Main method.
   */
  public static void main(String[] args) {
    new SpringApplication(RedisApplication.class).run(args);
  }
}
