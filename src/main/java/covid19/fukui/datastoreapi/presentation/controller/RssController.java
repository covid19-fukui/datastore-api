package covid19.fukui.datastoreapi.presentation.controller;

import covid19.fukui.datastoreapi.application.service.RssService;
import covid19.fukui.datastoreapi.presentation.dto.response.ApiResponse;
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
public class RssController {

  private final RssService rssService;

  @GetMapping(path = "rss/fukuishimbun")
  public ApiResponse getRssInfo() {
    return rssService.getRssInfo();
  }
}
