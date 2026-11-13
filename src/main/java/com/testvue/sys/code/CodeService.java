package com.testvue.sys.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testvue.common.auth.LoginUser;
import com.testvue.common.exception.BizException;

/**
 * <pre>
 * 공통코드관리. 그룹을 지우면 그 아래 코드도 함께 지운다.
 * </pre>
 */
@Service
public class CodeService {

	@Autowired
	private CodeMapper codeMapper;

	public List<CodeGroup> getCodeGroups(LoginUser user, String keyword) {
		return codeMapper.selectCodeGroupList(user.getMbId(), keyword);
	}

	@Transactional
	public void addCodeGroup(LoginUser user, CodeGroup group) {
		if (isBlank(group.getGrpCmmCd())) {
			throw new BizException("그룹코드를 입력하세요.");
		}
		if (codeMapper.countCodeGroup(user.getMbId(), group.getGrpCmmCd()) > 0) {
			throw new BizException("이미 있는 그룹코드입니다: " + group.getGrpCmmCd());
		}
		codeMapper.insertCodeGroup(user.getMbId(), user.getEmpCd(), group);
	}

	@Transactional
	public void modifyCodeGroup(LoginUser user, String grpCmmCd, CodeGroup group) {
		group.setGrpCmmCd(grpCmmCd);
		if (codeMapper.updateCodeGroup(user.getMbId(), user.getEmpCd(), group) == 0) {
			throw new BizException("그룹코드가 없습니다: " + grpCmmCd);
		}
	}

	@Transactional
	public void removeCodeGroup(LoginUser user, String grpCmmCd) {
		codeMapper.deleteCodeByGroup(user.getMbId(), grpCmmCd);
		codeMapper.deleteCodeGroup(user.getMbId(), grpCmmCd);
	}

	public List<Code> getCodes(LoginUser user, String grpCmmCd, String useYn) {
		return codeMapper.selectCodeList(user.getMbId(), grpCmmCd, useYn);
	}

	@Transactional
	public void addCode(LoginUser user, String grpCmmCd, Code code) {
		code.setGrpCmmCd(grpCmmCd);
		if (isBlank(code.getCdVl())) {
			throw new BizException("코드값을 입력하세요.");
		}
		if (codeMapper.countCodeGroup(user.getMbId(), grpCmmCd) == 0) {
			throw new BizException("그룹코드가 없습니다: " + grpCmmCd);
		}
		if (codeMapper.countCode(user.getMbId(), grpCmmCd, code.getCdVl()) > 0) {
			throw new BizException("이미 있는 코드값입니다: " + code.getCdVl());
		}
		codeMapper.insertCode(user.getMbId(), user.getEmpCd(), code);
	}

	@Transactional
	public void modifyCode(LoginUser user, String grpCmmCd, String cdVl, Code code) {
		code.setGrpCmmCd(grpCmmCd);
		code.setCdVl(cdVl);
		if (codeMapper.updateCode(user.getMbId(), user.getEmpCd(), code) == 0) {
			throw new BizException("코드가 없습니다: " + grpCmmCd + "/" + cdVl);
		}
	}

	@Transactional
	public void removeCode(LoginUser user, String grpCmmCd, String cdVl) {
		codeMapper.deleteCode(user.getMbId(), grpCmmCd, cdVl);
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().length() == 0;
	}
}
