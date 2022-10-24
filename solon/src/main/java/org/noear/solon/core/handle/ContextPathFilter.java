package org.noear.solon.core.handle;

/**
 * 提供 ContextPath 类似的功能（优先级要极高）
 *
 * @author noear
 * @since 1.8
 */
public class ContextPathFilter implements Filter {
    private final String path;
    private final boolean forced;

    /**
     * @param path '/demo/'
     */
    public ContextPathFilter(String path, boolean forced) {
        if (path.endsWith("/")) {
            this.path = path;
        } else {
            this.path = path + "/";
        }

        this.forced = forced;
    }

    public ContextPathFilter(String path) {
        this(path, false);
    }


    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        if (ctx.pathNew().startsWith(path)) {
            ctx.pathNew(ctx.path().substring(path.length() - 1));
        } else {
            if (forced) {
                return;
            }
        }

        chain.doFilter(ctx);
    }
}
