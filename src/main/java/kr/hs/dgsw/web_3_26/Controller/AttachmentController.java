package kr.hs.dgsw.web_3_26.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController
{
    @PostMapping("/attachment")
    public String upload(@RequestPart MultipartFile uploadFile)
    {
        String destFilename = "C:/Users/Quote/IdeaProjects/web_3_26/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + uploadFile.getOriginalFilename();
        try
        {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            return destFilename;
        }
        catch(Exception e)
        {

        }
        return null;
    }
}
