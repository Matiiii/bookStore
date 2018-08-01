package pl.jstk.to;

import java.util.List;

import pl.jstk.model.Information;

public class BookAndInfoTo {

	private Information info;
	private List<BookTo> listBookTo;

	public BookAndInfoTo(Information info, List<BookTo> bookTo) {
		this.info = info;
		this.listBookTo = bookTo;
	}

	public Information getInfo() {
		return info;
	}

	public void setInfo(Information info) {
		this.info = info;
	}

	public List<BookTo> getListBookTo() {
		return listBookTo;
	}

	public void setListBookTo(List<BookTo> bookTo) {
		this.listBookTo = bookTo;
	}

}
