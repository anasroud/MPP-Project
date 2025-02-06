package business;

import java.io.Serializable;
import java.time.LocalDate;

final public class Checkout implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookCopy copy;
	private String memId;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private int copyNum;

	public Checkout(String memId, BookCopy copy, LocalDate checkoutDate, LocalDate dueDate, int copyNum) {
		this.memId = memId;
		this.copy = copy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.copyNum = copyNum;
	}

	public BookCopy getCopy() {
		return copy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCopyNum() {
		return copyNum;
	}

	public String getMemId() {
		return memId;
	}

	@Override
	public String toString() {
		String s = String.format("%6s %9s %15s %16s %10s %18s %18s", memId, this.copy.getBook().getIsbn(),this.copy.getBook().getTitle(), this.copy.getBook().getMaxCheckoutLength(),
				copyNum, checkoutDate, dueDate);
		return s;
	}
}
