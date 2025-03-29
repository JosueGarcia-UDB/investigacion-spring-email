package sv.org.superate.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.org.superate.controller.DTO.request.PrestamoRequest;
import sv.org.superate.controller.DTO.response.PrestamoResponse;
import sv.org.superate.repository.domain.Prestamo;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {

    @Mapping(target = "id", ignore = true)
    Prestamo toEntity(PrestamoRequest request);

    PrestamoResponse toResponse(Prestamo prestamo);
}