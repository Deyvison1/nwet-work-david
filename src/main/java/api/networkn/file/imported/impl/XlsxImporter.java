package api.networkn.file.imported.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import api.networkn.file.imported.contract.FileImported;
import api.networkn.models.dtos.CategoryDTO;

@Component
public class XlsxImporter implements FileImported {

	@Override
	public List<CategoryDTO> importFile(InputStream inputStream) throws Exception {
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			if (rowIterator.hasNext())
				rowIterator.next();

			return parseRowsToProductDTOList(rowIterator);
		}
	}

	private List<CategoryDTO> parseRowsToProductDTOList(Iterator<Row> rowIterator) {
		List<CategoryDTO> listCategory = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (isRowValid(row)) {
				listCategory.add(parseRowToProductDTO(row));
			}
		}
		return listCategory;
	}

	private CategoryDTO parseRowToProductDTO(Row row) {
		CategoryDTO dto = new CategoryDTO();
		dto.setDescription(row.getCell(0).getStringCellValue());
		dto.setName(row.getCell(1).getStringCellValue());
		return dto;
	}

	private static boolean isRowValid(Row row) {
		return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
	}

}
