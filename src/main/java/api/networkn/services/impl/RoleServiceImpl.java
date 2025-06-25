package api.networkn.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import api.networkn.models.Role;
import api.networkn.models.dtos.RoleDTO;
import api.networkn.models.repository.RoleRepository;
import api.networkn.services.IRoleService;
import api.networkn.utils.mappers.IRoleMapper;

@Service
public class RoleServiceImpl implements IRoleService {

	private final RoleRepository repository;
	private final IRoleMapper mapper;

	public RoleServiceImpl(final RoleRepository repository, final IRoleMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public RoleDTO save(RoleDTO dto) {
		Role entity = repository.save(mapper.toEntity(dto));
		return mapper.toDto(entity);
	}

	@Override
	public List<RoleDTO> findAll() {
		return mapper.toDto(repository.findAll());
	}
}
