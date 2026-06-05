package com.example.pp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    //サイトが読み込まれる順番
    //サイトのURLを読み込むとstatic/index.htmlを一番はじめに探す
    //なかったら@GetMapping("/")//http://localhost:8080/がトップになる
    //static/index.htmlが存在すると/は優先度下なので実行されずにトップにならない
    //static/index.htmlは自動で最初に読み込まれるからここに書く必要も無い
    //@Controllerはresources/templates/の中だけを見にいく
    //static/の中にあるものがデベロッパーツールで見える範囲でリンクページ遷移とかファイルの管理と構造は静的と同じ
    //static/の外のものは隠れて見えなくなるしファイル管理も静的と違うのでリンクで移動できないしコントローラー管理下から移動することになる

    //@GetMapping("/")//http://localhost:8080/
    //public String index() {
    //    return "test"; // templates/←"".html を探しに行って表示する
    //}
}