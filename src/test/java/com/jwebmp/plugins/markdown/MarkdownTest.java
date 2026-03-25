package com.jwebmp.plugins.markdown;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the NGX Markdown plugin components.
 */
public class MarkdownTest
{
    @Test
    void testDefaultConstruction()
    {
        Markdown<?> md = new Markdown<>();
        assertNotNull(md);
    }

    @Test
    void testConstructionWithContent()
    {
        Markdown<?> md = new Markdown<>("# Hello World");
        assertEquals("# Hello World", md.getMarkdownContent());
    }

    @Test
    void testFromSource()
    {
        Markdown<?> md = Markdown.fromSource("/assets/readme.md");
        assertEquals("/assets/readme.md", md.getSrc());
        assertNull(md.getData());
        assertNull(md.getMarkdownContent());
    }

    @Test
    void testFromData()
    {
        Markdown<?> md = Markdown.fromData("myMarkdownVar");
        assertEquals("myMarkdownVar", md.getData());
        assertNull(md.getSrc());
        assertNull(md.getMarkdownContent());
    }

    @Test
    void testFullFactory()
    {
        Markdown<?> md = Markdown.full("# Test");
        assertEquals("# Test", md.getMarkdownContent());
        assertTrue(md.getLineNumbers());
        assertTrue(md.getMermaid());
        assertTrue(md.getKatex());
        assertTrue(md.getEmoji());
        assertTrue(md.getClipboard());
    }

    @Test
    void testLineNumbers()
    {
        Markdown<?> md = new Markdown<>();
        md.setLineNumbers(true);
        md.setStart(5);
        assertTrue(md.getLineNumbers());
        assertEquals(5, md.getStart());
    }

    @Test
    void testLineHighlight()
    {
        Markdown<?> md = new Markdown<>();
        md.setLineHighlight(true);
        md.setLine("6, 10-16");
        md.setLineOffset(5);
        assertTrue(md.getLineHighlight());
        assertEquals("6, 10-16", md.getLine());
        assertEquals(5, md.getLineOffset());
    }

    @Test
    void testCommandLine()
    {
        Markdown<?> md = new Markdown<>();
        md.setCommandLine(true);
        md.setUser("chris");
        md.setHost("remotehost");
        md.setOutput("2, 4-8");
        assertTrue(md.getCommandLine());
        assertEquals("chris", md.getUser());
        assertEquals("remotehost", md.getHost());
        assertEquals("2, 4-8", md.getOutput());
    }

    @Test
    void testCommandLinePrompt()
    {
        Markdown<?> md = new Markdown<>();
        md.setCommandLine(true);
        md.setPrompt("PS C:\\Users\\Chris>");
        md.setFilterOutput("(out)");
        assertEquals("PS C:\\Users\\Chris>", md.getPrompt());
        assertEquals("(out)", md.getFilterOutput());
    }

    @Test
    void testEmoji()
    {
        Markdown<?> md = new Markdown<>("I :heart: ngx-markdown");
        md.setEmoji(true);
        assertTrue(md.getEmoji());
    }

    @Test
    void testKatex()
    {
        Markdown<?> md = new Markdown<>("$$E = mc^2$$");
        md.setKatex(true);
        KatexOptions<?> opts = new KatexOptions<>();
        opts.setDisplayMode(true);
        opts.setThrowOnError(false);
        opts.setErrorColor("#cc0000");
        md.setKatexOptions(opts);
        assertTrue(md.getKatex());
        assertNotNull(md.getKatexOptions());
        assertTrue(opts.getDisplayMode());
        assertFalse(opts.getThrowOnError());
        assertEquals("#cc0000", opts.getErrorColor());
    }

    @Test
    void testKatexOptionsExpression()
    {
        KatexOptions<?> opts = new KatexOptions<>();
        opts.setDisplayMode(true);
        opts.setThrowOnError(false);
        String expr = opts.toAngularExpression();
        assertTrue(expr.contains("displayMode: true"));
        assertTrue(expr.contains("throwOnError: false"));
    }

    @Test
    void testMermaid()
    {
        Markdown<?> md = new Markdown<>("```mermaid\ngraph TD; A-->B;\n```");
        md.setMermaid(true);
        MermaidOptions<?> opts = new MermaidOptions<>();
        opts.setDarkMode(true);
        opts.setLook("handDrawn");
        opts.setTheme("dark");
        md.setMermaidOptions(opts);
        assertTrue(md.getMermaid());
        assertNotNull(md.getMermaidOptions());
    }

    @Test
    void testMermaidOptionsExpression()
    {
        MermaidOptions<?> opts = new MermaidOptions<>();
        opts.setDarkMode(true);
        opts.setTheme("dark");
        opts.setLook("handDrawn");
        String expr = opts.toAngularExpression();
        assertTrue(expr.contains("darkMode: true"));
        assertTrue(expr.contains("theme: 'dark'"));
        assertTrue(expr.contains("look: 'handDrawn'"));
    }

    @Test
    void testClipboard()
    {
        Markdown<?> md = Markdown.fromSource("/assets/code.md");
        md.setClipboard(true);
        assertTrue(md.getClipboard());
    }

    @Test
    void testDisableSanitizer()
    {
        Markdown<?> md = new Markdown<>("# Test");
        md.setDisableSanitizer(true);
        assertTrue(md.getDisableSanitizer());
    }

    @Test
    void testInline()
    {
        Markdown<?> md = new Markdown<>();
        md.setData("markdownVar");
        md.setInline(true);
        assertTrue(md.getInline());
    }

    @Test
    void testCommandLineOptionsFactory()
    {
        CommandLineOptions<?> unix = CommandLineOptions.unix("chris", "remotehost");
        assertEquals("chris", unix.getUser());
        assertEquals("remotehost", unix.getHost());

        CommandLineOptions<?> custom = CommandLineOptions.withPrompt("PS C:\\>");
        assertEquals("PS C:\\>", custom.getPrompt());
    }

    @Test
    void testMarkdownOptions()
    {
        MarkdownOptions<?> opts = new MarkdownOptions<>();
        opts.setEmoji(true);
        opts.setKatex(true);
        opts.setMermaid(true);
        opts.setInline(false);
        opts.setDecodeHtml(true);
        opts.setDisableSanitizer(false);
        assertTrue(opts.getEmoji());
        assertTrue(opts.getKatex());
        assertTrue(opts.getMermaid());
        assertFalse(opts.getInline());
        assertTrue(opts.getDecodeHtml());
        assertFalse(opts.getDisableSanitizer());
    }
}

