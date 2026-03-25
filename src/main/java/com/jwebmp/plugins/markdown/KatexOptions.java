package com.jwebmp.plugins.markdown;

import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import lombok.Getter;
import lombok.Setter;

/**
 * Configuration options for the KaTeX math rendering plugin within ngx-markdown.
 * <p>
 * Maps to the KaTeX options and Auto-Render options used by ngx-markdown.
 * Can be provided per-component via {@code [katexOptions]}.
 * </p>
 *
 * <h3>Usage:</h3>
 * <pre>
 * KatexOptions opts = new KatexOptions();
 * opts.setDisplayMode(true);
 * opts.setThrowOnError(false);
 * opts.setErrorColor("#cc0000");
 *
 * Markdown md = new Markdown("$$E = mc^2$$");
 * md.setKatex(true);
 * md.setKatexOptions(opts);
 * </pre>
 *
 * @param <J> self-referencing generic type for fluent API
 * @see <a href="https://katex.org/docs/options.html">KaTeX Options</a>
 * @see <a href="https://katex.org/docs/autorender.html">KaTeX Auto-Render Options</a>
 */
@Getter
@Setter
public class KatexOptions<J extends KatexOptions<J>> extends JavaScriptPart<J>
{
    /**
     * Whether to render the math in display mode (block-level, centered).
     * Default is false (inline mode).
     */
    private Boolean displayMode;

    /**
     * Whether to throw a {@code ParseError} when KaTeX encounters an unsupported command.
     * Default is true.
     */
    private Boolean throwOnError;

    /**
     * Color used for invalid LaTeX in the rendered output when throwOnError is false.
     * Default is "#cc0000".
     */
    private String errorColor;

    /**
     * Whether to produce MathML output alongside HTML.
     * Default is true.
     */
    private Boolean output;

    /**
     * Whether to place KaTeX code in the global group.
     */
    private Boolean globalGroup;

    /**
     * Max size for user-specified sizes. -1 disables the limit.
     */
    private Integer maxSize;

    /**
     * Max number of macro expansions. Default is 1000.
     */
    private Integer maxExpand;

    /**
     * Whether to allow certain commands that could enable XSS attacks.
     */
    private Boolean trust;

    /**
     * Whether to use strict mode. Warns about non-standard LaTeX features.
     */
    private Boolean strict;

    /**
     * Creates default KaTeX options.
     */
    public KatexOptions()
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

        if (displayMode != null)
        {
            sb.append("displayMode: ").append(displayMode);
            first = false;
        }
        if (throwOnError != null)
        {
            if (!first) sb.append(", ");
            sb.append("throwOnError: ").append(throwOnError);
            first = false;
        }
        if (errorColor != null)
        {
            if (!first) sb.append(", ");
            sb.append("errorColor: '").append(errorColor).append("'");
            first = false;
        }
        if (maxSize != null)
        {
            if (!first) sb.append(", ");
            sb.append("maxSize: ").append(maxSize);
            first = false;
        }
        if (maxExpand != null)
        {
            if (!first) sb.append(", ");
            sb.append("maxExpand: ").append(maxExpand);
            first = false;
        }
        if (trust != null)
        {
            if (!first) sb.append(", ");
            sb.append("trust: ").append(trust);
            first = false;
        }
        if (strict != null)
        {
            if (!first) sb.append(", ");
            sb.append("strict: ").append(strict);
        }

        sb.append("}");
        return sb.toString();
    }
}

