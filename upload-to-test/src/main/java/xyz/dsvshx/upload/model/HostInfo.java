package xyz.dsvshx.upload.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dongzhonghua
 * Created on 2021-04-24
 */
@Data
@AllArgsConstructor
public class HostInfo {
    private String hostname;
    private String ip;
    private int port;
    private String homePageUrl;
}
