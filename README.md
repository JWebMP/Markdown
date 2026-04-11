# JWebMP NGX Markdown

[![Maven Central](https://img.shields.io/maven-central/v/com.jwebmp.plugins/ngx-markdown)](https://central.sonatype.com/artifact/com.jwebmp.plugins/ngx-markdown)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue)](https://www.apache.org/licenses/LICENSE-2.0)

![Java 25+](https://img.shields.io/badge/Java-25%2B-green)
![Modular](https://img.shields.io/badge/Modular-JPMS-green)
![Angular](https://img.shields.io/badge/Angular-20-DD0031?logo=angular)
![TypeScript](https://img.shields.io/badge/TypeScript-5-3178C6?logo=typescript)

<!-- Tech icons row -->
![Marked](https://img.shields.io/badge/Marked-17-000000)
![PrismJS](https://img.shields.io/badge/PrismJS-1.30-5C2D91)
![Mermaid](https://img.shields.io/badge/Mermaid-11-FF3670)
![KaTeX](https://img.shields.io/badge/KaTeX-0.16-6B9F35)
![JWebMP](https://img.shields.io/badge/JWebMP-2.0-0A7)

Full-featured Markdown rendering for JWebMP with Angular 21. Wraps [ngx-markdown](https://github.com/jfcere/ngx-markdown) providing Prism.js syntax highlighting, Mermaid diagrams, KaTeX math rendering, emoji support, and clipboard copy.

Built on [ngx-markdown](https://github.com/jfcere/ngx-markdown) · [Marked](https://marked.js.org/) · [PrismJS](https://prismjs.com/) · [Mermaid](https://mermaid.js.org/) · [KaTeX](https://katex.org/) · JPMS module `com.jwebmp.plugins.markdown` · Java 25+

---

## Features

- **Markdown Parsing** — Full GitHub Flavored Markdown via Marked
- **Syntax Highlighting** — PrismJS with line numbers, line highlight, and command line plugins
- **Diagrams** — Mermaid flowcharts, sequence diagrams, Gantt charts, and more
- **Math Rendering** — KaTeX for LaTeX math expressions
- **Emoji** — Shortname-to-unicode conversion via Emoji-Toolkit
- **Clipboard** — One-click copy-to-clipboard for code blocks
- **Three Content Modes** — Static transclusion, variable binding (`[data]`), remote source (`[src]`)
- **Type-Safe API** — Enum-based and options-class configuration
- **JPMS** — Fully modular with `module-info.java`
- **Guice DI** — Auto-discovered via ServiceLoader

## Installation

### Maven

```xml
<dependency>
    <groupId>com.jwebmp.plugins</groupId>
    <artifactId>ngx-markdown</artifactId>
    <version>2.0.0-RC3</version>
</dependency>
```

### Module System

Add to your `module-info.java`:

```java
requires com.jwebmp.plugins.markdown;
```

## Usage

### Static Markdown Content

```java
Markdown<?> md = new Markdown<>("# Hello World\nThis is **bold** and *italic*.");
page.add(md);
```

### Remote Source

```java
Markdown<?> md = Markdown.fromSource("/assets/readme.md");
page.add(md);
```

### Variable Binding

```java
Markdown<?> md = Markdown.fromData("myMarkdownVariable");
page.add(md);
```

### Syntax Highlighting with Line Numbers

```java
Markdown<?> md = Markdown.fromSource("/assets/code-samples.md");
md.setLineNumbers(true);
md.setStart(5); // start line numbers from 5
```

### Line Highlighting

```java
Markdown<?> md = Markdown.fromSource("/assets/code.md");
md.setLineHighlight(true);
md.setLine("6, 10-16");
md.setLineOffset(5);
```

### Command Line

```java
// Unix prompt
Markdown<?> md = Markdown.fromSource("/assets/commands.bash");
md.setCommandLine(true);
md.setUser("chris");
md.setHost("remotehost");
md.setOutput("2, 4-8");

// Windows prompt
Markdown<?> md2 = new Markdown<>("```powershell\nGet-Date\n```");
md2.setCommandLine(true);
md2.setPrompt("PS C:\\Users\\Chris>");
md2.setFilterOutput("(out)");
```

### Mermaid Diagrams

```java
Markdown<?> md = new Markdown<>("```mermaid\ngraph TD;\n  A-->B;\n  B-->C;\n```");
md.setMermaid(true);

// With options
MermaidOptions<?> opts = new MermaidOptions<>();
opts.setDarkMode(true);
opts.setLook("handDrawn");
md.setMermaidOptions(opts);
```

### KaTeX Math

```java
Markdown<?> md = new Markdown<>("$$E = mc^2$$");
md.setKatex(true);

// With options
KatexOptions<?> opts = new KatexOptions<>();
opts.setDisplayMode(true);
opts.setThrowOnError(false);
md.setKatexOptions(opts);
```

### Emoji Support

```java
Markdown<?> md = new Markdown<>("I :heart: ngx-markdown :rocket:");
md.setEmoji(true);
```

### Clipboard Copy

```java
Markdown<?> md = Markdown.fromSource("/assets/code-samples.md");
md.setClipboard(true);
```

### Full-Featured (All Plugins)

```java
Markdown<?> md = Markdown.full("# My Document\n\n```java\nSystem.out.println();\n```\n\n$$x^2$$\n\n:smile:");
page.add(md);
```

## Architecture

```
com.jwebmp.plugins.markdown
├── Markdown                # Main <markdown> Angular component
├── MarkdownOptions         # Pipe/component configuration options
├── MermaidOptions          # Mermaid diagram configuration
├── KatexOptions            # KaTeX math rendering configuration
├── CommandLineOptions      # PrismJS command line plugin options
├── config/
│   └── MarkdownPageConfigurator  # Plugin registration & TS dependencies
└── implementations/
    └── MarkdownInclusionModule   # Guice module scan inclusion
```

## TypeScript Dependencies

Automatically managed by the page configurator:

| Package | Version | Purpose |
|---------|---------|---------|
| `ngx-markdown` | `^19.0.0` | Angular markdown component |
| `marked` | `^17.0.0` | Markdown parser |
| `prismjs` | `^1.30.0` | Syntax highlighting |
| `mermaid` | `^11.0.0` | Diagrams & charts |
| `katex` | `^0.16.0` | Math rendering |
| `emoji-toolkit` | `^10.0.0` | Emoji support |
| `clipboard` | `^2.0.11` | Copy-to-clipboard |

## Dependencies

This plugin depends on the [JWebMP Prism plugin](../prism) (`com.jwebmp.plugins:prism`) which provides the PrismJS language and theme enumerations.

## License

Apache License 2.0
