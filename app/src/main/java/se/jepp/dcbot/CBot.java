package se.jepp.dcbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.IOException;

public class CBot extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        BotBrain brain = new BotBrain(event.getMessage().getContentRaw(), event.getAuthor().getName());

        if(brain.isMe()) return;

        /*if(brain.isEmbed()){
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Corona data for " + brain.country,"");
            embed.setDescription("Data will show: Country, province and when last updated");
            embed.addField(brain.cData, "",false);

            embed.setColor(Color.YELLOW);
            embed.setFooter("Bot created by " + event.getJDA().getUserById("162678142434017281").getName(), event.getGuild().getOwner().getUser().getAvatarUrl());
            event.getChannel().sendMessage(embed.build()).queue();
            embed.clear();
            return;
        }*/
       if (brain.isCommand()) {
            try {
                event.getChannel().sendMessage(brain.respond()).queue();
            } catch (IOException ignore) {}
        }
    }
}
