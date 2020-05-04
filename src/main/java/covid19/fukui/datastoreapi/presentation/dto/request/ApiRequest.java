package covid19.fukui.datastoreapi.presentation.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ApiRequest implements Serializable {

  @NotNull private String media;
}
