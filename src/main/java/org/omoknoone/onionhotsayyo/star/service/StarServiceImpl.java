package org.omoknoone.onionhotsayyo.star.service;

import org.modelmapper.ModelMapper;
import org.omoknoone.onionhotsayyo.follow.aggregate.Follow;
import org.omoknoone.onionhotsayyo.follow.dto.FollowDTO;
import org.omoknoone.onionhotsayyo.star.aggregate.Star;
import org.omoknoone.onionhotsayyo.star.dto.MyStarPostListDTO;
import org.omoknoone.onionhotsayyo.star.dto.StarDTO;
import org.omoknoone.onionhotsayyo.star.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StarServiceImpl implements StarService{

	private final StarRepository starRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper) {
		this.starRepository = starRepository;
		this.modelMapper = modelMapper;
	}

	// 좋아요(또는 싫어요) 추가
	@Transactional
	@Override
	public void addStar(StarDTO starDTO) {

		Star star = modelMapper.map(starDTO, Star.class);
		starRepository.save(star);

	}

	// 좋아요(또는 싫어요) 삭제
	@Transactional
	@Override
	public void removeStar(StarDTO starDTO) {

		Star star = modelMapper.map(starDTO, Star.class);
		starRepository.delete(star);

	}

	// 회원이 좋아요를 누른 게시물 목록 조회
	@Transactional(readOnly = true)
	@Override
	public List<MyStarPostListDTO> findLikedPostsByMemberId(String memberId) {
		return starRepository.findLikedPostsByMemberId(memberId);
	}

	// 좋아요 / 싫어요 여부 확인
	@Override
	public Integer existsStar(StarDTO starDTO) {
		List<Star> starList = starRepository.findAll();

		for (Star star : starList) {
			if (star.getMemberId().equals(starDTO.getMemberId()) &&
				star.getPostId().equals(starDTO.getPostId())) {
				return star.isStatus() ? 1 : 0;			// 1: 좋아요 누른 상태 / 0: 싫어요 누른 상태
			}
		}
		return 2;		// 아무 것도 안 누른 상태
	}





}
