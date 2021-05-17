package poly.service;

import java.util.List;

import poly.dto.BoardDTO;

public interface IBoardService {
	
	List<BoardDTO> getBoardList();

	int insertPost(BoardDTO pDTO);


	BoardDTO getPostDetail(BoardDTO pDTO);

	int deletePost(BoardDTO pDTO);

	int updatePost(BoardDTO pDTO);
	

	List<BoardDTO> getsearchList(BoardDTO bDTO);

}
