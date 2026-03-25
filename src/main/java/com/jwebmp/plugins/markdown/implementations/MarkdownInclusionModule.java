package com.jwebmp.plugins.markdown.implementations;

import com.guicedee.client.services.config.IGuiceScanModuleInclusions;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Registers the NGX Markdown module for inclusion in the Guice dependency injection scan.
 * <p>
 * This implementation is discovered via the Java ServiceLoader mechanism through the
 * {@code META-INF/services/com.guicedee.client.services.config.IGuiceScanModuleInclusions} file.
 * </p>
 */
public class MarkdownInclusionModule implements IGuiceScanModuleInclusions<MarkdownInclusionModule>
{
    @Override
    public @NotNull Set<String> includeModules()
    {
        Set<String> set = new HashSet<>();
        set.add("com.jwebmp.plugins.markdown");
        return set;
    }
}

