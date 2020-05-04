package covid19.fukui.datastoreapi.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class TweetApiResponse {
  private BigDecimal latency;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime requestDate;

  private List<Shop> shopList;

  @Data
  @AllArgsConstructor
  public static class Shop {
    private String name;
    private Long num;
  }
}
