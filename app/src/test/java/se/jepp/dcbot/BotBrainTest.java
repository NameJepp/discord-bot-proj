package se.jepp.dcbot;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotBrainTest {
    @Test
    public void shouldNotRespondToMessageIfSentByBot() throws Exception {
        BotBrain botBrain = new BotBrain("!ehee","Pandemico");
        assertTrue("Should not respond to self", botBrain.isMe());
        System.out.println(botBrain.isMe());
    }
    @Test
    public void shouldRespondToMessageIfNotSentByBot() throws Exception {
        BotBrain botBrain = new BotBrain("!ehee","Jepp");
        assertFalse("Should respond to others", botBrain.isMe());
        System.out.println(botBrain.isMe());
    }
    @Test
    public void shouldRespondIfMessageIsCommand() throws Exception {
        BotBrain botBrain = new BotBrain("!hello", "Jepp");
        assertTrue("Message must be a command!", botBrain.isCommand());
    }
    @Test
    public void shouldNotRespondIfMessageIsNotCommand() throws Exception {
        BotBrain botBrain = new BotBrain("sjajjeej", "Jepp");
        assertFalse("Message must not be a command!", botBrain.isCommand());
    }
    @Test
    public void shouldSendCorrectData() throws Exception{
        BotBrain botBrain = new BotBrain("!cData sweden", "Jepp");
        System.out.println(botBrain.respond());
    }
}

