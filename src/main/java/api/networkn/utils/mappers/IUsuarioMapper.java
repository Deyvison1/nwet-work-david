package api.networkn.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import api.networkn.models.Usuario;
import api.networkn.models.dtos.UsuarioDTO;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUsuarioMapper extends BaseEntityMapper<Usuario, UsuarioDTO> {

}
