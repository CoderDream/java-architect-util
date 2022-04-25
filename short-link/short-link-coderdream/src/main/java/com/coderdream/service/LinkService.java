package com.coderdream.service;


public interface LinkService {
    String getShortLink(String longLink);

    String getLongLink(String shortLink);
}
