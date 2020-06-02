package staff;

import java.time.LocalDate;


public class stafftest {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		LocalDate date1 = LocalDate.of(2017, 1, 13);
		LocalDate date2 = LocalDate.of(2018, 1, 13);

		
		
		StaffMember a = new StaffMember("Chris", 1000, date1, date2);
		Lecturer b = new Lecturer("Chris", 1000, date1, date2, "CSE", 'A');
		
		System.out.println(a.equals(b));
		System.out.println(b.equals(a));
		
		printStaffDetails(a);
		printStaffDetails(b);
		
		

	}
	
	public static void printStaffDetails(StaffMember s) {
		System.out.println(s.toString());
	}

}
