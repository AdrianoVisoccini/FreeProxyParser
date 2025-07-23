package com.habrai.bot.service.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.habrai.bot.pojo.gigachat.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface GigachatService {

    String getResponse(String request, String attachment);

    FileResponse uploadFile(MultipartFile file);

    String deleteFile(String fileId);
}
