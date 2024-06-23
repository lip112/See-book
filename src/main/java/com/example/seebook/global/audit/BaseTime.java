package com.example.seebook.global.audit;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modifiedDate;

    //persist() 메소드를 호출해서 엔티티를 영속성 컨텍스트에 관리하기 직전에 호출된다.
    @PrePersist
    public void onPrePersist() {
        String customLocalDateTimeFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime parsedCreateDate = LocalDateTime.parse(customLocalDateTimeFormat, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.createdDate = parsedCreateDate;
    }


    //flush나 commit을 호출해서 엔티티를 데이터베이스에 수정하기 직전에 호출된다.
    @PreUpdate
    public void onPreUpdate() {
        String customLocalDateTimeFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime parsedModifiedDate = LocalDateTime.parse(customLocalDateTimeFormat, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.modifiedDate = parsedModifiedDate;
    }

}
