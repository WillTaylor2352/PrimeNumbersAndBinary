package primeNumberProtocols;

import java.io.Serializable;
import java.math.BigInteger;

public class PrimeNumberProtocol implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private BigInteger number;
	private String message;
	private Boolean resultOfPrimeNumberCheck;		// True = number is prime, false = number is not prime
	public enum enumStatus {
		HERE_IS_A_MESSAGE_FOR_YOU,
		SEND_ME_A_NUMBER_TO_CHECK,
		CHECK_THIS_NUMBER,
		THIS_NUMBER_HAS_BEEN_CHECKED,
		ERROR
	};
	private enumStatus status;
	private boolean isPrime;

	public BigInteger getNumber() {
		return number;
	}
	public void setNumber(BigInteger number) {
		this.number = number;
	}
	public boolean isPrime() {
		return isPrime;
	}
	public void setPrime(boolean isPrime) {
		this.isPrime = isPrime;
	}
	public enumStatus getStatus() {
		return status;
	}
	public void setStatus(enumStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getResultOfPrimeNumberCheck() {
		return resultOfPrimeNumberCheck;
	}
	public void setResultOfPrimeNumberCheck(Boolean resultOfPrimeNumberCheck) {
		this.resultOfPrimeNumberCheck = resultOfPrimeNumberCheck;
	};
}

