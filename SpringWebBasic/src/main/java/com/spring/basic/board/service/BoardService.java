package com.spring.basic.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.basic.board.controller.BoardModifyRequestDTO;
import com.spring.basic.board.dto.BoardListResponseDTO;
import com.spring.basic.board.entity.Board;
import com.spring.basic.board.repository.IBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final IBoardMapper mapper;

	public void insertArticle(String writer, String title, String content) {
		Board board = new Board();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		mapper.insertArticle(board);
		
	}

	public List<BoardListResponseDTO> getArticles() {
		List<BoardListResponseDTO> dtoList = new ArrayList<>();
		List<Board> boardList = mapper.getArticles(); // 전체 글 목록을 가져와라
		for(Board b : boardList) {
			BoardListResponseDTO dto = new BoardListResponseDTO(b);
			dtoList.add(dto);
		}
		return dtoList;
		
	}

	public Board retrieve(int boardNo) {
		return mapper.getArticle(boardNo);
		
	}

	public void modify(BoardModifyRequestDTO dto) {
//		mapper.updateArticle(board);
		
						
		mapper.updateArticle(Board.builder()
									.boardNo(dto.getBoardNo())
									.writer(dto.getWriter())
									.title(dto.getTitle())
									.content(dto.getContent())
									.build());
	}

	public void deleteArticle(int boardNo) {
		mapper.deleteArticle(boardNo);
		
	}
	
}
