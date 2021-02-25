package org.noear.solon.logging;

import org.noear.mlog.AppenderSimple;
import org.noear.mlog.Level;
import org.noear.mlog.Metainfo;
import org.noear.solon.Solon;

/**
 * @author noear
 * @since 1.3
 */
public abstract class LogAbstractAppender extends AppenderSimple implements LogAppender {
    public LogAbstractAppender() {
        String levelStr = Solon.cfg().get("solon.logging.appender." + getName() + ".level");
        setLevel(Level.of(levelStr, getDefaultLevel()));

        enable = Solon.cfg().getBool("solon.logging.appender." + getName() + ".enable", true);
    }

    private boolean enable = true;

    @Override
    public boolean getEnable() {
        return enable;
    }

    private Level level;

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void append(String loggerName, Class<?> clz, Level level, Metainfo metainfo, Object content) {
        if (enable == false || this.level.code > level.code) {
            return;
        }

        appendDo(loggerName, clz, level, metainfo, content);
    }
}
