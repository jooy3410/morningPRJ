package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.BoardDTO;

@Mapper("BoardMapper")
public interface IBoardMapper {
	
	List<BoardDTO> getBoardList();

	int insertPost(BoardDTO pDTO);

	BoardDTO getPostDetail(BoardDTO pDTO);
	
	int deletePost(BoardDTO pDTO);

	int updatePost(BoardDTO pDTO);
	
	List<BoardDTO> getBoardList(BoardDTO uDTO)throws Exception;

	List<BoardDTO> getsearchList(BoardDTO bDTO);

}
