package com.jwebmp.plugins.markdown;

import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import lombok.Getter;
import lombok.Setter;

/**
 * Configuration options for the PrismJS command line plugin within ngx-markdown.
 * <p>
 * The command line plugin displays a command line with a prompt and, optionally,
 * the output/response from the commands.
 * </p>
 *
 * <h3>Usage:</h3>
 * <pre>
 * Markdown md = Markdown.fromSource("/assets/commands.md");
 * md.setCommandLine(true);
 * md.setUser("chris");
 * md.setHost("remotehost");
 * md.setOutput("2, 4-8");
 *
 * // Or for a Windows prompt:
 * Markdown md2 = new Markdown("```powershell\nGet-Date\n```");
 * md2.setCommandLine(true);
 * md2.setPrompt("PS C:\\Users\\Chris>");
 * md2.setFilterOutput("(out)");
 * </pre>
 *
 * @param <J> self-referencing generic type for fluent API
 */
@Getter
@Setter
public class CommandLineOptions<J extends CommandLineOptions<J>> extends JavaScriptPart<J>
{
    /**
     * The user name displayed in the command prompt.
     * Results in {@code $} for regular users and {@code #} for root.
     */
    private String user;

    /**
     * The host name displayed in the command prompt.
     */
    private String host;

    /**
     * The full prompt string for non-Unix prompts (e.g., {@code "PS C:\Users\Chris>"}).
     */
    private String prompt;

    /**
     * The lines to present as output (no prompt, no highlighting).
     * Format: {@code "2, 4-8"} — single numbers, ranges, or comma-separated.
     */
    private String output;

    /**
     * A prefix to automatically detect output lines. Lines beginning with this
     * prefix are treated as output and the prefix is removed.
     */
    private String filterOutput;

    /**
     * Creates default command line options.
     */
    public CommandLineOptions()
    {
        // Default constructor
    }

    /**
     * Creates command line options for a Unix-style prompt.
     *
     * @param user the user name
     * @param host the host name
     * @return configured options
     */
    public static CommandLineOptions<?> unix(String user, String host)
    {
        CommandLineOptions<?> opts = new CommandLineOptions<>();
        opts.setUser(user);
        opts.setHost(host);
        return opts;
    }

    /**
     * Creates command line options for a custom prompt string.
     *
     * @param prompt the full prompt string
     * @return configured options
     */
    public static CommandLineOptions<?> withPrompt(String prompt)
    {
        CommandLineOptions<?> opts = new CommandLineOptions<>();
        opts.setPrompt(prompt);
        return opts;
    }
}

