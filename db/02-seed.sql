-- testVue 시험 데이터
-- 로그인: 회사코드 PRD / 아이디 admin / 비밀번호 test1234!  (USER_PWD = SHA-256('test1234!'))

INSERT INTO TBSY_MEMBER (MB_ID, MB_NM, REPRESENT_NM, TELNO, CONT_FRYMD, CONT_TOYMD, USE_YN, SORT_NO, IN_EMP_CD, IN_DTM) VALUES
    ('PRD',  '테스트 회사', '홍길동', '02-1234-5678', '20260101', '20991231', 'Y', 10, 'ADMIN001', NOW()),
    ('DEMO', '데모 회사',   '김데모', '02-9876-5432', '20260301', '20261231', 'Y', 20, 'ADMIN001', NOW());

INSERT INTO TBIN_EMPMST (MB_ID, EMP_CD, EMP_NM, EMAIL) VALUES
    ('PRD', 'ADMIN001', '관리자', 'admin@testvue-demo.local');

INSERT INTO TBSY_LOGIN (MB_ID, EMP_CD, LOGIN_ID, USER_PWD, ACCT_LOCK_TYPE, IN_EMP_CD, IN_DTM) VALUES
    ('PRD', 'ADMIN001', 'admin', 'd27c5b2db7dc392a0cfeb18b9782f34709d1dea7bdb8dd7209f6d4c7387c7910', 'N', 'ADMIN001', NOW());

INSERT INTO TBSY_RESOURCE (MB_ID, RESOURCE_ID, PRNT_RESOURCE_ID, RESOURCE_NAME, RESOURCE_URL, SORT_NO, USE_YN, IN_EMP_CD, IN_DTM) VALUES
    ('PRD', 'M100', NULL,   '시스템관리',   'NONE',                       10, 'Y', 'ADMIN001', NOW()),
    ('PRD', 'M110', 'M100', '공통코드관리', '/sys/codes',                10, 'Y', 'ADMIN001', NOW()),
    ('PRD', 'M120', 'M100', '회원사관리',   '/sys/members',              20, 'Y', 'ADMIN001', NOW()),
    ('PRD', 'M130', 'M100', '로그인이력',   '/sys/login-hists',          30, 'Y', 'ADMIN001', NOW()),
    ('PRD', 'M200', NULL,   '샘플',         'NONE',                       20, 'Y', 'ADMIN001', NOW()),
    ('PRD', 'M210', 'M200', '샘플게시판',   '/sample/boards',            10, 'Y', 'ADMIN001', NOW());

INSERT INTO TBCM_COMMON_CODE_GROUP (MB_ID, GRP_CMM_CD, GRP_CMM_CD_NM, GRP_CMM_CD_DESC, SYSTEM_GUBUN, SORT_NO, IN_EMP_CD, IN_DTM) VALUES
    ('PRD', 'USE_YN',      '사용여부',   '사용여부 공통코드',          'CM',   10, 'ADMIN001', NOW()),
    ('PRD', 'ACTION_TYPE', '액션유형',   '액션 로그 유형',             'SY',   20, 'ADMIN001', NOW()),
    ('PRD', 'ATTACH_GBN',  '첨부구분',   '첨부파일 구분',              'SY',   30, 'ADMIN001', NOW()),
    ('PRD', 'TEST_GROUP',  '테스트그룹', '워크플로우 시험용 샘플 그룹', 'DEMO', 40, 'ADMIN001', NOW());

INSERT INTO TBCM_COMMON_CODE (MB_ID, GRP_CMM_CD, CD_VL, CD_VL_NM, USE_YN, SORT_NO, IN_EMP_CD, IN_DTM) VALUES
    ('PRD', 'USE_YN',      'Y',       '사용',       'Y', 10, 'ADMIN001', NOW()),
    ('PRD', 'USE_YN',      'N',       '미사용',     'Y', 20, 'ADMIN001', NOW()),
    ('PRD', 'ACTION_TYPE', 'GO',      '페이지이동', 'Y', 10, 'ADMIN001', NOW()),
    ('PRD', 'ACTION_TYPE', 'READ',    '조회',       'Y', 20, 'ADMIN001', NOW()),
    ('PRD', 'ATTACH_GBN',  'GENERAL', '일반첨부',   'Y', 10, 'ADMIN001', NOW()),
    ('PRD', 'TEST_GROUP',  'A001',    '샘플코드1',  'Y', 10, 'ADMIN001', NOW()),
    ('PRD', 'TEST_GROUP',  'A002',    '샘플코드2',  'Y', 20, 'ADMIN001', NOW());

INSERT INTO TBTS_SAMPLE_BOARD (MB_ID, TITLE, CONTENT, WRITER_NM, USE_YN, IN_EMP_CD, IN_DTM) VALUES
    ('PRD', '첫 번째 게시글', '샘플게시판 첫 글입니다.', '관리자', 'Y', 'ADMIN001', NOW()),
    ('PRD', '두 번째 게시글', 'Vue 화면에서 수정해 보세요.', '관리자', 'Y', 'ADMIN001', NOW()),
    ('PRD', '세 번째 게시글', '삭제하면 USE_YN이 N으로 바뀝니다.', '관리자', 'Y', 'ADMIN001', NOW());
