package xyz.dsvshx.upload.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.nacos.api.exception.NacosException;

import xyz.dsvshx.upload.model.InstanceResult;
import xyz.dsvshx.upload.model.Result;
import xyz.dsvshx.upload.proxy.FileClient;
import xyz.dsvshx.upload.service.FileService;
import xyz.dsvshx.upload.service.NacosService;
import xyz.dsvshx.upload.util.HostUtils;

/**
 * @author dongzhonghua
 * Created on 2021-04-22
 */
@RestController
public class MainController {

    @Autowired
    private NacosService nacosService;

    @Autowired
    private FileClient fileClient;

    @Autowired
    private FileService fileService;


    @RequestMapping(value = "all-instance", method = RequestMethod.GET)
    public Result<InstanceResult> allInstance() throws NacosException {
        InstanceResult res = new InstanceResult(HostUtils.getHostName(), nacosService.getAllHostInfo());
        System.out.println(res);
        return Result.success(res);
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("targetPath") String targetPath,
            @RequestParam(value = "proxyServer", required = false) String proxyServer) {
        if (StringUtils.isNotBlank(proxyServer)) {
            // 不是本机，代理转发
            return fileClient.uploadFile(file, targetPath, proxyServer);
        } else {
            // 本机，直接存储文件
            return Result.success(fileService.saveFile(file, targetPath));
        }
    }
}
