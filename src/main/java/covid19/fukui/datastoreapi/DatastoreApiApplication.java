package covid19.fukui.datastoreapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DatastoreApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(DatastoreApiApplication.class, args);
  }
}
