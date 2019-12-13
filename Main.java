import java.sql.*;
import java.util.Scanner; 
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Main{  
public static void main(String args[])throws Exception{  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS","root","user1");//for storing entries into EMS database with username root and password user1
if(con==null)
{
 System.out.println("Connection not established");
 System.out.println("-------------------------------------------");
}
else
{
 System.out.println("Connection Ok");
 System.out.println("-------------------------------------------");
}
	Scanner sc= new Scanner(System.in);
	
System.out.println("Enter Visitor Details:");
System.out.println("Enter Name :");
String name=sc.nextLine();
System.out.println("Enter Email Address :");
String email=sc.nextLine();
System.out.println("Enter Phone Number :");
String phone=sc.nextLine();
String checkout="";

System.out.println("Enter Host Details: ");
System.out.println("Enter Name :");
String hname=sc.nextLine();
System.out.println("Enter Email Address :");
String hemail=sc.nextLine();
System.out.println("Enter Phone Number :");
String hphone=sc.nextLine();
System.out.println("Enter Address :");
String address=sc.nextLine();
System.out.println("Enter Checkin Time :");
String checkin=sc.nextLine();



Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
String timestamp=sdf.format(cal.getTime());



PreparedStatement pstmt=con.prepareStatement("insert into visitor(timestamp,name,email,phone,checkin,checkout,hostname,address) values(?,?,?,?,?,?,?,?)");
pstmt.setString(1,timestamp);
pstmt.setString(2,name);
pstmt.setString(3,email);
pstmt.setString(4,phone);
pstmt.setString(5,checkin);
pstmt.setString(6,checkout);
pstmt.setString(7,hname);
pstmt.setString(8,address);
int insert=pstmt.executeUpdate();
if(insert>0) 
	System.out.println("Visitor Data Inserted");

PreparedStatement pstmt2=con.prepareStatement("insert into Host(hname,hemail,hphone) values(?,?,?)");
pstmt2.setString(1,hname);
pstmt2.setString(2,hemail);
pstmt2.setString(3,hphone);
int insert2=pstmt.executeUpdate();
if(insert2>0) 
	System.out.println("Host Data Inserted");

Email e=new Email(hemail,name,phone,email);
e.sendtohost();//Mail sent


String checkoutfinal;
	System.out.println("Enter Checkout Time");
	checkoutfinal=sc.nextLine();
PreparedStatement pstmt3=con.prepareStatement("update visitor set checkout=? where email=?");
pstmt3.setString(1,checkoutfinal);
pstmt3.setString(2,email);
pstmt3.executeUpdate();

Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from visitor");  
String fname="",fphone="",fcheckin="",fcheckout="",fhost="",faddress="";
while(rs.next())  
{
	fname=rs.getString(2);
	fphone=rs.getString(4);
	fcheckin=rs.getString(5);
	fcheckout=rs.getString(6);
	fhost=rs.getString(7);
	faddress=rs.getString(8);
}


Email e2=new Email(email,fname,fphone,fcheckin,fcheckout,fhost,faddress);
String msgtohost="Name of Visitor :"+name+"\n"+"Phone number of Visiter :"+phone+"\n"+"Email of Visiter :"+email;
SMS stoh=new SMS(msgtohost,hphone);
stoh.sendSms();
e2.sendtoVisitor();
con.close();
}
catch(Exception e){ System.out.println(e);} 

}  
}  
