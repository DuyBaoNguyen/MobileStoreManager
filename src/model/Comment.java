package model;

import java.sql.Timestamp;
import java.util.List;

public class Comment {
	int id;
	String content;
	Timestamp time;
	String personName;
	Boolean personSex;
	Comment answerComment;
	List<Comment> comments;
	Product product;
	Account answerAccount;
	boolean answerd;
	
	public Comment() {
		super();
	}
	
	public Comment(int id) {
		super();
		this.id = id;
	}
	
	public Comment getAnswerComment() {
		return answerComment;
	}

	public void setAnswerComment(Comment answerComment) {
		this.answerComment = answerComment;
	}

	public boolean getAnswerd() {
		return answerd;
	}

	public void setAnswerd(boolean answerd) {
		this.answerd = answerd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Boolean getPersonSex() {
		return personSex;
	}

	public void setPersonSex(Boolean personSex) {
		this.personSex = personSex;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Account getAnswerAccount() {
		return answerAccount;
	}

	public void setAnswerAccount(Account answerAccount) {
		this.answerAccount = answerAccount;
	}
}
