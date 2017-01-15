package inc.itnity.elbilad.domain.schedulers;

import rx.Scheduler;

/**
 * Created by alexeyverbitskiy on 10/31/16.
 */

public interface ObserveOn {
    Scheduler getScheduler();
}
