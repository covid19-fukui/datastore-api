package covid19.fukui.datastoreapi.domain.repository;

import covid19.fukui.datastoreapi.domain.model.Rss;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;

import java.util.List;

public interface RssRepository extends DatastoreRepository<Rss, String> {

  @Query("SELECT * FROM rss ORDER BY published_at DESC LIMIT 20")
  List<Rss> findRecencyTop();
}
