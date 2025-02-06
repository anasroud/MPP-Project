package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public void addMember(LibraryMember l) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		if (l.getMemberId().equals(""))
			throw new LibrarySystemException("Member ID is required.");
		if (da.checkMemberPresentOrNot(l))
			throw new LibrarySystemException("Duplicate Member ID");
		if (l.getFirstName().equals(""))
			throw new LibrarySystemException("First Name is required.");
		if (l.getLastName().equals(""))
			throw new LibrarySystemException("Last Name is required.");
		if (l.getTelephone().equals(""))
			throw new LibrarySystemException("Phone is required.");
		if (l.getAddress().getCity().equals(""))
			throw new LibrarySystemException("City is required.");
		if (l.getAddress().getZip().equals(""))
			throw new LibrarySystemException("ZIP is required.");
		if (l.getAddress().getState().equals(""))
			throw new LibrarySystemException("State is required.");
		if (l.getAddress().getStreet().equals(""))
			throw new LibrarySystemException("Street is required.");
		da.saveNewMember(l);
		return;
	}

	@Override
	public void saveBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		if (book.getIsbn().equals("")) {
			throw new BookException("ISBN must be filled.");
		}
		if (book.getNumCopies() < 0) {
			throw new BookException("Number of sopies must be greater than 0.");
		}
		if (book.getMaxCheckoutLength() <= 0) {
			throw new BookException("Maximuim checkout length must be greater than 0.");
		}
		if (book.getTitle().equals("")) {
			throw new BookException("Book title must be filled.");
		}
		if (map.containsKey(book.getIsbn())) {
			throw new BookException("Duplicated ISBN.");
		}
		da.saveNewBook(book);
		return;
	}

	@Override
	public Book getInfo(String memId, String isbn) throws BookException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		if (da.checkLibraryMemberById(memId)) {
			Book book = da.checkBookByISBN(isbn);
			if (book != null) {
				if (book.getNextAvailableCopy() == null) {
					throw new BookException("Did not found an available copy.");
				}
				return book;
			} else {
				throw new BookException("Book information did't found.");
			}
		} else {
			throw new BookException("Member Id didn't found.");
		}
	}

	@Override
	public int getAvailableCopyNum(BookCopy[] copies) {
		for (BookCopy copy : copies) {
			if (copy.isAvailable()) {
				return copy.getCopyNum();
			}
		}
		return -1;
	}

	@Override
	public void saveRecord(Checkout record) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.saveCheckoutRecord(record);
	}

	@Override
	public Book getLookUpDetails(String isbn) throws BookException {
		DataAccess da = new DataAccessFacade();
		if (da.checkBookByISBN(isbn) == null)
			throw new BookException("This book didn't found.");
		Book b = da.checkBookByISBN(isbn);
		return b;
	}

	@Override
	public void editBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.editSelectedBook(book);
	}

	@Override
	public void updateBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.updateBook(book);
	}

	public void print(String memId) {
		DataAccess da = new DataAccessFacade();
		String header = String.format("%5s %8s %13s %25s %10s %17s %15s", "ID", "ISBN","Title", "Max. Checkout Len",
				"Copy Num", "Checkout Date", "Due Date");
		System.out.println(header);
		HashMap<String, Checkout> records = da.readRecordsMap();
		int i=1;
		for(Checkout c :records.values()) {
			if (c.getMemId().substring(0, 4).equals(memId)) {
				System.out.println(i + " " +c);
				i++;
			}
				
		}
	}
	public List<Checkout> checkOverdue(String isbn) {
		DataAccess da = new DataAccessFacade();
		List<Checkout> recordList = new ArrayList<Checkout>();
		HashMap<String, Checkout> records = da.readRecordsMap();
		for(Checkout c :records.values()) {
			if (c.getCopy().getBook().getIsbn().equals(isbn) && !c.getCopy().isAvailable()
					&& c.getDueDate().compareTo(LocalDate.now()) < 0 ) {
				recordList.add(c);
			}
				
		}
		return recordList;
	}
}
