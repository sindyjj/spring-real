package com.spring.basic.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.basic.dto.ScoreListResponseDTO;
import com.spring.basic.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.IScoreMapper;
import com.spring.basic.score.repository.IScoreRepository;

import lombok.RequiredArgsConstructor;

//컨트롤러와 레파지토리 사이에 배치되어 기타 비지니스 로직 처리
//ex) 값을 가공, 예외 처리, dto로 변환, 트랜잭션 등등...
@Service // 빈 등록
@RequiredArgsConstructor
public class ScoreService {
	
	private final IScoreMapper scoreRepository;
	
//	@Autowired
//	public ScoreService(@Qualifier("spring") IScoreRepository scoreRepository) {
//		this.scoreRepository = scoreRepository;
//	}
	
	//등록 중간처리
	//컨트롤러는 나에게 DTO를 줬어.
	//하지만, 온전한 학생의 정보를 가지는 객체는 -> Score(Entity)
	//내가 Entity를 만들어서 넘겨줘야겠다.
	public void insertScore(ScoreRequestDTO dto) {
		Score score = new Score(dto);
		//Entity를 완성했으니, Repository에게 전달해서 DB에 넣자.
		scoreRepository.save(score);
	
	
	}

	public List<ScoreListResponseDTO> getlist() {
		List<ScoreListResponseDTO> dtoList = new ArrayList<>();
		List<Score> scoreList =  scoreRepository.findAll();
		for(Score s : scoreList) {
			ScoreListResponseDTO dto = new ScoreListResponseDTO(s); 
			// Entity를 DTO로 변환
			dtoList.add(dto); // 변환한 Dto를 DTO list에 추가
		}
		return dtoList;
		
	}

	public Score retrieve(int stuNum) {
		//응답하는 화면에 맞는 DTO를 선언해서 주어야 하는 것이 원칙.
		//만약에 Score 전체 데이터가 필요한ㄴ 것이 아니라면
		//몇 개만 추리고 가공할 수 있는 DTO를 설게해서 리턴하는 것이 맞습니다.
		return scoreRepository.findByStuNum(stuNum);
		
	}

	public void delete(int stuNum) {
		scoreRepository.deleteBystuNum(stuNum);
		
	}
	
	public void modify(int stuNum, ScoreRequestDTO dto) {
        Score score = scoreRepository.findByStuNum(stuNum);
        score.changeScore(dto); // 밑에 3줄을 요약하는 문
//        dto.setName(score.getStuName());
//        Score modScore = new Score(dto);
//        modScore.setStuNum(stuNum);

        scoreRepository.modify(score); 

    }
	
	

}
