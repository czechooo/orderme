package com.ggralak.orderme.tiles;

import org.springframework.web.context.support.ContextExposingHttpServletRequest;
import org.springframework.web.servlet.view.tiles3.TilesView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Based on <a href="http://stackoverflow.com/questions/2848415/accessing-spring-beans-from-a-tiles-view-jsp">
 *     Accessing Spring beans from a Tiles view</a>
 * <br/>
 *
 * Unfortunately {@link TilesView} inherits from
 * {@link org.springframework.web.servlet.view.AbstractUrlBasedView} instead of
 * {@link org.springframework.web.servlet.view.InternalResourceView}.
 * <br/>
 * This prevents from using {@link org.springframework.web.servlet.view.InternalResourceViewResolver}
 * and its <code>exposedContextBeanNames</code> to expose properties beans to the jsp pages.
 * <br/>
 *
 * This class is based on {@link org.springframework.web.servlet.view.InternalResourceViewResolver}
 * and introduces <code>exposedContextBeanNames</code> to Tiles & JSP.
 */
public class TilesExposingBeansView extends TilesView {

    private boolean exposeContextBeansAsAttributes = false;
    private Set<String> exposedContextBeanNames;

    public void setExposeContextBeansAsAttributes(boolean exposeContextBeansAsAttributes) {
        this.exposeContextBeansAsAttributes = exposeContextBeansAsAttributes;
    }

    public void setExposedContextBeanNames(String[] exposedContextBeanNames) {
        this.exposedContextBeanNames = new HashSet<String>(Arrays.asList(exposedContextBeanNames));
    }

    protected HttpServletRequest getRequestToExpose(HttpServletRequest originalRequest) {
        if (this.exposeContextBeansAsAttributes || this.exposedContextBeanNames != null)
            return new ContextExposingHttpServletRequest(originalRequest, getWebApplicationContext(), this.exposedContextBeanNames);
        return originalRequest;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest requestToExpose = getRequestToExpose(request);
        exposeModelAsRequestAttributes(model, requestToExpose);
        super.renderMergedOutputModel(model, requestToExpose, response);
    }
}
