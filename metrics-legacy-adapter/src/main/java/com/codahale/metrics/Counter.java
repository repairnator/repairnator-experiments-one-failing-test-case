package com.codahale.metrics;

@Deprecated
public class Counter implements Metric, Counting {

    private final io.dropwizard.metrics5.Counter counter;

    public Counter() {
        this(new io.dropwizard.metrics5.Counter());
    }

    public Counter(io.dropwizard.metrics5.Counter counter) {
        this.counter = counter;
    }

    public void inc() {
        counter.inc(1);
    }

    public void inc(long n) {
        counter.inc(n);
    }

    public void dec() {
        counter.dec(1);
    }

    public void dec(long n) {
        counter.dec(-n);
    }

    @Override
    public long getCount() {
        return counter.getCount();
    }

    @Override
    public io.dropwizard.metrics5.Metric getDelegate() {
        return counter;
    }
}
