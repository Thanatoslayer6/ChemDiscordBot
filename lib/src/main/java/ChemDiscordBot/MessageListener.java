package ChemDiscordBot;

import org.jetbrains.annotations.NotNull;

import ChemWordEquationConverter.Parser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class MessageListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(@NotNull MessageReceivedEvent ev) {
		super.onMessageReceived(ev);
		//System.out.println(ev.getMessage().getContentDisplay());
	}
	
	@Override
	public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent ev) {
		super.onSlashCommandInteraction(ev);

		// If user enters /word_to_equation command
		if (ev.getName().equals("weq")) {
			OptionMapping argument = ev.getOption("equation");
			if (argument == null) {
				ev.reply("Hold it right there! kindly fix your arguments").queue();	
				return;
			} 
			String wordEquation = argument.getAsString();
			String finalAnswer = new String();

			try {
				finalAnswer = Parser.getFormula(wordEquation); 
				// Convert the word equation to its proper chemical equation
				ev.reply("The answer to " + "**" + wordEquation + "**" + " is converted as.. " + "```" + finalAnswer + "```").queue();
			} catch (Exception e) {
				ev.reply("Hold it right there! " + "**" + wordEquation + "**" + " doesn't seem to work, kindly fix your arguments...").queue();
			}
		}
	}

}
