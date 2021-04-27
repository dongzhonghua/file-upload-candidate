package xyz.dsvshx.upload.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import xyz.dsvshx.upload.util.HostUtils;

/**
 * @author dongzhonghua
 * Created on 2021-04-24
 */
@Service
public class FileService {
    public String saveFile(MultipartFile file, String targetPath) {
        try {
            //获取上传文件名,包含后缀
            String originalFilename = file.getOriginalFilename();
            //生成保存文件
            File uploadFile = new File(targetPath + "/" + originalFilename);
            //将上传文件保存到路径
            file.transferTo(uploadFile);
            return "机器：" + HostUtils.getHostName() + ", 文件夹：" + uploadFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
