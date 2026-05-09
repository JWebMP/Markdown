package com.jwebmp.plugins.markdown.config;

import com.jwebmp.core.base.angular.client.annotations.angularconfig.NgScript;
import com.jwebmp.core.base.angular.client.annotations.angularconfig.NgStyleSheet;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootImportProvider;
import com.jwebmp.core.base.angular.client.annotations.boot.NgBootImportReference;
import com.jwebmp.core.base.angular.client.annotations.typescript.TsDependency;
import com.jwebmp.core.base.angular.client.annotations.typescript.TsDevDependency;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.plugins.PluginStatus;
import com.jwebmp.core.services.IPage;
import com.jwebmp.core.services.IPageConfigurator;
import jakarta.validation.constraints.NotNull;

/**
 * Page configurator for the NGX Markdown plugin.
 * <p>
 * Registers the necessary TypeScript dependencies (ngx-markdown, marked, prismjs, mermaid, katex,
 * emoji-toolkit, clipboard) and configures the Angular module providers required for markdown rendering.
 * </p>
 * <p>
 * The ngx-markdown library combines:
 * <ul>
 *   <li><b>Marked</b> — to parse markdown to HTML</li>
 *   <li><b>PrismJS</b> — for language syntax highlighting</li>
 *   <li><b>Mermaid</b> — for diagrams and charts visualization</li>
 *   <li><b>KaTeX</b> — for math expression rendering</li>
 *   <li><b>Emoji-Toolkit</b> — for emoji support</li>
 *   <li><b>Clipboard.js</b> — to copy code blocks to clipboard</li>
 * </ul>
 * <p>
 * This configurator is automatically discovered via the Java ServiceLoader mechanism
 * through the {@code META-INF/services/com.jwebmp.core.services.IPageConfigurator} file.
 * </p>
 */
@PluginInformation(
        pluginName = "NGX Markdown",
        pluginUniqueName = "ngx-markdown",
        pluginDescription = "Angular markdown component/directive/pipe with Prism.js syntax highlighting, " +
                "Mermaid diagrams, KaTeX math rendering, emoji support, and clipboard copy. " +
                "Built on the ngx-markdown library by jfcere.",
        pluginVersion = "19.0.0",
        pluginCategories = "markdown, syntax highlighting, diagrams, mermaid, katex, emoji",
        pluginSubtitle = "Full-featured Markdown rendering for Angular with syntax highlighting, " +
                "diagrams, math expressions, and emoji — integrated into JWebMP.",
        pluginGitUrl = "https://github.com/JWebMP/Plugins/Markdown",
        pluginWikiUrl = "https://github.com/JWebMP/Plugins/Markdown/wiki",
        pluginOriginalHomepage = "https://github.com/jfcere/ngx-markdown",
        pluginDownloadUrl = "https://mvnrepository.com/artifact/com.jwebmp.plugins/ngx-markdown",
        pluginIconUrl = "",
        pluginIconImageUrl = "",
        pluginLastUpdatedDate = "2026/03/25",
        pluginGroupId = "com.jwebmp.plugins",
        pluginArtifactId = "ngx-markdown",
        pluginModuleName = "com.jwebmp.plugins.markdown",
        pluginStatus = PluginStatus.DevelopmentStarted
)

// Core dependencies
@TsDependency(value = "ngx-markdown", version = ">=21.1.0")
@TsDependency(value = "marked", version = ">=18.0.0")

// Syntax highlighting (PrismJS) — ngx-markdown uses prismjs directly for highlighting
// The prism plugin also declares this dependency; npm deduplicates to a single version
@TsDependency(value = "prismjs", version = ">=1.30.0")
@TsDevDependency(value = "@types/prismjs", version = ">=1.26.0")

// PrismJS theme CSS — solarized light as default
//@NgStyleSheet(value = "node_modules/prismjs/themes/prism-solarizedlight.css", name = "PrismJS Theme")
// PrismJS line-numbers plugin CSS
@NgStyleSheet(value = "node_modules/prismjs/plugins/line-numbers/prism-line-numbers.css", name = "PrismJS Line Numbers CSS")
// PrismJS toolbar plugin CSS (required by clipboard/copy-to-clipboard plugin)
@NgStyleSheet(value = "node_modules/prismjs/plugins/toolbar/prism-toolbar.css", name = "PrismJS Toolbar CSS")

// PrismJS core — import via ES module and assign to globalThis so ngx-markdown
// and all language grammars share the same Prism instance
@NgBootImportReference(value = "Prism", reference = "prismjs", direct = true, assignToGlobal = true)

// PrismJS language components — side-effect imports register on the shared Prism instance
@NgBootImportReference(value = "", reference = "prismjs/components/prism-markup", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-css", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-clike", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-javascript", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-typescript", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-java", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-json", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-bash", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-yaml", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-xml-doc", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-sql", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-properties", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-diff", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-kotlin", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-groovy", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-python", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-scss", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/components/prism-markdown", sideEffect = true)

// PrismJS plugins — line-numbers and toolbar (needed for clipboard button)
@NgBootImportReference(value = "", reference = "prismjs/plugins/line-numbers/prism-line-numbers", sideEffect = true)
@NgBootImportReference(value = "", reference = "prismjs/plugins/toolbar/prism-toolbar", sideEffect = true)

// Diagrams — mermaid must be imported so ngx-markdown can discover it at runtime
@TsDependency(value = "mermaid", version = ">=11.13.0")
@NgBootImportReference(value = "mermaid", reference = "mermaid", direct = true, assignToGlobal = true)

// Math rendering
@TsDependency(value = "katex", version = ">=0.16.0")
@TsDevDependency(value = "@types/katex", version = "^0.16.0")
@NgScript(value = "node_modules/katex/dist/katex.min.js", name = "KaTeX")
@NgStyleSheet(value = "node_modules/katex/dist/katex.min.css", name = "KaTeX CSS")

// Emoji support
@TsDependency(value = "emoji-toolkit", version = "^10.0.0")
@NgScript(value = "node_modules/emoji-toolkit/lib/js/joypixels.min.js", name = "Emoji Toolkit")

// Clipboard support
@TsDependency(value = "clipboard", version = ">=2.0.11")
@NgScript(value = "node_modules/clipboard/dist/clipboard.min.js", name = "Clipboard")
@NgBootImportReference(value = "ClipboardJS", reference = "clipboard", direct = true, assignToGlobal = true)

// Angular bootstrap: provideMarkdown() with mermaid + clipboard config
@NgBootImportProvider("provideMarkdown({ mermaidOptions: { provide: MERMAID_OPTIONS, useValue: { startOnLoad: false } }, clipboardOptions: { provide: CLIPBOARD_OPTIONS, useValue: { buttonComponent: undefined } } })")
@NgBootImportReference(value = "provideMarkdown", reference = "ngx-markdown")
@NgBootImportReference(value = "MERMAID_OPTIONS", reference = "ngx-markdown")
@NgBootImportReference(value = "CLIPBOARD_OPTIONS", reference = "ngx-markdown")
public class MarkdownPageConfigurator implements IPageConfigurator<MarkdownPageConfigurator>
{
    /**
     * Whether the NGX Markdown plugin is enabled.
     */
    public static boolean enabled = true;

    /**
     * Creates a new MarkdownPageConfigurator instance.
     */
    public MarkdownPageConfigurator()
    {
        // Nothing needed
    }

    @NotNull
    @Override
    public IPage<?> configure(IPage<?> page)
    {
        return page;
    }

    @Override
    public boolean enabled()
    {
        return enabled;
    }
}

