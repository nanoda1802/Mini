# Mini
### 1) 주요기능
##### 직원 관리
- 신규 직원 추가
- 직원 정보 수정 (소속 팀 수정 / 직위 수정 / 담당 프로젝트 수정)
- 직원 조회 (유형별)
##### 프로젝트 관리
- 신규 프로젝트 추가
- 진행 프로젝트 수정 (프로젝트명 수정 / 리더 변경 / 소개 수정 / 유형 수정 / 상태 수정 / ... )
- 프로젝트 조회 (유형별)
##### 팀 관리
- 신규 팀 생성
- 팀 정보 수정 (팀원 추가 / 팀원 제거 / 팀명 수정 / ...)
- 팀 조회 (유형별)

### 2) 클래스 예시 코드
##### 직원 employee
```java
class Employee {
	// [1] 필드 (전부 private)
	String name;
	String team; // 나중에 id로 변경하거나, Team 객체 자체 넣기?
	Role role = Role.Junior;
	Project[] project; // 필요에 따라 HashSet이나 List로 변경?
	final Date startDate = new Date(); // format 수정해줘야할 듯, yyyy-MM-dd ??

	// [2] 생성자 (매개변수는 name과 team만 받기)
	// role과 startDate는 기본값이 있고, project는 setter로 넣어주기
	public Employee(String name, String team) {}

	// [3] 접근자 getter
	// 모든 필드 필요할 듯...?

	// [4] 설정자 setter
	// team, role, project에 대한 설정자 필요

	// [5] 필요에 따라 메서드 추가하기
	// 아마 정렬 기능 위해서 각 필드 별 compare 메서드 많이 생길 듯
}

// 임시 작명, Enum 관리용 모듈 만들어 통합 관리 예정
Enum Role { JUNIOR, SENIOR, MANAGER, DIRECTOR }
```
##### 프로젝트 Project
```java
class Project {
	// [1] 필드 (전부 private)
	String name;
	String leader; // 나중에 id로 변경하거나, Employee 객체 자체 넣기?
	String description; // 프로젝트 한 줄 소개
	ProjectType type;
	ProjectStatus status = ProjectStatus.NOT_STARTED; 
	String[] teams; // 필요에 따라 HashSet이나 List로 변경?
	final Date startDate = new Date(); // format 수정해줘야할 듯, yyyy-MM-dd ??
	Date expiredDate;

	// [2] 생성자 (매개변수는 name, leader, type, teams, expiredDate만 받기)
	public Project(String name, String leader, ProjectType type, Team[] teams, Date expiredDate) {}

	// [3] 접근자 getter
	// description 제외 모든 필드 필요할 듯...?

	// [4] 설정자 setter
	// startDate 제외하고 모든 필드 설정자 필요

	// [5] 필요에 따라 메서드 추가하기
	// 아마 정렬 기능 위해서 각 필드 별 compare 메서드 많이 생길 듯

}

// 임시 작명, Enum 관리용 모듈 만들어 통합 관리 예정
Enum ProjectType { CREATE, DEVELOP, DEBUG }
Enum ProjectStatus { NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED }
```
### 3) 또 필요한 것
- `Employee, Project, Team`의 각 인스턴스들 관리용 클래스 필요 
	- ArrayList? HashSet? Map? 알아보고 결정
	- 싱글턴으로 만드는 게 좋을 듯,,,?
	- 요소 추가/제거/조회 메서드는 양식이 유사할 거 같은데 abstract나 interface로 빼도 괜찮을지두
```java
static class Employees {
	// 싱글턴 만드는 방법 까먹었음다
	// [1] 대충 싱글턴 만들었다 치고

	// [2] DB 생길 때까진 얘로 관리
	private List<Employee> employees = new List<>(); 

	// [3] 대충 추가/제거/조회 하는 메서드들
}
```
- 오류 관리 방법? 프로그램이 꺼져선 안 된다
- 모듈 관리 어떻게 할지? 객체지향? 할줄 몰루
- 고민해보기

### 4) 추가해볼 만한 기능?
- 권한별 접근 기능 차등화
- 캘린더 연동 기능
- 메신저 기능
- 회원가입 로그인 -> 비밀번호 암호화 복호화



### 5) 컬럼 정의 (나중에 ERD로 발전시키기)
##### 직원
- 아이디 (식별자, 기본키)
- 이름 
- 소속 팀
- 권한?
- 현재 프로젝트 (여러 개 될 수 있음)
- 입사일자
##### 프로젝트
- 아이디 (식별자, 기본키)
- 프로젝트명
- 프로젝트 설명
- 리더?
- 유형 (Enum?)
- 진행 상태 (Enum? 시작예정 / 진행중 / 완료 / 문제발생)
- 관련 팀 (여러 개 될 수 있음)
- 시작일
- 마감일
##### 팀
- 아이디 (식별자, 기본키)
- 팀명
- 리더
