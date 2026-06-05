package com.example.pp5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
//import java.util.*; // util内のクラスをまとめてインポート

import com.example.pp5.model.Sketch;

@RestController//自動でjsonを返す
@RequestMapping("/api")
public class GemController {

    @GetMapping("/test")
    public Map<String, float[][][][]> testV(){
        Sketch s = new Sketch();
        float[][][][] vs = s.voronoiBox();
        return Map.of("vertices",vs);
    }
}