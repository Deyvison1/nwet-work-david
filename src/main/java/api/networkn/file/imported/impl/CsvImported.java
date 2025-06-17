package api.networkn.file.imported.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import api.networkn.file.imported.contract.FileImported;
import api.networkn.models.dtos.CategoryDTO;

@Component
public class CsvImported implements FileImported {

	@Override
	public List<CategoryDTO> importFile(InputStream inputStream) throws Exception {
		CSVFormat format = CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).setIgnoreEmptyLines(true)
				.setTrim(true).build();

		Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));
		return parseRecordsToCategoryDTO(records);
	}

	private List<CategoryDTO> parseRecordsToCategoryDTO(Iterable<CSVRecord> records) {
		List<CategoryDTO> listCategoryDTO = new ArrayList<CategoryDTO>();

		for (CSVRecord record : records) {
			CategoryDTO dto = new CategoryDTO();
			dto.setDescription(record.get("description"));
			dto.setName(record.get("name"));
			listCategoryDTO.add(dto);
		}
		return listCategoryDTO;
	}
}
