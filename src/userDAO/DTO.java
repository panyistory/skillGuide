package userDAO;

public class DTO {
	String name = null;
	int skill_1;
	int skill_2;
	int skill_3;
	int skill_4;
	int[] skillIdx = new int[4];
	
	// 기본 생성자
	public DTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DTO(String name, int skill_1, int skill_2, int skill_3, int skill_4) {
		this.name = name;
		this.skill_1 = skill_1;
		this.skill_2 = skill_2;
		this.skill_3 = skill_3;
		this.skill_4 = skill_4;
	}

	// Getter and Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSkill_1() {
		return skill_1;
	}

	public void setSkill_1(int skill_1) {
		this.skill_1 = skill_1;
	}

	public int getSkill_2() {
		return skill_2;
	}

	public void setSkill_2(int skill_2) {
		this.skill_2 = skill_2;
	}

	public int getSkill_3() {
		return skill_3;
	}

	public void setSkill_3(int skill_3) {
		this.skill_3 = skill_3;
	}

	public int getSkill_4() {
		return skill_4;
	}

	public void setSkill_4(int skill_4) {
		this.skill_4 = skill_4;
	}

	public int[] getSkillIdx() {
		return skillIdx;
	}

	public void setSkillIdx(int[] skillIdx) {
		this.skillIdx = skillIdx;
	}
	
	
}
