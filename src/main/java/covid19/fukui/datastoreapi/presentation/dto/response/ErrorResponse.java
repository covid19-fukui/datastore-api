package covid19.fukui.datastoreapi.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {
  @JsonProperty("Code")
  private Integer code;

  @JsonProperty("Message")
  private String message;
}
