package xyz.dsvshx.upload.proxy;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.dsvshx.upload.model.Result;
import xyz.dsvshx.upload.model.UploadFileApi;

/**
 * @author dongzhonghua
 * Created on 2021-04-24
 */
@Service
public class FileClient {
    public Result<String> uploadFile(MultipartFile file,
            String targetPath, String proxyServer) {
        System.out.println("-------------" + proxyServer);
        File f = new File("/tmp/xxx");
        try {
            file.transferTo(f);
            //文件上传
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(proxyServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getOriginalFilename(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), f));
            Response<Result<String>> execute =
                    retrofit.create(UploadFileApi.class).upload(part, targetPath, null)
                            .execute();
            return execute.body();
        } catch (IOException e) {
            return Result.success(e.getMessage());
        }
    }
}