package covid19.fukui.datastoreapi.domain.service;

import covid19.fukui.datastoreapi.domain.model.Tweet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TweetDomainService {

  public Map<String, Long> getTweetCount(List<Tweet> tweets, int limit) {
    return tweets.stream()
        .collect(Collectors.groupingBy(Tweet::getShop, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .limit(limit)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public Map<String, Long> convertTweetCount(Map<String, String> countStringMap) {
    return countStringMap.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, s -> NumberUtils.toLong(s.getValue(), 0)));
  }

  public Map<String, String> convertStringTweetCount(Map<String, Long> countStringMap) {
    return countStringMap.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, s -> s.getValue().toString()));
  }
}
