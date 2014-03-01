package com.seatbookie.svg.parse;

public class Seat {

	private String seatNo;

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatNo == null) ? 0 : seatNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (seatNo == null) {
			if (other.seatNo != null)
				return false;
		} else if (!seatNo.equals(other.seatNo))
			return false;
		return true;
	}

}
