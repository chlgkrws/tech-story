package com.blogcode.posts.service;

import com.blogcode.posts.dto.NoticeDTO;
import com.blogcode.posts.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Transactional
    public Long saveNotice(NoticeDTO noticeDTO) {
//        return noticeRepository.save(noticeDTO);
        return noticeRepository.count(); //수정할것
    }
}
