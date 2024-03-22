package webapp.demo1_handler;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.event.EventListener;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author noear 2023/4/19 created
 */
@Mapping("/sse")
@Component
public class SseHandler implements Handler , EventListener<SseHandler.SseEvent> {
    private List<SseEvent> sseEvents = new ArrayList<>();

    @Override
    public void handle(Context ctx) throws Throwable {
        ctx.contentType("text/event-stream");
        ctx.charset("utf-8");

        while (true) {
            if (sseEvents.size() > 0) {
                Iterator<SseEvent> iterator = sseEvents.iterator();
                while (iterator.hasNext()) {
                    SseEvent event = iterator.next();
                    ctx.output(event.toString());
                    ctx.flush();
                }
            } else {
                Thread.sleep(1000);
            }
        }
    }

    @Override
    public void onEvent(SseEvent sseEvent) throws Throwable {
        sseEvents.add(sseEvent);
    }


    public static class SseEvent {

    }
}
