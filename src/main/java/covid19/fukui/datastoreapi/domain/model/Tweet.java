package covid19.fukui.datastoreapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Tweet")
public class Tweet {
  @Field(name = "tweet_id")
  Long tweetId;

  String shop;

  @Field(name = "published_at")
  Long publishedAt;
}
