package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.BoardDTO;
import poly.persistance.mapper.IBoardMapper;
import poly.service.IBoardService;

@Service("BoardService")
public class BoardService implements IBoardService{

	@Resource(name = "BoardMapper")
	IBoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> getBoardList(){
		
		return boardMapper.getBoardList();
	}
	
	@Override
	public int insertPost(BoardDTO pDTO) {
		
		return boardMapper.insertPost(pDTO);
	}


	@Override
	public BoardDTO getPostDetail(BoardDTO pDTO) {
		
		return boardMapper.getPostDetail(pDTO);
	}

	@Override
	public int deletePost(BoardDTO pDTO) {
		// TODO Auto-generated method stub
		return boardMapper.deletePost(pDTO);
	}

	@Override
	public int updatePost(BoardDTO pDTO) {
		// TODO Auto-generated method stub
		return boardMapper.updatePost(pDTO);
	}

	@Override
	public List<BoardDTO> getsearchList(BoardDTO bDTO) {
		// TODO Auto-generated method stub
		return boardMapper.getsearchList(bDTO);
	}
	
	




	

	
	
}
