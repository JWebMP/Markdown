/**
 * JWebMP plugin for ngx-markdown — full-featured Markdown rendering for Angular.
 * <p>
 * This package provides Java components that generate Angular-compatible HTML for the
 * <a href="https://github.com/jfcere/ngx-markdown">ngx-markdown</a> library, enabling
 * rich Markdown rendering in JWebMP web applications.
 * </p>
 *
 * <h2>Features</h2>
 * <ul>
 *   <li><b>Markdown Parsing</b> — via <a href="https://marked.js.org/">Marked</a></li>
 *   <li><b>Syntax Highlighting</b> — via <a href="https://prismjs.com/">PrismJS</a> with line numbers, line highlight, and command line plugins</li>
 *   <li><b>Diagrams</b> — via <a href="https://mermaid.js.org/">Mermaid</a></li>
 *   <li><b>Math Rendering</b> — via <a href="https://katex.org/">KaTeX</a></li>
 *   <li><b>Emoji Support</b> — via <a href="https://github.com/nicecatch/emoji-toolkit">Emoji-Toolkit</a></li>
 *   <li><b>Clipboard</b> — via <a href="https://clipboardjs.com/">Clipboard.js</a></li>
 * </ul>
 *
 * <h2>Key Classes</h2>
 * <ul>
 *   <li>{@link com.jwebmp.plugins.markdown.Markdown} — Main component for rendering the {@code <markdown>} tag</li>
 *   <li>{@link com.jwebmp.plugins.markdown.MarkdownOptions} — Pipe/component configuration options</li>
 *   <li>{@link com.jwebmp.plugins.markdown.MermaidOptions} — Mermaid diagram configuration</li>
 *   <li>{@link com.jwebmp.plugins.markdown.KatexOptions} — KaTeX math rendering configuration</li>
 *   <li>{@link com.jwebmp.plugins.markdown.CommandLineOptions} — PrismJS command line plugin options</li>
 * </ul>
 *
 * @see <a href="https://github.com/jfcere/ngx-markdown">ngx-markdown on GitHub</a>
 * @see <a href="https://jfcere.github.io/ngx-markdown">ngx-markdown Demo</a>
 */
package com.jwebmp.plugins.markdown;

