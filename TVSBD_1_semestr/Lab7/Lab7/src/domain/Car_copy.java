package domain;

public class Car_copy extends Entity {
	private String state_num;
	private Long car_id;

	public String getState_num() {
		return state_num;
	}

	public void setState_num(String state_num) {
		this.state_num = state_num;
	}

	public Long getCar_id() {
		return car_id;
	}

	public void setCar_id(Long car_id) {
		this.car_id = car_id;
	}

	@Override
	public String toString() {
		return state_num;
	}
}
