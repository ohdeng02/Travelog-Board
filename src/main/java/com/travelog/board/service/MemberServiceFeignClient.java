package com.travelog.board.service;

import com.travelog.board.dto.BoardBookmarkDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "members")
public interface MemberServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/bookmark/isBookmark")
    String isBookmark(@RequestBody BoardBookmarkDto dto);
}
