package api.networkn.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import api.networkn.models.Role;
import api.networkn.models.dtos.RoleDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleMapper extends BaseEntityMapper<Role, RoleDTO> {

}
