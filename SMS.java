    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
    import java.net.URLConnection;
    import java.net.URLEncoder;
     
    public class SMS {
    	String sms="";
    	String num="";
    	public SMS(String sms, String num)
    	{
    		this.sms=sms;
    		this.num=num;
    	}
    	public String sendSms() {
    		try {
    			
    			
    			// Construct data
    			String apiKey = "apikey=" + "M8cEBZcxWAU-DYhQonS0CqjtJH0h2lxlAAJ66qoQkb";
    			String message = "&message=" + sms;
    			String sender = "&sender=" + "TXTLCL";
    			String numbers = "&numbers=" + num;
    			
    			// Send data
    			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
    			String data = apiKey + numbers + message + sender;
    			conn.setDoOutput(true);
    			conn.setRequestMethod("POST");
    			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
    			conn.getOutputStream().write(data.getBytes("UTF-8"));
    			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			final StringBuffer stringBuffer = new StringBuffer();
    			String line;
    			while ((line = rd.readLine()) != null) {
    				stringBuffer.append(line);
    			}
    			rd.close();
    			
    			return stringBuffer.toString();
    		} catch (Exception e) {
    			System.out.println("Error SMS "+e);
    			return "Error "+e;
    		}
    	}
    }

