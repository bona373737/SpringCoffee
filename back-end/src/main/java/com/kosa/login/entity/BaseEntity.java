package com.kosa.login.entity;

import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//엔티티 작업중 데이터의 등록시간이나 수정시간등이 자동으로 추가가되고 변경해야하는 컬럼이있는데 이를 자동으로 처리할 수 있도록 추상클래스로 설정
@MappedSuperclass //테이블로 생성 x
@EntityListeners(value = {AuditingEntityListener.class})//jpa내부에서 엔티티 객체가 생성/변경되는 것을 감지하는 역할 = 리스너
@Getter
abstract public class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;


    @LastModifiedDate
    @Column(name = "modddate")
    private LocalDateTime modDate;
}
