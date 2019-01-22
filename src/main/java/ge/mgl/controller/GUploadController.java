package ge.mgl.controller;


import ge.mgl.service.GStorageService;
import ge.mgl.utils.MGLIOUtils;
import ge.mgl.utils.MGLStringUtils;
import ge.mgl.utils.RequestResponseWithSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MJaniko on 5/2/2017.
 */
@RestController
@RequestMapping("/upload")
public class GUploadController {

    @Autowired
    private GStorageService storageService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<String> listUploadedFiles() throws IOException {
        return storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(GUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ResponseBody
    public RequestResponseWithSource<List<String>> uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files,
                                                                             @RequestParam("type") String object
    ) {
        RequestResponseWithSource<List<String>> response  = new RequestResponseWithSource<>(true);
        if (files.length == 0 || MGLStringUtils.IsNullOrBlank(object)){
            response.setMessage("File size is null or empty.");
            response.setSuccess(false);
            return response;
        }
        if (!MGLIOUtils.limitFileSize(files)) {
            response.setMessage("Wrong file size.");
            response.setSuccess(false);
            return response;
        }
        if (!MGLIOUtils.isValidObjectPath(object)) {
            response.setMessage("This file path is not valid.");
            response.setSuccess(false);
            return response;
        }

        List<String> fileList = new ArrayList<>();
        for(MultipartFile file : files){
            fileList.add(storageService.storeEncoded(file));
        }

        response.setSource(fileList);
        return response;
    }
}
