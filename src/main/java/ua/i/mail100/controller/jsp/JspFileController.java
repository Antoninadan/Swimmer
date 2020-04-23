package ua.i.mail100.controller.jsp;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@Profile("jsp")
@RequestMapping("file")
public class JspFileController {

    @GetMapping("open")
    public String openFilePage() {
        return "file";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {// имена параметров (тут - "file") - из формы JSP.

        String fileName = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                fileName = file.getOriginalFilename();

                String rootPath = "C:\\path\\";  //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

//                logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                return "You successfully uploaded file=" + fileName;

            } catch (Exception e) {
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + fileName + " because the file was empty.";
        }
    }
}
