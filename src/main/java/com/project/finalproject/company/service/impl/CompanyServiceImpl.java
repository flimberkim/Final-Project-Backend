package com.project.finalproject.company.service.impl;

import com.project.finalproject.company.dto.CompanyJobpostResponse;
import com.project.finalproject.company.entity.Company;
import com.project.finalproject.company.exception.CompanyException;
import com.project.finalproject.company.exception.CompanyExceptionType;
import com.project.finalproject.company.repository.CompanyRepository;
import com.project.finalproject.company.service.CompanyService;
import com.project.finalproject.jobpost.entity.Jobpost;
import com.project.finalproject.jobpost.exception.JobpostException;
import com.project.finalproject.jobpost.exception.JobpostExceptionType;
import com.project.finalproject.jobpost.repository.JobpostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final JobpostRepository jobpostRepository;

    /**
     * 기업 본인이 작성한 채용공고 목록 조회
     * @param companyEmail : 기업 email
     * @return 채용 공고 리스트
     */
    @Override
    public List<CompanyJobpostResponse.ShortDTO> showJobpostList(String companyEmail) {

        Company company = companyRepository.findByEmail(companyEmail).orElseThrow(
                () -> new CompanyException(CompanyExceptionType.NOT_FOUND_USER)
        );

        List<Jobpost> jobposts = jobpostRepository.findByCompanyId(company.getId());

        return jobposts.stream()
                .map(CompanyJobpostResponse.ShortDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * 채용 공고 단건 조회
     * @param companyEmail : 회사 이메일
     * @param postId : 게시글 아이디
     * @return 채용 공고 내용 상세 출력
     */
    @Override
    public CompanyJobpostResponse.LongDTO showJobpostDetail(String companyEmail, Long postId) {

        //사용자 확인용
        Company company = companyRepository.findByEmail(companyEmail).orElseThrow(
                () -> new CompanyException(CompanyExceptionType.NOT_FOUND_USER)
        );

        //postId로 게시글 조회
        Jobpost jobpost = jobpostRepository.findById(postId).orElseThrow(
                () -> new JobpostException(JobpostExceptionType.NOT_FOUND_PAGE)
        );

        return CompanyJobpostResponse.LongDTO.builder()
                .jobpost(jobpost)
                .build();
    }
}