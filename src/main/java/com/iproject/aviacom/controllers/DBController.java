package com.iproject.aviacom.controllers;

import com.iproject.aviacom.configs.DBConfig;
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
        DBConfig.initField();
        String command = String.format("mysqldump --no-tablespaces --column-statistics=0 -u%s -p%s -h%s %s > %s",
                DBConfig.username, DBConfig.password, DBConfig.host, DBConfig.dbname, "load/" + DBConfig.dbname + ".sql");

        //String command2 = "mysqldump --no-tablespaces --column-statistics=0 -uf0723938_aviacom -pJ3FnBfhM -hf0723938.xsph.ru f0723938_aviacom > load/f0723938_aviacom.sql";
        try {
            ConsoleService.exec(command);
            String uri = Paths.get("load/" + DBConfig.dbname + ".sql").toUri().toString();
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
        DBConfig.initField();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_");
        String filename = sdf.format(new Date()) + "aviacom.sql";
        String command = String.format("mysqldump --column-statistics=0 -u%s -p%s -h%s --databases %s > %s",
                DBConfig.username, DBConfig.password, DBConfig.host, DBConfig.dbname, "dumps/" + filename);
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
        DBConfig.initField();
        File file = new File(Paths.get("dumps/" + filename).toUri());

        String command = String.format("mysql -u%s -p%s -h%s %s < %s",
                DBConfig.username, DBConfig.password, DBConfig.host, DBConfig.dbname , file.toPath());


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
