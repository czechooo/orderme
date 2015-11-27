package com.ggralak.orderme.tiles;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * Based on <a href="http://stackoverflow.com/questions/2848415/accessing-spring-beans-from-a-tiles-view-jsp">
 *     Accessing Spring beans from a Tiles view</a>
 * <br/>
 *
 * Unfortunately {@link org.springframework.web.servlet.view.tiles3.TilesView} inherits from
 * {@link AbstractUrlBasedView} instead of
 * {@link org.springframework.web.servlet.view.InternalResourceView}.
 * <br/>
 * This prevents from using {@link org.springframework.web.servlet.view.InternalResourceViewResolver}
 * and its <code>exposedContextBeanNames</code> to expose properties beans to the jsp pages.
 * <br/>
 *
 * This class is based on {@link org.springframework.web.servlet.view.InternalResourceViewResolver}
 * and introduces <code>exposedContextBeanNames</code> to Tiles & JSP.
 */
public class TilesExposingBeansViewResolver extends UrlBasedViewResolver {

    private Boolean exposeContextBeansAsAttributes;
    private String[] exposedContextBeanNames;

    public void setExposeContextBeansAsAttributes(boolean exposeContextBeansAsAttributes) {
        this.exposeContextBeansAsAttributes = exposeContextBeansAsAttributes;
    }

    public void setExposedContextBeanNames(String[] exposedContextBeanNames) {
        this.exposedContextBeanNames = exposedContextBeanNames;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractUrlBasedView superView = super.buildView(viewName);
        if (superView instanceof TilesExposingBeansView) {
            TilesExposingBeansView view = (TilesExposingBeansView) superView;
            if (this.exposeContextBeansAsAttributes != null) view.setExposeContextBeansAsAttributes(this.exposeContextBeansAsAttributes);
            if (this.exposedContextBeanNames != null) view.setExposedContextBeanNames(this.exposedContextBeanNames);
        }
        return superView;
    }
}
