package covid19.fukui.datastoreapi.domain.repository;

import covid19.fukui.datastoreapi.domain.model.Tweet;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetDataStoreRepository extends DatastoreRepository<Tweet, String> {

  @Query("SELECT * FROM Tweet ORDER BY created_at DESC LIMIT @limit")
  List<Tweet> findRecencyTop(@Param("limit") int limit);
}
