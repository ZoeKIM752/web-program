package du.common;

import java.io.File;
import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;

import du.board.domain.BoardAttFileVO;
import egovframework.rte.fdl.property.EgovPropertyService;

@Component
@DependsOn(value = "propertiesService")
public class FileUploadUtil {
		
	private static EgovPropertyService propertiesService;
	
	@Resource(name="propertiesService")
	public void setPropertiesService(EgovPropertyService service) {
		propertiesService = service;
	}	

	public static void uploadBoardAttFileVO(BoardAttFileVO attFileVO) throws Exception{
		// 1. filePath
		String fileStorePath = propertiesService.getString("fileStorePath");
		String dailyPath = LocalDate.now().toString();
		String filePath = fileStorePath + dailyPath;
		
		File directory = new File(filePath);
		if(!directory.exists()) {
			directory.mkdir();
		}	
		attFileVO.setFilePath(filePath);
		
		// 2. oldFilename
		MultipartFile multipartFile = attFileVO.getAttFile();
		String originalFilename = multipartFile.getOriginalFilename();
		attFileVO.setOldFilename(originalFilename);
		
		// 3. newFilename and fileSize
		int pos = originalFilename.lastIndexOf(".");		
		String ext = originalFilename.substring(pos);
		String newFilenameBody = Generators.timeBasedGenerator().generate().toString();		
		String newFilename = newFilenameBody + ext;	
		attFileVO.setNewFilename(newFilename);
		attFileVO.setFileSize(multipartFile.getSize());		
		
		// 4. real file copy
		File newFile = new File(filePath + File.separator + newFilename);
		multipartFile.transferTo(newFile);
	}
}
