package api.networkn.file.exporter.contract;

import java.util.List;

import org.springframework.core.io.Resource;

import api.networkn.models.dtos.CategoryDTO;

public interface FileExporter {
	Resource exportFile(List<CategoryDTO> listCategoryDTO) throws Exception;
}
