package covid19.fukui.datastoreapi.application.service;

import covid19.fukui.datastoreapi.domain.model.Tweet;
import covid19.fukui.datastoreapi.domain.repository.TweetDataStoreRepository;
import covid19.fukui.datastoreapi.domain.service.TweetDomainService;
import covid19.fukui.datastoreapi.infrastructure.repository.TweetRedisRepositoryImpl;
import covid19.fukui.datastoreapi.presentation.dto.response.TweetApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TweetService {

  private final TweetDataStoreRepository dataStoreRepository;
  private final TweetRedisRepositoryImpl redisRepository;
  private final TweetDomainService tweetDomainService;
  private static final int NUM_SHOP = 20;
  private static final int NUM_TWEET = 200;

  public TweetApiResponse getTweet() {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    TweetApiResponse tweetApiResponse = new TweetApiResponse();
    tweetApiResponse.setRequestDate(LocalDateTime.now());

    Map<String, Long> result = this.fetchCache();

    tweetApiResponse.setTweetCount(result);
    stopWatch.stop();
    tweetApiResponse.setLatency(BigDecimal.valueOf(stopWatch.getTotalTimeSeconds()));
    return tweetApiResponse;
  }

  private Map<String, Long> fetchCache() {
    Map<String, Long> tweetCount =
        redisRepository.getCache().entrySet().stream()
            .filter(s -> s.getValue() > 0)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (tweetCount.size() == 0) {
      List<Tweet> tweets = dataStoreRepository.findRecencyTop(NUM_TWEET);
      tweetCount = tweetDomainService.getTweetCount(tweets, NUM_SHOP);
      redisRepository.insertCache(tweetCount);
    }

    return tweetCount;
  }
}
