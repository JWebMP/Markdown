package com.jwebmp.plugins.markdown;

import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import lombok.Getter;
import lombok.Setter;

/**
 * Configuration options for the Mermaid diagramming plugin within ngx-markdown.
 * <p>
 * Maps to {@code MermaidAPI.MermaidConfig} used in the mermaid configuration.
 * Can be provided globally via {@code provideMarkdown({ mermaidOptions: ... })}
 * or per-component via {@code [mermaidOptions]}.
 * </p>
 *
 * <h3>Usage:</h3>
 * <pre>
 * MermaidOptions opts = new MermaidOptions();
 * opts.setDarkMode(true);
 * opts.setLook("handDrawn");
 * opts.setTheme("dark");
 *
 * Markdown md = new Markdown("```mermaid\ngraph TD; A-->B;\n```");
 * md.setMermaid(true);
 * md.setMermaidOptions(opts);
 * </pre>
 *
 * @param <J> self-referencing generic type for fluent API
 * @see <a href="https://mermaid.js.org/config/schema-docs/config.html">Mermaid Configuration</a>
 */
@Getter
@Setter
public class MermaidOptions<J extends MermaidOptions<J>> extends JavaScriptPart<J>
{
    /**
     * Whether to use dark mode for diagrams.
     */
    private Boolean darkMode;

    /**
     * The Mermaid theme to use (e.g., "default", "dark", "forest", "neutral").
     */
    private String theme;

    /**
     * The visual look of diagrams (e.g., "classic", "handDrawn").
     */
    private String look;

    /**
     * The font family for Mermaid text.
     */
    private String fontFamily;

    /**
     * Whether to start Mermaid on page load.
     */
    private Boolean startOnLoad;

    /**
     * Whether to enable secure mode.
     */
    private Boolean securityLevel;

    /**
     * The log level for Mermaid (1-5, where 1 is debug and 5 is fatal).
     */
    private Integer logLevel;

    /**
     * Creates default Mermaid options.
     */
    public MermaidOptions()
    {
        // Default constructor
    }

    /**
     * Converts these options to an Angular template expression string.
     *
     * @return the Angular object literal expression
     */
    public String toAngularExpression()
    {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;

        if (darkMode != null)
        {
            sb.append("darkMode: ").append(darkMode);
            first = false;
        }
        if (theme != null)
        {
            if (!first) sb.append(", ");
            sb.append("theme: '").append(theme).append("'");
            first = false;
        }
        if (look != null)
        {
            if (!first) sb.append(", ");
            sb.append("look: '").append(look).append("'");
            first = false;
        }
        if (fontFamily != null)
        {
            if (!first) sb.append(", ");
            sb.append("fontFamily: '").append(fontFamily).append("'");
            first = false;
        }
        if (startOnLoad != null)
        {
            if (!first) sb.append(", ");
            sb.append("startOnLoad: ").append(startOnLoad);
            first = false;
        }
        if (logLevel != null)
        {
            if (!first) sb.append(", ");
            sb.append("logLevel: ").append(logLevel);
        }

        sb.append("}");
        return sb.toString();
    }
}

