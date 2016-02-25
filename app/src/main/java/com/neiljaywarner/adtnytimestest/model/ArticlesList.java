package com.neiljaywarner.adtnytimestest.model;

import java.util.List;

/**
 * Handle result from REST request for retrofit.
 */
public class ArticlesList {

    private List<Article> results;  //from JSON

    public ArticlesList() {
    }


    public List<Article> getResults() {
        return results;
    }


}

// example json
/*
{
id: 131631,
page: 1,
results: [
{
id: "547e6075c3a368256200022f",
author: "anthonypagan1975",
content: "It was good. Although I wish it had more action scenes. It's worth watching ago don't miss out!",
url: "http://j.mp/1vjM0BW"
},
{
id: "54d9b29c9251410a4500100a",
author: "Andres Gomez",
content: "Yet more of the same extended in an inexcusable way. Let's hope the last movie of the saga can get a proper end.",
url: "http://j.mp/16Ki1te"
}
],
total_pages: 1,
total_results: 2
}
 */