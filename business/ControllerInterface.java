package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void addMember(LibraryMember l)throws LibrarySystemException;
	public void saveBook(Book book) throws BookException;
	public Book getInfo(String memId, String isbn) throws BookException;
	public void updateBook(Book book) throws BookException;
	public Book getLookUpDetails(String isbn) throws BookException;
	public void saveRecord(Checkout record);
	public void editBook(Book book) throws BookException;
	public int getAvailableCopyNum(BookCopy[] copies);
	public void print(String memId);
	public List<Checkout> checkOverdue(String isbn);
	
}
