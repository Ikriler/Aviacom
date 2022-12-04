package com.iproject.aviacom.controllers;

import com.iproject.aviacom.services.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("backup")
public class DBController {

    @GetMapping
    public String getMainPage(Model model) {

        model.addAttribute("restoreFiles", getRestoreList());

        return "backup/main";
    }

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    ServletContext context;

    public List<String> getRestoreList() {

        Path path = Paths.get("dumps");

        File folder = new File(path.toUri());

        List<String> dumps = new ArrayList<>();

        for(File dump: folder.listFiles()) {
            if(dump.isFile()) {
                dumps.add(dump.getName());
            }
        }
        Collections.reverse(dumps);
        return dumps;
    }

    @GetMapping("downloadSQL")
    @Deprecated
    public ResponseEntity<Resource> downloadDB(HttpServletResponse response) {
        String command = String.format("mysqldump -u %s --databases %s > %s",
                "root", "aviacom", "load/aviacom.sql");
        try {
            ConsoleService.exec(command);
            String uri = Paths.get("load/aviacom.sql").toUri().toString();
            Resource resource = resourceLoader.getResource(uri);
            System.out.println(uri);
            ResponseEntity<Resource> body = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                    .contentLength(resource.contentLength())
                    .body(resource);
            return body;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("createDump")
    @Deprecated
    public String createDump(Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_");
        String filename = sdf.format(new Date()) + "aviacom.sql";
        String command = String.format("mysqldump -u %s --databases %s > %s",
                "root", "aviacom", "dumps/" + filename);
        try {
            ConsoleService.exec(command);
            model.addAttribute("restoreFiles", getRestoreList());
            return "backup/main";
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("deleteDump")
    public String deleteDump(Model model, @RequestParam("filename") String filename) throws IOException {

        File file = new File(Paths.get("dumps/" + filename).toUri());

        Files.deleteIfExists(file.toPath());

        model.addAttribute("restoreFiles", getRestoreList());
        return "backup/main";
    }

    @PostMapping("restore")
    @Deprecated
    public String restore(Model model, @RequestParam("filename") String filename) throws IOException {

        File file = new File(Paths.get("dumps/" + filename).toUri());

        String command = String.format("mysql -u %s --one-database aviacom < %s", "root", file.toPath());


        try {
            ConsoleService.exec(command);
            model.addAttribute("restoreFiles", getRestoreList());
            return "backup/main";
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
