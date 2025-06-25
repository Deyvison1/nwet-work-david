package api.networkn.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Sort.Direction;

import api.networkn.exception.SortByException;

public final class SortByConfig {

	public static Direction getDirection(String sortBy) {
		String[] sortBySplit = validationSortBy(sortBy);
		if (sortBySplit.length == 1) {
			return Direction.ASC;
		} else {
			return setSortByDirection(sortBySplit[1]);
		}
	}

	public static String getFieldName(String sortBy) {
		String[] sortBySplit = validationSortBy(sortBy);
		return sortBySplit[0];
	}

	public static Direction setSortByDirection(String sortBy) {
		if (Strings.isBlank(sortBy)) {
			return Direction.ASC;
		}
		if (sortBy.toUpperCase().equals("DESC")) {
			return Direction.DESC;
		}
		return Direction.ASC;
	}

	private static String[] validationSortBy(String sortBy) {
		String[] sortBySplit;
		if (sortBy == null) {
			throw new SortByException("Sort by is null");
		}
		sortBySplit = sortBy.split(",");
		if (sortBySplit.length == 0) {
			throw new SortByException("Sort by is empty");
		}
		return sortBySplit;
	}
}
