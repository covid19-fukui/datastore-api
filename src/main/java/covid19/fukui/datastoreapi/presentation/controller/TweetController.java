package covid19.fukui.datastoreapi.presentation.controller;

import covid19.fukui.datastoreapi.application.service.TweetService;
import covid19.fukui.datastoreapi.presentation.dto.response.TweetApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@AllArgsConstructor
@Slf4j
public class TweetController {

  private final TweetService tweetService;

  @GetMapping(path = "tweetCount")
  public TweetApiResponse getTweetCount() {
    return tweetService.getTweet();
  }
}
