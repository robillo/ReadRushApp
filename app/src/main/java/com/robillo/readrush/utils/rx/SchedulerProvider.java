package com.robillo.readrush.utils.rx;

import io.reactivex.Scheduler;

@SuppressWarnings("unused")
public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
