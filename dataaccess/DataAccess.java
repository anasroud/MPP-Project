package dataaccess;

import java.util.HashMap;

import business.Book;
import business.Checkout;
//import business.CheckoutRecord;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String,Checkout> readRecordsMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public boolean checkMemberPresentOrNot(LibraryMember member);
	public void saveNewBook(Book book); 
	public void editSelectedBook(Book book); 
	public boolean checkLibraryMemberById(String memId);
	public Book checkBookByISBN(String isbn);
	public void saveCheckoutRecord(Checkout checkoutRecord);
	public void updateBook(Book book);
	
}
