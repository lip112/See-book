package com.example.seebook.global.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.awt.event.AWTEventListener;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass : 하위 클래스에게 매핑되는 정보를 상위클래스에 지정
 * 상위클래스에서는 따로 분리되는 테이블을 갖지 않는다.
 * @EntityListeners : auditing을 여러 클래스에 추가하는 경우, @EntityListener를
 * 활용해서 코드를 한군데 모아둘 수 있다.
 * @CreatedDate : 필드를 갖고 있는 엔티티의 생성날짜 추가
 * @@LastModifiedDate : 필드를 갖고 있는 엔티티의 최근 수정날짜 추가
 */
@Getter
@MappedSuperclass
@EntityListeners(AWTEventListener.class)
public class BaseTime {
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
