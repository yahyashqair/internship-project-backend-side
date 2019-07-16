package com.exult.ProjectCisco.controller;

import com.exult.ProjectCisco.dto.FileResponse;
import com.exult.ProjectCisco.service.Storage.StorageService;
import com.exult.ProjectCisco.service.deviceLoader.DeviceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

@Controller
@RestController
@RequestMapping("loader")
public class DeviceLoaderController {


    @Autowired
    private DeviceLoader deviceLoader;
    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/{filename:.+}")
    public String loadDevicePackagesFromFile(@PathVariable() String filename) throws ParserConfigurationException, SAXException, IOException, ZipException {
        return storageService.unzipedFileAndLoaded(filename);
    }
    @PostMapping("/zip")
    @ResponseBody
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();
        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

}
