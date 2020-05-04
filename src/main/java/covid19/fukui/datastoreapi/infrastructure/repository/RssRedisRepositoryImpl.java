package covid19.fukui.datastoreapi.infrastructure.repository;

import covid19.fukui.datastoreapi.domain.model.Rss;
import covid19.fukui.datastoreapi.domain.repository.RedisRepository;
import covid19.fukui.datastoreapi.domain.service.RssDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
@Slf4j
public class RssRedisRepositoryImpl implements RedisRepository {

  private final RssDomainService rssDomainService;
  private final StringRedisTemplate redisTemplate;

  public void insertCache(List<Rss> rsses) {
    if (rsses.size() > 0) {
      List<String> jsonList = rssDomainService.getJsonRss(rsses);
      redisTemplate.delete(RSS_KEY);
      redisTemplate.opsForList().rightPushAll(RSS_KEY, jsonList);
      redisTemplate.expire(RSS_KEY, 600, TimeUnit.SECONDS);
    }
  }

  public List<Rss> getCache() {
    List<String> jsonList = redisTemplate.opsForList().range(RSS_KEY, 0, -1);

    if (StringUtils.isEmpty(jsonList)) {
      return new ArrayList<>();
    }

    return rssDomainService.getRss(jsonList);
  }
}
