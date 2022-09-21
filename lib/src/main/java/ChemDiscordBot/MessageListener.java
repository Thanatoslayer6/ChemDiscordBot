package ChemDiscordBot;

import org.jetbrains.annotations.NotNull;

import ChemEquationBalancer.Solver;
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
		OptionMapping argument = null;
		// If user enters /word_to_equation command
		if (ev.getName().equals("weq")) {
			argument = ev.getOption("equation");
			if (argument == null) {
				ev.reply("Hold it right there! kindly fix your arguments").queue();	
				return;
			} 
			String wordEquation = argument.getAsString();
			String finalAnswer = new String();

			try {
				finalAnswer = Parser.getFormula(wordEquation); 
				// Convert the word equation to its proper chemical equation
				ev.reply("The answer to " + "**" + wordEquation + "**" + " is converted as follows: " + "```" + finalAnswer + "```").queue();
			} catch (Exception e) {
				ev.reply("Hold it right there! " + "**" + wordEquation + "**" + " doesn't seem to work, kindly fix your arguments...").queue();
			}
		} else if (ev.getName().equals("bal")) {
			argument = ev.getOption("equation");
			if (argument == null) {
				ev.reply("Can't balance that stuff... O_o").queue();
				return;
			}
			try {
				Solver ss = new Solver(argument.getAsString());
				//System.out.println(ss.printEquation(false));
				ev.reply("The balanced chemical reation is: ```" + ss.printEquation(false) + "```").queue();
			} catch (Exception e) {
				ev.reply("Hold it right there! I can't balance " + "**" + argument.getAsString() + "**" + " kindly fix your arguments...").queue();
			}		
			
		}
	}

}
