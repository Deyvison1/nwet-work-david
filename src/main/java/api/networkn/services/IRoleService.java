package api.networkn.services;

import java.util.List;

import api.networkn.models.dtos.RoleDTO;

public interface IRoleService {
	RoleDTO save(RoleDTO dto);
	List<RoleDTO> findAll();
}
