package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {

	public static List<String> readFile(String filename) throws IOException {
		return FileUtils.readLines(new File(filename), "utf-8");
	}
}
