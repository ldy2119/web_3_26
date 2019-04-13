package kr.hs.dgsw.web_3_26.Controller;

import kr.hs.dgsw.web_3_26.Domain.Comment;
import kr.hs.dgsw.web_3_26.Domain.User;
import kr.hs.dgsw.web_3_26.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_3_26.Repository.CommentRepository;
import kr.hs.dgsw.web_3_26.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile uploadFile)
    {
        String storedPath = "C:/Users/Quote/IdeaProjects/web_3_26/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + uploadFile.getOriginalFilename();

        try
        {
            File destFile = new File(storedPath);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            String originName = destFile.getName();
            AttachmentProtocol attachmentProtocol = new AttachmentProtocol(storedPath, originName);
            return attachmentProtocol;
        }
        catch(Exception e)
        {

        }
        return null;
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            String filePath = "";
            String fileName = "";

            if(type.equals("user"))
            {
                User user = userRepository.findById(id).map(found -> {
                    return found;
                }).orElse(null);
                filePath = user.getImagePath();
                fileName = user.getOriginFileName();
            }
            else if(type.equals("comment"))
            {
                Comment user = commentRepository.findById(id).map(found -> {
                    return found;
                }).orElse(null);
                filePath = user.getImagePath();
                fileName = user.getOriginFileName();
            }

            File file = new File(filePath);
            if(!file.exists())
            {
                return;
            }
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null)
                mimeType = "application/octet-stream";

            mimeType = "application/octet-stream";

            res.setContentType(mimeType);
            res.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
            res.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, res.getOutputStream());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
