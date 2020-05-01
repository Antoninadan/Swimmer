package ua.i.mail100.controller.jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.i.mail100.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//  https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/

@Controller
@Profile("rest")
@RequestMapping("file")
public class JspFileController {
    private static final Logger logger = LoggerFactory.getLogger(JspFileController.class);

//    @Autowired
//    private FileStorageService fileStorageService;

//    @GetMapping("open")
//    public String openFilePage() {
//        return "file";
//    }


    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        FileStorageService fileStorageService = new FileStorageService("C:/path/");
        ;
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
