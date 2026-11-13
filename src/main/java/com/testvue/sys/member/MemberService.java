package com.testvue.sys.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testvue.common.auth.LoginUser;
import com.testvue.common.exception.BizException;

/**
 * <pre>
 * 회원사관리. 회원사는 지우지 않고 사용여부만 바꾼다.
 * </pre>
 */
@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	public List<Member> getMembers(String mbNm, String useYn) {
		return memberMapper.selectMemberList(mbNm, useYn);
	}

	@Transactional
	public void addMember(LoginUser user, Member member) {
		if (member.getMbId() == null || member.getMbId().trim().length() == 0) {
			throw new BizException("회원사ID를 입력하세요.");
		}
		if (memberMapper.countMember(member.getMbId()) > 0) {
			throw new BizException("이미 있는 회원사ID입니다: " + member.getMbId());
		}
		memberMapper.insertMember(user.getEmpCd(), member);
	}

	@Transactional
	public void modifyMember(LoginUser user, String mbId, Member member) {
		member.setMbId(mbId);
		if (memberMapper.updateMember(user.getEmpCd(), member) == 0) {
			throw new BizException("회원사가 없습니다: " + mbId);
		}
	}
}
