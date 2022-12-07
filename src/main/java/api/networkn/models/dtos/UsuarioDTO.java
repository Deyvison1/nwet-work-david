package api.networkn.models.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String login;
	private String senha;
	private String role;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
