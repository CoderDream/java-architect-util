package com.coderdream.demos.web.util;

public class Test {

    public static void main(String[] args) {
        String url = " https://www.zhipin.com/job_detail/d55956585ae170880Xd609u_FFU~.html?lid=8iXpQvJTt4S.search.1&securityId=-E4FHnpNk2OY_-81sTIdhtXqrYbEJTeUHzuxAf6l";
        System.out.println(url.substring(url.lastIndexOf("/")+1, url.indexOf(".html")));
        System.out.println("d55956585ae170880Xd609u_FFU~");
    }
}
