package api.networkn.file.imported.contract;

import java.io.InputStream;
import java.util.List;

import api.networkn.models.dtos.CategoryDTO;

public interface FileImported {

	List<CategoryDTO> importFile(InputStream inputStream) throws Exception;
}
