package ChemDiscordBot;

import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
	public static void main(String[] args) throws LoginException {
		Dotenv conf = Dotenv.configure().load();
		JDABuilder jdb = JDABuilder.createDefault(conf.get("TOKEN"));
		JDA jda = jdb
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.addEventListeners(new MessageListener())
				.build();

		// Commands
		jda.upsertCommand("weq", "Converts a chemical word equation")
			.addOption(OptionType.STRING, "equation", "The complete chemical word equation", true)
			.queue();
	}
}
