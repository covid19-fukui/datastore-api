package covid19.fukui.datastoreapi.application.service;

import covid19.fukui.datastoreapi.domain.model.Rss;
import covid19.fukui.datastoreapi.domain.repository.RssRepository;
import covid19.fukui.datastoreapi.infrastructure.repository.RedisRepositoryImpl;
import covid19.fukui.datastoreapi.presentation.dto.response.ApiResponse;
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

  private final RssRepository rssRepository;
  private final RedisRepositoryImpl redisRepository;

  public ApiResponse getRssInfo() {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setRequestDate(LocalDateTime.now());

    List<Rss> result = this.fetchCache();

    apiResponse.setRssList(result);
    stopWatch.stop();
    apiResponse.setLatency(BigDecimal.valueOf(stopWatch.getTotalTimeSeconds()));
    return apiResponse;
  }

  private List<Rss> fetchCache() {
    List<Rss> rsses = redisRepository.getCache();
    if (rsses.size() == 0) {
      rsses = rssRepository.findRecencyTop();
      redisRepository.insertCache(rsses);
    }
    return rsses;
  }
}
