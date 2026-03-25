package com.jwebmp.plugins.markdown;

import com.jwebmp.core.base.angular.client.annotations.references.NgImportModule;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.client.services.interfaces.AnnotationUtils;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.plugins.ComponentInformation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JWebMP component for the ngx-markdown Angular library.
 * <p>
 * Renders a {@code <markdown>} Angular component that supports:
 * <ul>
 *   <li>Static markdown content via transclusion</li>
 *   <li>Variable-bound markdown via {@code [data]} binding</li>
 *   <li>Remote markdown files via {@code [src]} binding</li>
 *   <li>PrismJS syntax highlighting with {@code lineNumbers}, {@code lineHighlight}, and {@code commandLine} plugins</li>
 *   <li>Mermaid diagram rendering</li>
 *   <li>KaTeX math expression rendering</li>
 *   <li>Emoji shortname conversion</li>
 *   <li>Clipboard copy-to-clipboard for code blocks</li>
 *   <li>Inline markdown parsing</li>
 * </ul>
 *
 * <h3>Usage Examples:</h3>
 * <pre>
 * // Static markdown
 * Markdown md = new Markdown("# Hello World\nThis is **bold** text.");
 *
 * // Remote source
 * Markdown md = Markdown.fromSource("/assets/readme.md");
 *
 * // Variable binding with syntax highlighting
 * Markdown md = Markdown.fromData("myMarkdownVar");
 * md.setLineNumbers(true);
 *
 * // With Mermaid diagrams
 * Markdown md = new Markdown("```mermaid\ngraph TD;\n  A-->B;\n```");
 * md.setMermaid(true);
 *
 * // With KaTeX math
 * Markdown md = new Markdown("$$E = mc^2$$");
 * md.setKatex(true);
 *
 * // With emoji support
 * Markdown md = new Markdown("I :heart: ngx-markdown");
 * md.setEmoji(true);
 *
 * // With clipboard copy
 * Markdown md = Markdown.fromSource("/assets/code-samples.md");
 * md.setClipboard(true);
 * </pre>
 *
 * @see MarkdownOptions
 * @see MermaidOptions
 * @see KatexOptions
 * @see CommandLineOptions
 * @see com.jwebmp.plugins.markdown.config.MarkdownPageConfigurator
 */
@Getter
@Setter
@ComponentInformation(name = "NGX Markdown",
        description = "Markdown rendering component with Prism syntax highlighting, Mermaid diagrams, KaTeX math, emoji, and clipboard support",
        url = "https://github.com/jfcere/ngx-markdown")
@NgImportReference(value = "MarkdownComponent", reference = "ngx-markdown")
@NgImportModule("MarkdownComponent")
public class Markdown<J extends Markdown<J>> extends DivSimple<J> implements INgComponent<J>
{
    private static final AtomicInteger FIELD_COUNTER = new AtomicInteger(0);

    // ── Content sources ──

    /**
     * Static markdown content (rendered via transclusion inside the tag).
     */
    private String markdownContent;

    /**
     * Angular expression for variable binding via {@code [data]}.
     * This should be the name of a TypeScript variable/property.
     */
    private String data;

    /**
     * Remote source URL via {@code [src]}.
     */
    private String src;

    /**
     * Whether to parse as inline markdown, omitting top-level paragraph wrapping.
     */
    private Boolean inline;

    // ── Prism syntax highlighting ──

    /**
     * Whether to show line numbers in code blocks.
     */
    private Boolean lineNumbers;

    /**
     * Starting line offset for line number display.
     */
    private Integer start;

    /**
     * Whether to enable line highlighting.
     */
    private Boolean lineHighlight;

    /**
     * Lines to highlight. Format: {@code "6, 10-16"}.
     */
    private String line;

    /**
     * Line offset for line highlighting.
     */
    private Integer lineOffset;

    // ── Command line plugin ──

    /**
     * Whether to enable the command line plugin.
     */
    private Boolean commandLine;

    /**
     * User name for the command line prompt.
     */
    private String user;

    /**
     * Host name for the command line prompt.
     */
    private String host;

    /**
     * Custom prompt string (e.g., {@code "PS C:\Users\Chris>"}).
     */
    private String prompt;

    /**
     * Output lines specification (e.g., {@code "2, 4-8"}).
     */
    private String output;

    /**
     * Prefix to filter and mark as output (e.g., {@code "(out)"}).
     */
    private String filterOutput;

    // ── Emoji ──

    /**
     * Whether to enable emoji shortname conversion.
     */
    private Boolean emoji;

    // ── KaTeX ──

    /**
     * Whether to enable KaTeX math rendering.
     */
    private Boolean katex;

    /**
     * KaTeX rendering options.
     */
    private KatexOptions<?> katexOptions;

    // ── Mermaid ──

    /**
     * Whether to enable Mermaid diagram rendering.
     */
    private Boolean mermaid;

    /**
     * Mermaid configuration options.
     */
    private MermaidOptions<?> mermaidOptions;

    // ── Clipboard ──

    /**
     * Whether to enable clipboard copy-to-clipboard for code blocks.
     */
    private Boolean clipboard;

    // ── Sanitization ──

    /**
     * Whether to disable Angular's DomSanitizer for this component.
     */
    private Boolean disableSanitizer;

    /**
     * Creates an empty markdown component.
     */
    public Markdown()
    {
        setTag("markdown");
    }

    /**
     * Creates a markdown component with static content rendered via transclusion.
     *
     * @param markdownContent the raw markdown text
     */
    public Markdown(@NotNull String markdownContent)
    {
        this();
        this.markdownContent = markdownContent;
    }

    /**
     * Creates a markdown component that loads content from a remote URL.
     *
     * @param src the URL path to the markdown file
     * @return a configured Markdown component
     */
    public static Markdown<?> fromSource(@NotNull String src)
    {
        Markdown<?> md = new Markdown<>();
        md.setSrc(src);
        return md;
    }

    /**
     * Creates a markdown component that binds to a TypeScript variable.
     *
     * @param dataExpression the Angular expression for the data binding
     * @return a configured Markdown component
     */
    public static Markdown<?> fromData(@NotNull String dataExpression)
    {
        Markdown<?> md = new Markdown<>();
        md.setData(dataExpression);
        return md;
    }

    /**
     * Creates a markdown component with all common features enabled
     * (syntax highlighting, mermaid, katex, emoji, clipboard).
     *
     * @param markdownContent the raw markdown text
     * @return a fully-featured Markdown component
     */
    public static Markdown<?> full(@NotNull String markdownContent)
    {
        Markdown<?> md = new Markdown<>(markdownContent);
        md.setLineNumbers(true);
        md.setMermaid(true);
        md.setKatex(true);
        md.setEmoji(true);
        md.setClipboard(true);
        return md;
    }

    @Override
    protected void init()
    {
        if (!isInitialized())
        {
            // Content sources
            if (data != null)
            {
                addAttribute("[data]", data);
            }
            if (src != null)
            {
                addAttribute("[src]", "'" + src + "'");
            }
            if (inline != null && inline)
            {
                addAttribute("[inline]", "true");
            }

            // Prism line numbers
            if (lineNumbers != null && lineNumbers)
            {
                addAttribute("lineNumbers", "");
                if (start != null)
                {
                    addAttribute("[start]", String.valueOf(start));
                }
            }

            // Prism line highlight
            if (lineHighlight != null && lineHighlight)
            {
                addAttribute("lineHighlight", "");
                if (line != null)
                {
                    addAttribute("[line]", "'" + line + "'");
                }
                if (lineOffset != null)
                {
                    addAttribute("[lineOffset]", String.valueOf(lineOffset));
                }
            }

            // Command line
            if (commandLine != null && commandLine)
            {
                addAttribute("commandLine", "");
                if (user != null)
                {
                    addAttribute("[user]", "'" + user + "'");
                }
                if (host != null)
                {
                    addAttribute("[host]", "'" + host + "'");
                }
                if (prompt != null)
                {
                    addAttribute("[prompt]", "'" + escapeForAngularAttribute(prompt) + "'");
                }
                if (output != null)
                {
                    addAttribute("[output]", "'" + output + "'");
                }
                if (filterOutput != null)
                {
                    addAttribute("[filterOutput]", "'" + filterOutput + "'");
                }
            }

            // Emoji
            if (emoji != null && emoji)
            {
                addAttribute("emoji", "");
            }

            // KaTeX
            if (katex != null && katex)
            {
                addAttribute("katex", "");
                if (katexOptions != null)
                {
                    addAttribute("[katexOptions]", katexOptions.toAngularExpression());
                }
            }

            // Mermaid
            if (mermaid != null && mermaid)
            {
                addAttribute("mermaid", "");
                if (mermaidOptions != null)
                {
                    addAttribute("[mermaidOptions]", mermaidOptions.toAngularExpression());
                }
            }

            // Clipboard
            if (clipboard != null && clipboard)
            {
                addAttribute("clipboard", "");
            }

            // Sanitization
            if (disableSanitizer != null && disableSanitizer)
            {
                addAttribute("[disableSanitizer]", "true");
            }

            // Static content — bind via [data] to a generated TypeScript field.
            // Transclusion (inner text) is NOT used because Angular's template parser
            // chokes on unescaped { } and < > characters that appear in code samples.
            if (markdownContent != null && data == null && src == null)
            {
                String fieldName = "mdContent_" + FIELD_COUNTER.getAndIncrement();
                String escaped = escapeForTypeScriptString(markdownContent);
                addConfiguration(AnnotationUtils.getNgField(fieldName + " = `" + escaped + "`;", false, true));
                addAttribute("[data]", fieldName);
            }
        }
        super.init();
    }

    /**
     * Escapes a string for safe inclusion in a TypeScript template literal (backtick string).
     * Escapes backticks and {@code ${}} interpolation sequences.
     *
     * @param input the raw string
     * @return the escaped string safe for use inside TS backticks
     */
    private String escapeForTypeScriptString(String input)
    {
        if (input == null)
        {
            return "";
        }
        return input.replace("\\", "\\\\")
                    .replace("`", "\\`")
                    .replace("${", "\\${");
    }

    /**
     * Escapes a string for safe use in an Angular attribute binding.
     *
     * @param input the raw string to escape
     * @return the escaped string
     */
    private String escapeForAngularAttribute(String input)
    {
        if (input == null)
        {
            return "";
        }
        return input.replace("\\", "\\\\")
                    .replace("'", "\\'");
    }
}


