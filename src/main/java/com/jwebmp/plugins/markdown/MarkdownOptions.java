package com.jwebmp.plugins.markdown;

import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import lombok.Getter;
import lombok.Setter;

/**
 * Configuration options for the NGX Markdown component.
 * <p>
 * Maps to the {@code MarkdownModuleConfig} used with {@code provideMarkdown()} or
 * {@code MarkdownModule.forRoot()} in the Angular application.
 * </p>
 *
 * @param <J> self-referencing generic type for fluent API
 */
@Getter
@Setter
public class MarkdownOptions<J extends MarkdownOptions<J>> extends JavaScriptPart<J>
{
    /**
     * Whether to enable emoji shortname conversion.
     */
    private Boolean emoji;

    /**
     * Whether to parse markdown inline (omitting top-level paragraph wrapping).
     */
    private Boolean inline;

    /**
     * Whether to enable KaTeX math rendering.
     */
    private Boolean katex;

    /**
     * Whether to enable Mermaid diagram rendering.
     */
    private Boolean mermaid;

    /**
     * Whether to decode HTML entities.
     */
    private Boolean decodeHtml;

    /**
     * Whether to disable the Angular DomSanitizer.
     */
    private Boolean disableSanitizer;

    /**
     * Creates default markdown options.
     */
    public MarkdownOptions()
    {
        // Default constructor
    }
}

