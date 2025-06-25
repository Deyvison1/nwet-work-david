package api.networkn.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.networkn.models.dtos.RoleDTO;
import api.networkn.services.IRoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	private final IRoleService service;

	public RoleController(final IRoleService service) {
		this.service = service;
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<RoleDTO> save(RoleDTO dto) {
		return ResponseEntity.ok(service.save(dto));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<RoleDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

}
