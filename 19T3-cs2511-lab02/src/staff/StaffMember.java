package staff;

import java.time.LocalDate;

/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */
public class StaffMember {

	// Private attributes
	protected String name;
	protected int salary;
	protected LocalDate hire;
	protected LocalDate end;
	

	// Constructor
	public StaffMember(String name, int salary, LocalDate hire, LocalDate end) {
		this.name = name;
		this.salary = salary;
		this.hire = hire;
		this.end = end;
	}
	
	// Setters and Getters
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDate getHire() {
		return hire;
	}

	public void setHire(LocalDate hire) {
		this.hire = hire;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}	
	
	// Not sure if _hire and _end is correct and they can be outputted straight into string
	@Override
	public String toString() {
		return String.format(this.name + " " + Integer.toString(this.salary) + " " + this.hire + " " + this.end);
	}

	@Override
	public boolean equals(Object s) {
		if (s == null) {
			return false;
		}
		
		// use getclass()
		
		if (s.getClass().equals(this.getClass())) {
			StaffMember x = (StaffMember) s;
			if (x.name.equals(this.name) && x.salary == this.salary
					&& x.hire.equals(this.hire) && x.end.equals(this.end)) {
				return true;
			}
		}

		return false;
	}

} 

class Lecturer extends StaffMember {
	// Constructor
	
	/**
	 * Description here
	 * @param name		description
	 * @param salary
	 * @param hire
	 * @param end
	 * @param school
	 * @param status
	 * 
	 * @return returns what and when
	 */
	public Lecturer(String name, int salary, LocalDate hire, LocalDate end, String school, char status) {
		super(name, salary, hire, end);
		this.school = school;
		this.status = status;
	}

	// Attributes
	private String school;
	private char status;

	// Getter
	public String getSchool() {
		return this.school;
	}

	// Setter
	public void setSchool(String newSchool) {
		this.school = newSchool;
	}

	@Override
	public String toString() {
		return String.format(super.toString() + " " + school + " " + status);
	}

	@Override
	public boolean equals(Object l) {
		if (l == null) {
			return false;
		}
	
		if (l.getClass().equals(this.getClass())) {
			Lecturer x = (Lecturer) l;
			if (x.name.equals(this.name) && x.salary == this.salary
					&& x.hire.equals(this.hire) && x.end.equals(this.end)
					&& x.school.equals(this.school) && x.status == this.status) {
				return true;
			}
		} 

		return false;
	}

}
