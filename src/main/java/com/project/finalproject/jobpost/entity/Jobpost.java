package com.project.finalproject.jobpost.entity;

import com.project.finalproject.applicant.entity.enums.Sector;
import com.project.finalproject.company.entity.Company;
import com.project.finalproject.term.entity.enums.TermStatus;
import com.project.finalproject.term.entity.enums.TermType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 공고
 */
@Entity
@Table(name = "tb_jobpost")
public class Jobpost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobpost_id")
    private Long id; //채용공고 ID

    @Column(name = "jobpost_itle")
    private String content; //채용공고 내용

    @Column(name = "jobpost_education")
    @Enumerated(EnumType.STRING) //"고졸, 초대졸, 대졸, 석박사"
    private TermType type; //채용공고 최소학력 요구사항

    @Column(name = "jobpost_work_experience")
    private String version; //채용공고 최소경력 요구사항

    @Column(name = "jobpost_start_date")
    private LocalDateTime startDate; //채용공고 시작일

    @Column(name = "jobpost_due_date")
    private LocalDateTime dueDate; //채용공고 마감일

    @CreatedDate
    @Column(name = "jobpost_create_date",updatable = false)
    private String createDate; //채용공고 생성일

    @LastModifiedDate
    @Column(name = "jobpost_edit_date")
    private String editDate; //채용공고 수정일

    @Column(name = "jobpost_status")
    @Enumerated(EnumType.STRING)
    private TermStatus termsStatus; //채용공고 상태

    @Column(name = "jobpost_recruit_num")
    private TermStatus recruitNum; //채용공고 모집인원

    @Column(name= "jobpost_filepath")
    private String filepath; //채용공고 파일경로

    @Column(name= "jobpost_sector")
    private Sector sector; //채용공고 직무

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company; //채용공고를 작성한 기업회원
}
