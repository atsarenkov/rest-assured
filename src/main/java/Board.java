import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Board {
    private String id;
    private String idOrganization;
    @JsonProperty("prefs_permissionLevel")
    private String permissionLevel;
    @JsonProperty("prefs_background")
    private String background;
}
