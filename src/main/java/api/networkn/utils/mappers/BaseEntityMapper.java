package api.networkn.utils.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface BaseEntityMapper<E, D> {
	D toDto(E entity);

	E toEntity(D dto);

	@Mapping(target = "id", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	E toEntity(D dto, @MappingTarget E entity);

	List<D> toDto(List<E> entities);

	List<D> toDto(Iterable<E> entities);

	List<E> toEntity(List<D> dtos);
}