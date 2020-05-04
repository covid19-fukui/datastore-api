package covid19.fukui.datastoreapi.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import covid19.fukui.datastoreapi.domain.model.Rss;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RssDomainService {

  private final ObjectMapper mapper;

  public List<String> getJsonRss(List<Rss> rsses) {
    List<String> jsonRss = new ArrayList<>();
    try {
      for (Rss rss : rsses) {
        jsonRss.add(mapper.writeValueAsString(rss));
      }
    } catch (JsonProcessingException e) {
      log.error("JSONエンコードに失敗しました", e);
    }
    return jsonRss;
  }

  public List<Rss> getRss(List<String> jsonRss) {
    List<Rss> rsses = new ArrayList<>();
    try {
      for (String json : jsonRss) {
        rsses.add(mapper.readValue(json, Rss.class));
      }
    } catch (JsonProcessingException e) {
      log.error("JSONデコードに失敗しました", e);
    }
    return rsses;
  }
}
