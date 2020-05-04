package covid19.fukui.datastoreapi.application.service;

import covid19.fukui.datastoreapi.domain.model.Rss;
import covid19.fukui.datastoreapi.domain.repository.RssDataStoreRepository;
import covid19.fukui.datastoreapi.infrastructure.repository.RssRedisRepositoryImpl;
import covid19.fukui.datastoreapi.presentation.dto.response.RssApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RssService {

  private final RssDataStoreRepository rssDataStoreRepository;
  private final RssRedisRepositoryImpl redisRepository;
  private static final int NUM_PAPER = 20;

  public RssApiResponse getRssInfo() {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    RssApiResponse rssApiResponse = new RssApiResponse();
    rssApiResponse.setRequestDate(LocalDateTime.now());

    List<Rss> result = this.fetchCache();

    rssApiResponse.setRssList(result);
    stopWatch.stop();
    rssApiResponse.setLatency(BigDecimal.valueOf(stopWatch.getTotalTimeSeconds()));
    return rssApiResponse;
  }

  private List<Rss> fetchCache() {
    List<Rss> rsses = redisRepository.getCache();
    if (rsses.size() == 0) {
      rsses = rssDataStoreRepository.findRecencyTop(NUM_PAPER);
      redisRepository.insertCache(rsses);
    }
    return rsses;
  }
}
