import java.util.*;
import javax.mail.*; 
import javax.mail.internet.";
 Import.javax.activation."; 
 Import.javax.mail.Session;
 import javax.mail.Transport;
 public class SendEmail
{
public static void main(String[] args)
{
String recipient="recipient@gmail.com"; String sender="sender@gmail.com";

String host="192.168.69.60";

Properties properties = System.getProperties();

properties.setProperty("mail.smtp.host",host); 
Session session = Session.getDefaultInstance(properties);

try 
{
MimeMessage message = new MimeMessage(session); 
message.setFrom(new InternetAddress(sender));

message.addRecipient(Messsage.RecipientType.TO,new InternetAddress(recipient));

message.setSubject("This is Subject");
 message.setText This is a test mail"); 
 Transport.send(message);

System.out.println("Mail successfully sent");
}
catch(MessagingException mex)
{
mex.printStackTrace();
}
}
}
}