
import java.io.IOException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class DiscordBot extends ListenerAdapter
{	
	public MainListener Listener;
    DiscordBot(String args)
    {
        if(args.equals("0"))
        {
        	return;
        }
        
        JDABuilder.createLight(args, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(this)
            .setActivity(Activity.playing("KF2 is FUN :)"))
            .build();
    }
    public void SetListener(MainListener Listener)
    {
    	this.Listener = Listener;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
    
        
        if(!event.getAuthor().isBot() && msg.getChannel().getIdLong()==Long.parseLong(Listener.ChannelID))
        {
        	try {
            	
    			Listener.sendMessage("[Discord] "+event.getAuthor().getName()+" "+msg.getContentRaw());
    		} catch (IOException e) {
    			
    			e.printStackTrace();
    			System.out.println("Cannot send "+msg.getContentRaw());
    		}
        }
        
    }
}
