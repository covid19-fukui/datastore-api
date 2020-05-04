package covid19.fukui.datastoreapi.domain.repository;

import covid19.fukui.datastoreapi.domain.model.Rss;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RssDataStoreRepository extends DatastoreRepository<Rss, String> {

  @Query("SELECT * FROM rss ORDER BY published_at DESC LIMIT @limit")
  List<Rss> findRecencyTop(@Param("limit") int limit);
}
