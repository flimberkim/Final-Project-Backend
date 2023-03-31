package com.project.finalproject.applicant.service;

import com.project.finalproject.applicant.dto.request.InfoUpdateRequestDTO;
import com.project.finalproject.applicant.dto.request.SignupRequestDTO;
import com.project.finalproject.applicant.dto.response.MyInfoResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public interface ApplicantService {

    public String checkEmail(String applicantEmail);

    public String signup(SignupRequestDTO signupRequestDTO);

    public MyInfoResponseDTO myInfo(Long applicantID);

    public String infoUpdate(InfoUpdateRequestDTO infoUpdateRequestDTO);



    public String applyJobpost(Long jobpostId) throws IOException;

    public String cancelApplyJobpost(Long jobpostId) throws IOException;



    public String resumeSave(MultipartFile files) throws IOException;

    public ResponseEntity<Resource> resumeDownload() throws IOException;

    public String resumeDelete() throws IOException;

}