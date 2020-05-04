package covid19.fukui.datastoreapi.domain.repository;

import covid19.fukui.datastoreapi.domain.model.Rss;

import java.util.List;

public interface RedisRepository {

  void insertCache(List<Rss> rsses);

  List<Rss> getCache();
}
