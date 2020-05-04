package covid19.fukui.datastoreapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rss")
public class Rss {
  String title;
  String link;

  @Field(name = "published_at")
  Long publishedAt;
}
