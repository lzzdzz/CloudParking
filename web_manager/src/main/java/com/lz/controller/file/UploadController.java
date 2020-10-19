package com.lz.controller.file;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private HttpServletRequest Request;

    @PostMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile file){

        String path = Request.getSession().getServletContext().getRealPath("img");
        String filePath = path+"/"+file.getOriginalFilename();
        File desFile= new File(filePath);
        if (desFile.getParentFile().exists()){
            desFile.mkdir();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://localhost:9102/img/"+file.getOriginalFilename();
    }

    @Autowired
    private OSSClient ossClient;

    @PostMapping("/oss")
    public String ossUpload(@RequestParam("file") MultipartFile file,String folder){
        String buketName = "cloudparkin";
        String fileName = folder+"/"+UUID.randomUUID() +file.getOriginalFilename();

        try {
            ossClient.putObject(buketName,fileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://"+buketName+".oss-cn-beijing.aliyuncs.com/"+fileName;
    }
}
