package pe.farmaciasperuanas.digital.gcp.redis.application.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class ResourceConfig {

    private ResourceLoader resourceLoader;

    @Autowired
    public ResourceConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getBase64ScriptApi(String dataScript) {

        return Base64.getEncoder().encodeToString(dataScript.getBytes());
    }

    public String getStringScriptApi() {

        return getStringFileContent();
    }

    private String getStringFileContent() {
        String data="";

        try {

            Resource resource = resourceLoader.getResource("classpath:files/dto.json");

            InputStream fileAsStream = resource.getInputStream();

            byte[] bdata = FileCopyUtils.copyToByteArray(fileAsStream);
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("IOException", e);
        }

        return data;
    }
}
