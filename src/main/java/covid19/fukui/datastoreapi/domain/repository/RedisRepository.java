package covid19.fukui.datastoreapi.domain.repository;

import covid19.fukui.datastoreapi.domain.model.Rss;

import java.util.List;
import java.util.Map;

public interface RedisRepository {

  public static final String RSS_KEY = "paper";
  public static final String TWEET_KEY = "tweet";
}
