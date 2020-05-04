package covid19.fukui.datastoreapi.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import covid19.fukui.datastoreapi.domain.model.Rss;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiResponse {
  private BigDecimal latency;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime requestDate;

  private List<Rss> rssList;
}
