 import java.io.*;
import java.net.*;

import java.nio.charset.StandardCharsets;

import java.io.IOException;

import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.JSONArray;
public class MainListener {
	  
	 	public String apiURL = "";
	 	public String SteamAPIKey ="";
	 	public String CDAvatarURL= "";
	 	public String ChannelID = "";
	 	private Socket socket;
	    private PrintWriter out;
	    private BufferedReader in;
	    private int port;
	    private DiscordBot Bot;
	 
	MainListener(int port,String apiURL,String SteamAPIKey,String CDAvatarURL,String BotToken,String ChannelID) throws InterruptedException
	{	
		
		this.port = port;
		this.apiURL=apiURL;
		this.SteamAPIKey=SteamAPIKey;
		this.CDAvatarURL=CDAvatarURL;
		this.ChannelID=ChannelID;
		if(!BotToken.equals("0"))
		{	
			System.out.println("Initializing Discord Bot");
			Bot = new DiscordBot(BotToken);
			
		}
		
		
		new Thread(new Runnable() {
			@Override
			public void run()
			{
			
				try {
					
					SetupConnection();
					
				} catch (IOException | InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		).start();
		
		
		
	}
	
	private void SetupConnection() throws IOException,InterruptedException
	{	
		Thread.sleep(2000);
		if(Bot!=null)
		{
			Bot.SetListener(this);
		}
			
		
		socket = new Socket("0.0.0.0",port);
		out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        String inputLine;
        System.out.println("Start Listening....");
        while ((inputLine = in.readLine()) != null)
        {
	
          PostRequest(UnicodeConvert(inputLine));
        }
        stopConnection();
        Thread.sleep(8000);
        SetupConnection();
	}
	
	private static String UnicodeConvert(String Message)
	{	
		String[] array = Message.split("/");
		String Text ="";
		
		for(int numi=0;numi<Message.split("/").length;numi++)
		{
			Text = Text + (char)(Integer.parseInt(array[numi]));
		}
		return Text;
	}
	

	
	public void sendMessage(String msg) throws IOException{
        out.println(msg);
     
    }

	private void stopConnection() throws IOException{
        in.close();
        out.close();
        socket.close();
    }
    
	private String[] ExtractMessageInfo(String Message)
    {	
    	String[] OutString = new String[4];
 
    	String[] RawArr = Message.split(Pattern.quote("^$"));
    	if(RawArr[0]=="CDC")
    	{
    		OutString[0]="1";OutString[1]=RawArr[1];OutString[2]=RawArr[2];OutString[3]=CDAvatarURL;
    		return OutString;
    	}
    	
    	Long SteamID =Long.decode(RawArr[0]);
    	

    	String Username = RawArr[1];
    	String content=RawArr[2];
    	
    	try{
	        URL url = new URL("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+SteamAPIKey+"&steamids="+SteamID);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        StringBuilder result = new StringBuilder();
	        try (BufferedReader reader = new BufferedReader(
	                  new InputStreamReader(connection.getInputStream()))) {
	          for (String line; (line = reader.readLine()) != null; ) {
	              result.append(line);
	          }
	      }
	        
	      
	      JSONObject json = new JSONObject(result.toString());
	      
	      JSONArray arr = json.getJSONObject("response").getJSONArray("players");
	      String AvatarURL=arr.getJSONObject(0).getString("avatar");
	      OutString[0]=Long.toString(SteamID);OutString[1]=Username;OutString[2]=content;OutString[3]=AvatarURL;
	      
	      
	    }catch (Exception e){
	        System.out.println(e);
	        System.out.println("Could not retrieve"+Username+"'s Avatar");
	        
	    }
		
    	return OutString;
    }
	private void PostRequest(String Message)
    {
    	 try{
    	        URL url = new URL(apiURL);
    	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	        connection.setRequestMethod("POST");
    	        connection.setDoOutput(true);
    	        connection.setRequestProperty("Content-Type","application/json");
    	        connection.setRequestProperty("Accept", "application/json");
    	        
    	        String[] payloadData = ExtractMessageInfo(Message);
    	        
    	        System.out.println(payloadData[1]+": "+payloadData[2]);
    	        String payload = "{\r\n"
    	        		+ "  \"username\": \""+payloadData[1]+"\",\r\n"
    	        		+ "  \"avatar_url\": \""+payloadData[3]+"\",\r\n"
    	        		+ "  \"content\": \""+payloadData[2]+"\"}";
    	        		
    	        byte[] out = payload.getBytes(StandardCharsets.UTF_8);
    	        OutputStream stream = connection.getOutputStream();
    	        stream.write(out);
    	        
    	        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage()); // THis is optional
    	        stream.flush();
    	        connection.disconnect();
    	    }catch (Exception e){
    	        System.out.println(e);
    	        e.printStackTrace();
    	        System.out.println("Failed to Send a Request");
    	    }
    }
}
