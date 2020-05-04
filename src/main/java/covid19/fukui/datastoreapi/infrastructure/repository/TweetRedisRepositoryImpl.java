package covid19.fukui.datastoreapi.infrastructure.repository;

import covid19.fukui.datastoreapi.domain.repository.RedisRepository;
import covid19.fukui.datastoreapi.domain.service.TweetDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
@Slf4j
public class TweetRedisRepositoryImpl implements RedisRepository {

  private final StringRedisTemplate redisTemplate;
  private final TweetDomainService domainService;

  public void insertCache(Map<String, Long> tweetCount) {
    Map<String, String> stringTweetCount = domainService.convertStringTweetCount(tweetCount);
    if (stringTweetCount.size() > 0) {
      redisTemplate.delete(TWEET_KEY);
      redisTemplate.opsForHash().putAll(TWEET_KEY, stringTweetCount);
      redisTemplate.expire(TWEET_KEY, 600, TimeUnit.SECONDS);
    }
  }

  public Map<String, Long> getCache() {
    return domainService.convertTweetCount(
        redisTemplate.<String, String>opsForHash().entries(TWEET_KEY));
  }
}
