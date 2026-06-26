import com.guicedee.client.services.config.IGuiceScanModuleInclusions;
import com.jwebmp.plugins.markdown.implementations.MarkdownInclusionModule;

module com.jwebmp.plugins.markdown {
    exports com.jwebmp.plugins.markdown;
    exports com.jwebmp.plugins.markdown.config;

    requires transitive com.jwebmp.core.base.angular.client;
    requires static com.jwebmp.core.angular;
    requires transitive com.jwebmp.client;
    requires transitive com.jwebmp.core;
    requires transitive com.jwebmp.plugins.prism;

    requires static lombok;

    provides com.jwebmp.core.services.IPageConfigurator with com.jwebmp.plugins.markdown.config.MarkdownPageConfigurator;
    provides IGuiceScanModuleInclusions with MarkdownInclusionModule;

    opens com.jwebmp.plugins.markdown to tools.jackson.databind, com.google.guice, com.jwebmp.core, com.jwebmp.core.angular;
    opens com.jwebmp.plugins.markdown.config to tools.jackson.databind, com.google.guice, com.jwebmp.core, com.jwebmp.core.angular;
    opens com.jwebmp.plugins.markdown.implementations to tools.jackson.databind, com.google.guice, com.jwebmp.core, com.jwebmp.core.angular;
}

